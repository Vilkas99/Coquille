package com.example.coquille.controllers.Turtle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.coquille.R
import com.example.coquille.controllers.Turtle.levels.TurtleLevel1_Fragment
import com.example.coquille.controllers.Turtle.levels.TurtleLvl2Fragment
import com.example.coquille.controllers.activity_home
import com.example.coquille.databinding.ActivityTurtleBinding
import com.example.coquille.models.games.Turtle.Level
import com.example.coquille.models.games.Turtle.Position
import com.example.coquille.models.games.Turtle.TurtleGame
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils

class Turtle : AppCompatActivity() {

    lateinit var sharedPref: MySharedPreferences

    //Estado de juego
    lateinit var gameState: TurtleGame
    lateinit var binding: ActivityTurtleBinding

    //Positions
    lateinit var positions: MutableList<View>
    lateinit var currentPosView: View

    //Obstacles
    lateinit var firePositions: MutableList<View>
    lateinit var sharkPositions: MutableList<View>

    //Abilities
    lateinit var freezeSharkView : View
    lateinit var waterView : View
    lateinit var slowMotionView : View

    //Level fragments
    val level1Fragment = TurtleLevel1_Fragment()
    val level2Fragment = TurtleLvl2Fragment()

    //currentLevel
    lateinit var myLevel : Level

    var padding  = -50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = MySharedPreferences(this)

        //Binding
        binding = ActivityTurtleBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        //Asignamos a nuestra variable la información sobre los poderes representados en XML
        // A cada uno de ellos le creamos una posición, y se la asignamos como Tag.
        freezeSharkView = binding.freeze
        val freezeValue = Position(Utils.getId(freezeSharkView), "freezeShark",0,0)
        freezeSharkView.setTag(freezeValue)

        waterView = binding.water
        val waterValue = Position(Utils.getId(waterView), "water",0,0)
        waterView.setTag(waterValue)

        slowMotionView = binding.slowMotion
        val slowValue = Position(Utils.getId(slowMotionView), "slowMotion",0,0)
        slowMotionView.setTag(slowValue)


        chooseLevel() //Llamamos a la función "chooseLevel"



    }


    //Función que se encarga de determinar el fragmento a utilizar, de acuerdo al nivel seleccionado.
    fun chooseLevel() {
        //Creamos la variable del fragmento.
        var currentLevel = level1Fragment as Fragment

        //Obtenemos de los "intent" aquel con la llave de "turtleLevel"
        val bundle =intent.getStringExtra("turtleLevel").toString()

        //Y dependiendo del nivel, asignamos un fragmento o el otro.
        when(bundle){
            "level1" ->
                currentLevel = level1Fragment

            "level2" ->
                currentLevel = level2Fragment

        }

        //Realizamos la transacción para mostrar el nivel seleccionado a través del fragmento
        supportFragmentManager.beginTransaction().apply {
            replace(binding.turtleLevel.id, currentLevel)
            commit()
        }
    }

    //Función para establecer todas las propiedades del nivel (Ejecutada desde el fragmento)
    fun setCurrentLevel(level : Level){

        //Del nivel obtenemos las posiciones, las ubicaciones del fuego, de los tiburones y la del jugador.
        positions = level.positions
        firePositions = level.firePos
        sharkPositions = level.sharkPos
        currentPosView = level.initialPos

        //Creamos un estado de juego con TurtleGame
        gameState = TurtleGame(level, 0)

        //Establecemos el nivel en cuestión
        myLevel = level

        //Ejecutamos la función "timeSequence" para crear un contador de tiempo de acuerdo al asignado por el nivel
        timerSequence(level.totalTime as Long,1000, this)
    }


    //Función que se encarga de procesar toda la interacción que tenga el jugador con cualquiera de los elementos en cuestión
    fun handleInteraction(view: View?){
        //Obtenemos el tag de la view con la que está interactuando el usuario.
        val tag = view?.getTag() as Position
        //Si el estado de esta view es de posición...
        if( tag.state == "position"){
            //Le preguntamos al estado de juego si puede administrar una interacción cuyo estado es el de desplazarse...
            if (gameState.handleInteraction("move",this, view)) {
                //Detenemos la animación que se estaba ejecutando en nuestra posición actual
                Utils.stopAnimation(currentPosView as LottieAnimationView)
                //Detenemos cualquier animación de "Wow" que se esté ejecutando.
                Utils.stopAnimation(binding.wowIcon )

                //Creamos la animación del jugador en la view a la que nos queremos desplazar, para simular el efecto de movimiento
                Utils.createAnimation(view as LottieAnimationView, R.raw.turtle, 30, padding)

                //Actualizamos nuestra posición actual.
                currentPosView = gameState.currentPosition

                //Llamamos a la función "setMovementPosition"
                setMovementPosition(positions, false, false)
            }

            //Si el estado del objeto es de "fire" y el estado del jugador es "removeFire"...
        } else if (tag.state == "fire" && gameState.playerState == "removeFire"){
            //Ejectuamos setMovementPosition
            setMovementPosition(firePositions, false, true)
            //Si el estado de juego puede administrar un estado de "removeFire"...
            if (gameState.handleInteraction("removeFire",this, view)){
                //Obtenemos las posiciones de fuego del estado de fuego.
                firePositions = gameState.firePositions

                //Detenemos la animación de fuego en esa view...
                Utils.stopAnimation(view as LottieAnimationView)

                //Cambiamos el icono de la habilidad de agua para denotar que esta se encuentra recargandose...
                waterView.setBackgroundResource(R.drawable.habilidad_2_lock)
                //Actualizamos los puntos que ha ganado el jugador
                binding.puntosText.setText(gameState.points.toString())

                //Ejecutamos la función randomNice
                randomNice()

            }
            //Si el tag es de "SlowMotion" entonces están haciendo click en la habilidad del mismo nombre...
        } else if (tag.state == "slowMotion"){
            //Le preguntamos al estado de juego si puede administrar una interacción de slowMotion
            if (gameState.handleInteraction("slowMotion", this, null)){

                //Cambiamos el icono de la habilidad de slowMotion para denotar que esta se encuentra recargandose...
                slowMotionView.setBackgroundResource(R.drawable.habilidad_3_lock)

                //Detenemos la animación actual en la posición del jugador
                Utils.stopAnimation(currentPosView as LottieAnimationView)
                //Y le ejecutamos una nueva en donde la tortuga se encuentra en llamas para denotar este nuevo poder.
                Utils.createAnimation(currentPosView as LottieAnimationView, R.raw.turtle_fire, 40, padding)

                //Llamamos a la función "setMovementPosition"
                setMovementPosition(positions, true, false)
            }

            //Si el tag es de "freezeShark" entonces están haciendo click en la habilidad del mismo nombre...
        } else if (tag.state == "freezeShark"){
            //Le preguntamos al estado de juego si puede administrar una interacción de freezeShark
            if (gameState.handleInteraction("freezeShark",this, null)){
                //Cambiamos el icono de la habilidad de freezeShark  para denotar que esta se encuentra recargandose
                freezeSharkView.setBackgroundResource(R.drawable.habilidad_1_lock)

                //Por cada tiburon que tengamos...
                sharkPositions.forEach{ shark ->
                    //Le detenemos su animación actual
                    Utils.stopAnimation(shark as LottieAnimationView)
                    //Y ejecutamos la animación donde están congelados.
                    Utils.createAnimation(shark as LottieAnimationView, R.raw.shark_freeze, 50, padding)
                }

                //Ejecutamos la función de randomNice
                randomNice()
            }
            //Si la tag es de "player" significa que el jugador está haciendo click en su posición actual
        } else if (tag.state == "player"){
            //Establecemos que el estado del jugador es el de "Moving"
            gameState.playerState = "moving"
            //Obtenemos todas sus posibles posiciones de desplazamiento a través de la función returnNeighbour del estado de juego
            val possiblePositions =  gameState.returnNeighbour(positions)
            //Llamamos a la función setMovementPosition con estas posiciones
            setMovementPosition(possiblePositions, true, false)

            //Finalmente, si el tag es de "water" entonces están tocando la habilidad con el mismo nombre
        } else if(tag.state == "water"){
            //Establecemos que el estado del jugador es el de "removeFire"
            gameState.playerState = "removeFire"
            //Llamaos a la función setMovementPosition
            setMovementPosition(firePositions, true, true)
        }
    }




    //Función que se encarga de establecer un timer de acuerdo al tiempo brindado, el intervalor de avance y el contexto
    fun timerSequence(time: Long, intervalo: Long, context : Context){
        //Establecemos los contadores que determinan los tiempos en donde los tiburones cambiaran de posición, y los fuegos aparecerán.
        var sharkTime = 0
        var fireTime = 0
        //Creamos un "CountDownTimer" con el tiempo e intervalos en cuestión.
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                    //En cada segundo, ejecutamos evaluateCoolDown y evaluateGameState
                    evaluateCoolDown()
                    evaluateGameState()

                    //Si el estado de juego es "won" o el estado de juego es "finished"...
                    if (gameState.currentState == "won" || gameState.currentState == "finished"){
                        //Obtenemos al usuario actual...
                        val currentUserUpdated = Utils.getCurrentUser(context)
                        //Le asignamos los puntos que ha ganado en el juego
                        currentUserUpdated.points += gameState.points
                        //Actualizamos la infor en sharedPref
                        sharedPref.editData(currentUserUpdated, "currentUser")
                        //Ejectuamos las funciones routeToHome y cancel
                        routeToHome()
                        cancel()
                    }

                    //En cada segundo, ejecutamos la función removeCoolDown y aumentamos el tiempo del estado del juego
                    gameState.removeCoolDown()
                    gameState.currentTime    += 1

                    //A su vez, aumentamos en un el tiempo de aparición de los tiburos y fuegos
                    sharkTime += 1
                    fireTime += 1

                    //Si el tiempo de aparición de los tiburones es mayor o igual al determinado por el nivel, y el estado de juego no se encuentra ni en "freeze" o "slow"
                    if (sharkTime >= myLevel.timeForSharks && (gameState.currentState != "freeze" && gameState.currentState != "slow") ){
                        //Ejecutamos la función setSharkPositions
                        setSharkPositions()
                        //Reiniciamos el tiempo de aparición
                        sharkTime = 0
                    }

                    //Misma situación para el tiempo de aparición del fuego.
                    if (fireTime >= myLevel.timeForFire && (gameState.currentState != "freeze" && gameState.currentState != "slow")){
                        setFirePositions()
                        fireTime = 0
                    }

                    //A su vez, cada segundo actualizamos el tiempo que aparece en pantalla.
                    binding.tiempoText.setText((p0 / 1000).toString())
                    // Y ejecutamos la función evaluateGameState de nuestro estado de juego
                    gameState.evaluateGameState(context, false)

            }

            //Cuando finalice el contador...
            override fun onFinish() {
                //Ejecutamos la función evaluateGameState de nuestro estado de juego, pero ahora le determinamos que el juego ha finalizado
                gameState.evaluateGameState(context, true)
            }
        }.start() //Después de las configuraciones, lo iniciamos.
    }

    //Funciones que nos transfiere a la pantalla de home
    fun routeToHome() {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }


    //Funciones que nos transfiere a la pantalla de home haciendo uso de un botón
    fun routeToHomeButton(view : View) {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }

    //Función que se encarga de evaluar el tiempo de recarga de las habilidades
    fun evaluateCoolDown(){
        //Si la habilidad de agua se está recargando, establecemos en la interfaz el tiempo restante para ser utilizada
        if (gameState.coolDownWater > 0) binding.cooldownWater.setText(gameState.coolDownWater.toString())
        else {
            //Si la habilidad no se está recargando, significa que puede ser utilizada, así que establecemos su texto de recarga como "vacio"
            binding.cooldownWater.text = ""
            //Y establecemos su background con el icono de la habilidad (Ahora sin la 'X' que denotaba su estado anterior de carga)
            waterView.setBackgroundResource(R.drawable.habilidad_2)
        }

        //El proceso anterior se repite para cada una de las demás habilidades.
        if (gameState.coolDownFreezeMonkey > 0) binding.cooldownFreeze.setText(gameState.coolDownFreezeMonkey.toString())
        else {
            binding.cooldownFreeze.text = ""
            freezeSharkView.setBackgroundResource(R.drawable.habilidad_1)
        }

        if (gameState.coolDownSlowMotion > 0) binding.cooldownSlowMotion.setText(gameState.coolDownSlowMotion.toString())
        else {
            binding.cooldownSlowMotion.text = ""
            slowMotionView.setBackgroundResource(R.drawable.habilidad_3)
        }
    }

    //Función que se encarga de evaluar el estado de juego..
    fun evaluateGameState(){
        //Si el estado de juego es de freeze...
        if (gameState.currentState == "freeze"){
            //Y la duración de este es de 0, significa que el efecto de "freeze" ya ha pasado...
            if (gameState.freezeDuration == 0){
                //En ese caso, recorremos todos los tiburones en cuestión, y detenemos sus animación de freeze para poder ejecutar su animación normal
                sharkPositions.forEach{ shark ->
                    Utils.stopAnimation(shark as LottieAnimationView)
                    Utils.createAnimation(shark as LottieAnimationView, R.raw.shark, 70, padding)
                }
                //Cambiamos el estado de juego a normal
                gameState.currentState = "normal"
            }
            //Si el estado de juego es de slow...
        } else if (gameState.currentState == "slow"){
            //Y la duración de este es de 0, significa que el efecto de "slow" ya ha pasado...
            if (gameState.slowDuration == 0){
                //Detenemos la animación de la posición actual del jugador
                Utils.stopAnimation(currentPosView as LottieAnimationView)
                //Establecemos la animación de la tortuga
                Utils.createAnimation(currentPosView as LottieAnimationView, R.raw.turtle, 70, padding)
                //Cambiamos el estado de juego a normal
                gameState.currentState = "normal"
            }
        }
    }

    //Función que se encarga de establecer nuevos "fuegos" en el tablero.
    fun setFirePositions(){
        //Creamos una variable para la próxima posición.
        var newPossiblePosition: View
        do { //Generamos un doWhile que se ejecutará si esta nueva posición ya se encuentra ocupada ya sea por otro fuego, un tiburón, o el jugador
            //Obtenemos el próximo indice random
            val nextIndexRandom = (0 until positions.size).random()
            //Accedemos a la nueva posicion con ese índice
            newPossiblePosition = positions[nextIndexRandom]
        } while ( newPossiblePosition in firePositions || newPossiblePosition in sharkPositions || newPossiblePosition == currentPosView)

        //Obtenemos la información de esa posición
        var data = newPossiblePosition.getTag() as  Position
        data.state = "fire" // Y le asignamos a su estado el valor de "fire"

        //Generamos la animación del "fuego" en esa nueva posición
        Utils.createAnimation(newPossiblePosition as LottieAnimationView, R.raw.fire_animation, 30, padding)
        //La añadimos a nuestro arreglo de posiciones en llamas.
        firePositions.add(newPossiblePosition)
        //Actualizamos el arreglo del estado de juego
        gameState.firePositions = firePositions
    }

    //Función que se encarga de generar un emoticon de asombro
    fun randomNice(){
        //Generamos una variable al azar del 0 al 10
        val nextIndexRandom = (0 until 10).random()
        //Si esta es mayora 7
        if (nextIndexRandom > 7){
            //Generamos un valor al azar para la animación
            val randomIndexAnimation = (0 until 2).random()
            when (randomIndexAnimation){
                //Ejecutamos la animación en cuestión
                0 -> Utils.createAnimation(binding.wowIcon, R.raw.nice_emoji, 1, 460)
                1 -> Utils.createAnimation(binding.wowIcon, R.raw.epic_emoji, 1, 460)
                2 -> Utils.createAnimation(binding.wowIcon, R.raw.love_emoji, 1, 460)
            }
        }
    }

    //Función que se encarga de generar nuevas posiciones para los tiburones
    fun setSharkPositions(){
        //Si el estado de juego es diferente a "freeze"
        if(gameState.currentState != "freeze"){
            //Generamos una lista de las nuevas posiciones para los tiburones
            var newSharks = mutableListOf<View>()
            //Por cada tiburon que tengamos actualmente...
            sharkPositions.forEach{ view ->
                //Generamos una posible nueva posición...
                var newPossibleSharkPos: View
                //A través de un doWhile que se ejecutará mientras esta posición se encuentre ocupada por algún otro tiburón, fuego o por el propio jugador
                do {
                    //Generamos un índice al azar
                    val nextIndexRandom = (0 until positions.size).random()
                    //Obtenemos la posición que refiere a ese índice
                    newPossibleSharkPos = positions[nextIndexRandom]
                } while ( newPossibleSharkPos in firePositions || newPossibleSharkPos in sharkPositions || newPossibleSharkPos == currentPosView)

                //Obtenemos la información original de la posición inicial del tiburón
                val originalData = view.getTag() as Position
                //Le asignamos un estado de "position" ya que el tiburón ya no se encontrará ahí
                originalData.state = "position"
                //Detenemos la animación de esa posición
                Utils.stopAnimation(view as LottieAnimationView)

                //Obtenemos la información de la nueva posición
                val newSharkData = newPossibleSharkPos.getTag() as Position
                //Le establecemos que su estado será de shark, porque ahora ahí estará el tiburón
                newSharkData.state = "shark"
                //Le generamos su animación ese lugar
                Utils.createAnimation(newPossibleSharkPos as LottieAnimationView, R.raw.shark, 40, padding)
                //Añadimos esa posición a nuestra lista de nuevos tiburones
                newSharks.add(newPossibleSharkPos)
            }
            //Actualizamos la lista
            sharkPositions = newSharks
            gameState.sharkPositions = newSharks
        }

    }

    //Función que se encarga de pintar de color naranja posiciones que cumplan con cierto estado...
    fun setMovementPosition(positions : MutableList<View>, nextPos : Boolean, fire: Boolean){
            //Por cada posición en nuestro arreglo de posiciones...
            positions.forEach{position ->
                //Si el resultado de positionEvaluation es verdadero, o si la variable "fire" es verdadera...
                if(positionEvaluation(position) || fire)
                    //Nos preguntamos si la variable nextPos es verdadera...
                    if (nextPos)
                        //De serlo, actualizamos esa posición con el icono del circulo naranja
                        position.setBackgroundResource(R.drawable.ic_next_pos_circle)
                    else
                        //De lo contrario, le colocamos un circulo vacío
                        position.setBackgroundResource(R.drawable.ic_pos_circle)
            }
    }

    //Función que se encarga de evaluar que cierta posición no se encuentre ocupada por un fuego, el jugador o algún tiburón
    fun positionEvaluation(position: View) : Boolean{
        return !firePositions.contains(position) && !sharkPositions.contains(position) && currentPosView != position
    }

}