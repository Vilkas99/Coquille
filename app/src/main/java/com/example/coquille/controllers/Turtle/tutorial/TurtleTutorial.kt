package com.example.coquille.controllers.Turtle.tutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.example.coquille.R
import com.example.coquille.controllers.activity_home
import com.example.coquille.databinding.ActivityPreviewGameBinding
import com.example.coquille.databinding.ActivityTurtleTutorialBinding
import com.example.coquille.utils.Utils
import org.w3c.dom.Text


//Posiciones
lateinit var posInicial : LottieAnimationView
lateinit var posActual : LottieAnimationView
lateinit var sharkPos : LottieAnimationView
lateinit var firePos :LottieAnimationView

//Poderes
lateinit var water : View
lateinit var slowMo : View
lateinit var freeze : View

//Estado
var tutoState = "initial"

//Interacciones
lateinit var button : Button
lateinit var description : TextView

//Binding
lateinit var binding: ActivityTurtleTutorialBinding

class TurtleTutorial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTurtleTutorialBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        //Llamamos a la función "initialSetup"
        initialSetup()

    }

    //Esta función se encarga de establecer la relación de los objetos XML con las variables en cuestión
    fun initialSetup(){
        posInicial = binding.mainPos
        posActual = posInicial

        sharkPos = binding.topLeft
        firePos = binding.topRight

        //Al inicio, establecemos todos los poderes como invisibles.
        water = binding.waterTuto
        water.visibility = View.INVISIBLE

        slowMo = binding.slowMotionTuto
        slowMo.visibility = View.INVISIBLE

        freeze = binding.freezeTuto
        freeze.visibility = View.INVISIBLE

        button = binding.button
        description = binding.descriptionTuto
    }

    //Función ejecutada por el botón de "Continuar" que se encarga de determinar cual será el siguiente tutorial, de acuerdo al estado del juego
    fun nextTuto(view: View){
        when(tutoState){
            "initial"->
                firstTuto()
            "firstTuto" ->
                secondTuto()
            "fourthTuto" ->
                fifthTuto()

            "fifthTuto" ->
                sixthTuto()

            "sixthTuto" ->
                seventhTuto()
            "seventhTuto" ->
                eightTuto()

            "tenthTuto" ->
                eleventhTuto()
            "twelfthTuto"->
                routeToHome()
        }
    }

    //Función que se encarga de desplazar a la tortuga
    fun moveTuga(view: View){
        //Si nos encontramops el segundo tutorial
        if(tutoState == "secondTuto"){
            //Establecemos el nuevo estado del tutorial
            tutoState = "thirdTuto"
            //Pintamos de color naranja las posiciones disponibles
            binding.leftPos.setBackgroundResource(R.drawable.ic_next_pos_circle)
            binding.rightPos.setBackgroundResource(R.drawable.ic_next_pos_circle)
            binding.topPos.setBackgroundResource(R.drawable.ic_next_pos_circle)
            //Hacemos invisible el botón
            button.visibility = View.INVISIBLE
            //Colocamos un nuevo texto como descripción
            description.setText("¡Podrás notar que ahora los circulos se colorearon de naranja! Eso significa que pudes moverte a ellos; haz 'tap' en alguno de ellos para mover a 'Tuga'")
        } else if(tutoState == "ninethTuto"){ //Si nos encontramos en el noveno tutorial...
            //Modificamos el background de la habilidad de agua, con el objetivo de representar que ha sido utilizada
            water.setBackgroundResource(R.drawable.habilidad_2_lock)
            //Detenemos la animación que se estuviese ejcutando en la posición inicial
            Utils.stopAnimation(posInicial)
            //Llamaos a la función para el noveno tutorial
            ninethTuto()
        }
    }

    //Función que se encarga de colocar a la tortuga en el lugar que le corresponde
    fun placeTuga(view: View){
        //Si nos encontramos en el tercer tutorial
        if(tutoState == "thirdTuto"){

            //Las posiciones anteriormente disponibles las colocamos con el circulo azul, para denotar que ya no estamos en movimiento
            binding.leftPos.setBackgroundResource(R.drawable.ic_pos_circle)
            binding.rightPos.setBackgroundResource(R.drawable.ic_pos_circle)
            binding.topPos.setBackgroundResource(R.drawable.ic_pos_circle)

            //Establecemos que la posición actual será aquella con la que interactuó el usuario
            posActual = view as LottieAnimationView
            //Detenemos la animación que se estuviese ejecutando en la posición inicial
            Utils.stopAnimation(posInicial)
            //Creamos una animación en la posición actual para denotar el desplazamiento de la tortuga
            Utils.createAnimation(view as LottieAnimationView, R.raw.turtle, 100, -50)
            //Cambiamos el estado del tutorial
            tutoState = "fourthTuto"
            //Ejecutamos el cuarto tutorial
            fourthTuto()
        }
    }

    //Función que se encarga de congelar al tiburón ejecutando la animación de congelado en la posición del tiburón
    fun freezeShark(view: View){
        if(tutoState == "sixthTuto"){
            Utils.createAnimation(sharkPos, R.raw.shark_freeze, 100, -50)
            freeze.setBackgroundResource(R.drawable.habilidad_1_lock)
            button.visibility = View.VISIBLE
            description.setText("Lo has congelado! Pero también tu habilidad se ha gastado; cuando tenga una cruz roja, significa que no podrá usarse hasta que se recargue.")
        }
    }

    //Función que se encarga de pintar la posición del fuego de naranja cuando el usuario hace click en el poder de agua
    fun tapOnWater(view: View){
        if (tutoState == "eightTuto"){
            posInicial.setBackgroundResource(R.drawable.ic_next_pos_circle)
            description.setText("Al usar tu habilidad, los lugares donde se encuentran los fueguitos cambian a color naranja! Haz tap encima del fueguito para apagarlo")
            tutoState = "ninethTuto"
        }
    }

    //Función que se encarga de pintar de naranaja todas las posiciones disponibles, después de haber utilizado la habilidad de "slow"
    fun tapOnSlow(view: View){
        if(tutoState == "twelfthTuto"){
            Utils.createAnimation(posActual, R.raw.turtle_fire, 100, -50)
            binding.topPos.setBackgroundResource(R.drawable.ic_next_pos_circle)
            binding.rightPos.setBackgroundResource(R.drawable.ic_next_pos_circle)
            binding.leftPos.setBackgroundResource(R.drawable.ic_next_pos_circle)
            slowMo.setBackgroundResource(R.drawable.habilidad_3_lock)

            button.visibility = View.VISIBLE
            button.setText("Finalizar")
            description.setText("Listo! Has terminado el tutorial! Haz click en 'Finalizar' para regresar al menú principal.")

        }
    }

    //Funciones que modifican el texto de la descripción de acuerdo al tutorial en cuestión.
    fun firstTuto(){
        Utils.createAnimation(posInicial,   R.raw.turtle, 200, -50)
        button.setText("Siguiente")
        description.setText("¡Este es tu personaje! Su nombre es 'Tuga' y es una tortuga aventurera ")
        tutoState = "firstTuto"
    }

    fun secondTuto(){
        description.setText("Los circulos son posiciones a las que te puedes mover; haz un 'tap' en 'Tuga'")
        button.visibility = View.INVISIBLE
        tutoState = "secondTuto"
    }

    fun fourthTuto(){
        button.visibility = View.VISIBLE
        description.setText("¡WOW! Tuga se ha movido! Recuerda que Tuga sólo se puede mover si haces tap en ella, y después tap en alguno de los círculos naranjas")
    }

    fun fifthTuto(){
        description.setText("OH NO! Ha aparecido un tiburón! Son inofensivos, pero estos te impiden moverte al lugar que ocupan.")
        Utils.createAnimation(sharkPos, R.raw.shark, 100, -50)
        tutoState = "fifthTuto"
    }

    fun sixthTuto(){
        button.visibility = View.INVISIBLE
        freeze.visibility = View.VISIBLE

        description.setText("¡Mira! Una habilidad ha aparecido! Haz click en ella para congelar al tiburón.")
        tutoState = "sixthTuto"
    }

    fun seventhTuto(){
        description.setText("¡Un fueguito ha aparecido! La misión de Tuga es apagar todos los fuegos antes de que se acabe el tiempo")
        Utils.createAnimation(posInicial, R.raw.fire_animation, 100, -50)
        tutoState = "seventhTuto"
    }

    fun eightTuto(){
        description.setText("¡Otra habilidad ha aparecido! ¡Con ella puedes apagar el fueguito! Hazle un tap encima")
        water.visibility = View.VISIBLE
        button.visibility = View.INVISIBLE
        tutoState = "eightTuto"
    }

    fun ninethTuto(){
        description.setText("¡Felicidades! Has apagado al fueguito; eres toda una tortuga aventurera. Pero mira, desbloqueaste otra habilidad")
        slowMo.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        tutoState = "tenthTuto"
    }

    fun eleventhTuto(){
        tutoState = "twelfthTuto"
        button.visibility = View.INVISIBLE
        description.setText("Al hacer 'Tap' en esta habilidad, Tuga se volverá superpodersa y podrá moverse a cualquier posición, sin importar lo lejos que esté. Pruébala haciendo un 'Tap' encima de esta")
    }


    fun routeToHome() {
        tutoState = "initial"
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }


    fun routeToHomeBut(view: View) {
        tutoState = "initial"
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }

}