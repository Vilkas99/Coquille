package com.example.coquille.controllers.Colorful

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.coquille.R
import com.example.coquille.controllers.PreviewGame
import com.example.coquille.databinding.ActivityRepeatedFiguresBinding
import com.example.coquille.databinding.ActivityRepeatedFiguresLvl2Binding
import com.example.coquille.databinding.ActivityRepeatedFiguresLvl3Binding
import com.example.coquille.models.games.Colorful.Colorful
import com.example.coquille.utils.*
import kotlin.random.Random

class Repeated_figuresLvl3 : AppCompatActivity() {

    //Variable para llamar a la clase que maneja los datos del usuario y permite compartirlos con el perfil
    private val mySharedPreferences : MySharedPreferences = MySharedPreferences(this)
    //Variables privadas con una inicialización tardía, permiten acceder a la cantidad de corazones y los elementos del XML
    private lateinit var binding: ActivityRepeatedFiguresLvl3Binding
    private lateinit var heartViews : List<ImageView>
    private lateinit var hearts : HeartSystem
    lateinit var gameState : Colorful
    //Variables que permiten acceder al Bundle y a los puntos.
    val b: Bundle = Bundle()
    var points: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repeated_figures_lvl3)
        //Mediante la variable binding si infla el layout, para poder acceder a todos sus elementos.
        binding = ActivityRepeatedFiguresLvl3Binding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        //Sistema de corazones, accede a cada uno de los corazones dentro del layout
        heartViews = listOf(findViewById(R.id.memory_card_first_heart), findViewById(R.id.memory_card_second_heart), findViewById(R.id.memory_card_third_heart))
        hearts = HeartSystem(3, heartViews)

        //Inicialización del contructor
        gameState = Colorful(35000,0)

        //Funcion para generar la secuencia del nivel de manera aleatoria.
        genSequence()

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR1.setOnClickListener {
            if(binding.backButton.tag == binding.imageR1.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR2.setOnClickListener {
            if(binding.backButton.tag == binding.imageR2.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR3.setOnClickListener {
            if(binding.backButton.tag == binding.imageR3.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR4.setOnClickListener {
            if(binding.backButton.tag == binding.imageR4.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR5.setOnClickListener {
            if(binding.backButton.tag == binding.imageR5.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR6.setOnClickListener {
            if(binding.backButton.tag == binding.imageR6.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR7.setOnClickListener {
            if(binding.backButton.tag == binding.imageR7.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR8.setOnClickListener {
            if(binding.backButton.tag == binding.imageR8.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR9.setOnClickListener {
            if(binding.backButton.tag == binding.imageR9.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR10.setOnClickListener {
            if(binding.backButton.tag == binding.imageR10.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR11.setOnClickListener {
            if(binding.backButton.tag == binding.imageR11.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR12.setOnClickListener {
            if(binding.backButton.tag == binding.imageR12.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR13.setOnClickListener {
            if(binding.backButton.tag == binding.imageR13.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR14.setOnClickListener {
            if(binding.backButton.tag == binding.imageR14.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Mediante un Listener se determina la respuesta correcta y la cantidad de corazones disponibles.
        binding.imageR15.setOnClickListener {
            if(binding.backButton.tag == binding.imageR15.tag && hearts.currentHearts > 0){
                points = gameState.calculatePoints()
                binding.textPoints.text = points.toString()
                genSequence()
            }
            //En caso de que la respuesta sea incorrecta, se resta un corazón, de los 3 disponibles.
            else{
                hearts.reduceHeart()
            }
        }

        //Se llama a la función del timer, pasando el tiempo deseado, y los intervalos en que este se vaya reduciendo.
        timerSequence(35000, 1000)

    }

    //Función para generar la secuencia en los patrones.
    fun genSequence( ) {

        /*Mediante un random, de 0 a la longitud de la lista que llamamos del objeto FigureConstants
        * dentro de la misma buscamos la lista con valores correspodientes*/
        val randomIndex = Random.nextInt(0, FigureConstants.colorfulLvl3_1.size)

        //Mediante la variable imageToUse se almacena una de las imagenes dentro de la lista correspodiente
        val imageToUse = FigureConstants.colorfulLvl3_1[randomIndex] //3 "varawiiii!!!"
        val imageResource = resources.getIdentifier(imageToUse, null, packageName)

        //Se hace lo mismo con una lista diferente, para poder acceder a diferentes imágenes.
        val imageToUse2 = FigureConstants.colorfulLvl3_2[randomIndex] //2 "pocion"
        val imageResource2 = resources.getIdentifier(imageToUse2, null, packageName)

        //Se hace lo mismo con una lista diferente, para poder acceder a diferentes imágenes.
        val imageToUse3 = FigureConstants.colorfulLvl3_3[randomIndex] //1 "araña"
        val imageResource3 = resources.getIdentifier(imageToUse3, null, packageName)

        //Se hace lo mismo con una lista diferente, para poder acceder a diferentes imágenes.
        val imageToUse4 = FigureConstants.colorfulLvl3_4[randomIndex] //3 "varawiiii!!!"
        val imageResource4 = resources.getIdentifier(imageToUse4, null, packageName)

        //Se hace lo mismo con una lista diferente, para poder acceder a diferentes imágenes.
        val imageToUse5 = FigureConstants.colorfulLvl3_5[randomIndex] //2 "pocion"
        val imageResource5 = resources.getIdentifier(imageToUse5, null, packageName)

        //Se hace lo mismo con una lista diferente, para poder acceder a diferentes imágenes.
        val imageToUse6 = FigureConstants.colorfulLvl3_6[randomIndex] //1 "araña"
        val imageResource6 = resources.getIdentifier(imageToUse6, null, packageName)

        // Se llena la lista mutable con los valores de los imagesResourse
        val listToSort = mutableListOf(imageResource, imageResource2, imageResource2, imageResource2, imageResource3, imageResource3, imageResource4, imageResource4, imageResource5, imageResource5, imageResource5, imageResource6, imageResource6, imageResource3, imageResource4)
        // Se crea una segunda lista mutable, donde se almacenará las imágenes de manera aleatoria, para poder crear distintas secuencias .
        val listSort = mutableListOf<Int>()

        while (listToSort.size > 0){

            //Se genera un numero aleatorio 0 al tamaño de la listaMutable listToSort
            val randomIndex2 = Random.nextInt(0, listToSort.size)
            //Se obtiene ese valor de la lista mutable
            val comparacion = listToSort[randomIndex2]
            //Cuando se obtiene el numero se remueve de la lista
            listToSort.removeAt(randomIndex2)
            // Se agrega el valor removido a la nueva lista mutable
            listSort.add(comparacion)

        }

        //Se asigna a cada imagen un valor para que sea tomado de la segunda lista
        binding.imageR1.setBackgroundResource(listSort[0])
        binding.imageR2.setBackgroundResource(listSort[1])
        binding.imageR3.setBackgroundResource(listSort[2])
        binding.imageR4.setBackgroundResource(listSort[3])
        binding.imageR5.setBackgroundResource(listSort[4])
        binding.imageR6.setBackgroundResource(listSort[5])
        binding.imageR7.setBackgroundResource(listSort[6])
        binding.imageR8.setBackgroundResource(listSort[7])
        binding.imageR9.setBackgroundResource(listSort[8])
        binding.imageR10.setBackgroundResource(listSort[9])
        binding.imageR11.setBackgroundResource(listSort[10])
        binding.imageR12.setBackgroundResource(listSort[11])
        binding.imageR13.setBackgroundResource(listSort[12])
        binding.imageR14.setBackgroundResource(listSort[13])
        binding.imageR15.setBackgroundResource(listSort[14])

        /*Se asigna al botón de retorno, su variable tag, el valor del primer ImageSource, que es la única no repetida
         por lo que es la respuesta correcta*/
        binding.backButton.tag = imageResource

        //Para poder realizar la comparación a cada imagen se le asigna en su tag, el mismo valor que recibieron de la listSort.
        binding.imageR1.tag = listSort[0]
        binding.imageR2.tag = listSort[1]
        binding.imageR3.tag = listSort[2]
        binding.imageR4.tag = listSort[3]
        binding.imageR5.tag = listSort[4]
        binding.imageR6.tag = listSort[5]
        binding.imageR7.tag = listSort[6]
        binding.imageR8.tag = listSort[7]
        binding.imageR9.tag = listSort[8]
        binding.imageR10.tag = listSort[9]
        binding.imageR11.tag = listSort[10]
        binding.imageR12.tag = listSort[11]
        binding.imageR13.tag = listSort[12]
        binding.imageR14.tag = listSort[13]
        binding.imageR15.tag = listSort[14]

    }

    //Función para poder regresar a la vista de Preview
    fun routeToPreview(view : View){
        val intent = Intent(this, PreviewGame::class.java)
        //Se indica el nombre del juego al que se va a volver
        val nameGame :String = "colorful"
        val b : Bundle = Bundle()
        b.putString("indexArray", nameGame)
        intent.putExtras(b)
        startActivity(intent)
    }

    //Función ppara determinar tiempo
    fun timerSequence(time: Long, intervalo: Long){
        //Se crea el objeto de CountDownTimer recibe el tiempo y el intervalo en que este será reducido
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                binding.timer.setText("00:" + p0 / 1000)
            }
            //Una vez terminado el timer, entra a la segunda condición
            override fun onFinish() {
                //Una vez se termina el tiempo, los botones se desactivan
                binding.imageR1.setOnClickListener(null)
                binding.imageR2.setOnClickListener(null)
                binding.imageR3.setOnClickListener(null)
                binding.imageR4.setOnClickListener(null)
                binding.imageR5.setOnClickListener(null)
                binding.imageR6.setOnClickListener(null)
                binding.imageR7.setOnClickListener(null)
                binding.imageR8.setOnClickListener(null)
                binding.imageR9.setOnClickListener(null)
                //Se llama a la función de Game Over
                gameOver()
            }
        }.start()
    }

    fun gameOver(){
        //Mediante la clase Utils se llama al objeto usuario.
        val user = Utils.getCurrentUser(this)
        user.points += points
        //Se agregan los puntos obtenidos en el juego al usuario actual.
        mySharedPreferences.editData(user, "currentUser")
        //Mediante la clase de Utils se llama a la funcion para poder determinar los streak diarios y mandar las notificaciones del juego
        Utils.setEndStreakWorker(this)
        Utils.setStreakNotification(this)
        //Una vez terminado el nivel, se despliega un Popup indicando la cantidad de gemas que ha ganado.
        val intent = Intent(this, Popup::class.java)
        b.putString("titlePopup", "Felicidades!")
        b.putString("customized", "")
        b.putString("bodyPopup", "Haz ganado " + points.toString() + " gemas")
        intent.putExtras(b)
        startActivity(intent)
    }
}