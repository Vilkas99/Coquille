package com.example.coquille.controllers.Sequence

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coquille.R
import com.example.coquille.controllers.PreviewGame
import com.example.coquille.controllers.Sequence.levels.Sequence_stage1
import com.example.coquille.controllers.Sequence.levels.Sequence_stage2
import com.example.coquille.controllers.Sequence.levels.Sequence_stage3
import com.example.coquille.databinding.ActivitySequenceGameBinding
import com.example.coquille.models.games.Sequence.Sequence
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Popup
import com.example.coquille.utils.Utils

class Sequence_game() : AppCompatActivity() {

    //Variable para llamar a la clase que maneja los datos del usuario y permite compartirlos con el perfil
    private val mySharedPreferences : MySharedPreferences = MySharedPreferences(this)
    private lateinit var binding: ActivitySequenceGameBinding
    //Se crean variables que llaman a las funciones de los fragmentos, para poder acceder a sus recursos
    val infoFragment = Sequence_stage1()
    val infoFragment2 = Sequence_stage2()
    val infoFragment3 = Sequence_stage3()
    //Variables que permiten acceder al Bundle y a la clase de Secuencia.
    val b: Bundle = Bundle()
    lateinit var sequence: Sequence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequence_game)
        //Mediante la variable binding si infla el layout, para poder acceder a todos sus elementos.
        binding = ActivitySequenceGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        chooseLevel()

    }

    //La función de escoger nivel
    fun chooseLevel() {
        //Mediante una variable de Fragmento se obtiene el nivel actual
        var currentLevel = infoFragment as Fragment
        val bundle =intent.getStringExtra("sequenceLevel").toString()

        //Mediante el when, hacemos que el bundle seleccione a uno de los tres fragmentos
        when(bundle){
            "level1" ->
                currentLevel = infoFragment


            "level2" ->
                currentLevel = infoFragment2


            "level3" ->
                currentLevel = infoFragment3

        }

        //Si el nivel actual es igual al del fragmento corrspodiente se inicia, inicializando valores
        if(currentLevel == infoFragment2){
            supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.host, currentLevel)
                    commit()
                }
            //Se inicializa mediante la función de timer el tiempo para este nivel en específico.
            timerSequenceLvl2(35000, 1000)
        }
        //Si el nivel actual es igual al del fragmento corrspodiente se inicia, inicializando valores
        else if(currentLevel == infoFragment){
            supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.host, currentLevel)
                    commit()
                }
            //Se inicializa mediante la función de timer el tiempo para este nivel en específico.
            timerSequenceLvl1(15000, 1000)
        }
        //Si el nivel actual es igual al del fragmento corrspodiente se inicia, inicializando valores
        else if(currentLevel == infoFragment3){
            supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.host, currentLevel)
                    commit()
                }
            //Se inicializa mediante la función de timer el tiempo para este nivel en específico.
            timerSequenceLvl3(55000, 1000)
        }

    }

    //Función para poder regresar a la vista de Preview
    fun routeToPreview(view : View){
        val intent = Intent(this, PreviewGame::class.java)
        val nameGame :String = "sequence"
        val b : Bundle = Bundle()
        b.putString("indexArray", nameGame)
        intent.putExtras(b)
        startActivity(intent)
    }

    //Función ppara determinar tiempo del nivel 1
    fun timerSequenceLvl1(time: Long, intervalo: Long){
        //Se crea el objeto de CountDownTimer recibe el tiempo y el intervalo en que este será reducido
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                infoFragment.timer.setText("00:" + p0 / 1000)
            }
            //Una vez terminado el timer, entra a la segunda condición
            override fun onFinish() {
                //Una vez se termina el tiempo, los botones se desactivan
                infoFragment.option1.setOnClickListener(null)
                infoFragment.option2.setOnClickListener(null)
                infoFragment.option3.setOnClickListener(null)
                infoFragment.pointsString = infoFragment.puntos.text.toString()
                infoFragment.pointsInt = infoFragment.pointsString.toInt()
                //Se llama a la función de Game Over
                gameOverLvl1()

            }
        }.start()
    }

    //Función ppara determinar tiempo del nivel 2
    fun timerSequenceLvl2(time: Long, intervalo: Long){
        //Se crea el objeto de CountDownTimer recibe el tiempo y el intervalo en que este será reducido
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                infoFragment2.timer.setText("00:" + p0 / 1000)
            }
            //Una vez terminado el timer, entra a la segunda condición
            override fun onFinish() {
                //Una vez se termina el tiempo, los botones se desactivan
                infoFragment2.option1.setOnClickListener(null)
                infoFragment2.option2.setOnClickListener(null)
                infoFragment2.option3.setOnClickListener(null)
                infoFragment2.pointsString = infoFragment2.puntos.text.toString()
                infoFragment2.pointsInt = infoFragment2.pointsString.toInt()
                //Se llama a la función de Game Over
                gameOverLvl2()
            }
        }.start()
    }

    //Función ppara determinar tiempo del nivel 3
    fun timerSequenceLvl3(time: Long, intervalo: Long){
        //Se crea el objeto de CountDownTimer recibe el tiempo y el intervalo en que este será reducido
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                infoFragment3.timer.setText("00:" + p0 / 1000)
            }
            //Una vez terminado el timer, entra a la segunda condición
            override fun onFinish() {
                //Una vez se termina el tiempo, los botones se desactivan
                infoFragment3.option1.setOnClickListener(null)
                infoFragment3.option2.setOnClickListener(null)
                infoFragment3.option3.setOnClickListener(null)
                infoFragment3.pointsString = infoFragment3.puntos.text.toString()
                infoFragment3.pointsInt = infoFragment3.pointsString.toInt()
                //Se llama a la función de Game Over
                gameOverLvl3()
            }
        }.start()
    }

    //Función cuando se termina el juego en el primer nivel
    fun gameOverLvl1(){
        //Mediante la clase Utils se llama al objeto usuario.
        val user = Utils.getCurrentUser(this)
        user.points += infoFragment.pointsInt
        //Se agregan los puntos obtenidos en el juego al usuario actual.
        mySharedPreferences.editData(user, "currentUser")
        //Mediante la clase de Utils se llama a la funcion para poder determinar los streak diarios y mandar las notificaciones del juego
        Utils.setEndStreakWorker(this)
        Utils.setStreakNotification(this)
        //Una vez terminado el nivel, se despliega un Popup indicando la cantidad de gemas que ha ganado.
        val intent = Intent(this, Popup::class.java)
        b.putString("titlePopup", "Felicidades!")
        b.putString("customized", "")
        b.putString("bodyPopup", "Haz ganado " + infoFragment.pointsInt.toString() + " gemas")
        intent.putExtras(b)
        startActivity(intent)
    }

    //Función cuando se termina el juego en el segundo nivel
    fun gameOverLvl2(){
        //Mediante la clase Utils se llama al objeto usuario.
        val user = Utils.getCurrentUser(this)
        user.points += infoFragment2.pointsInt
        //Se agregan los puntos obtenidos en el juego al usuario actual.
        mySharedPreferences.editData(user, "currentUser")
        //Mediante la clase de Utils se llama a la funcion para poder determinar los streak diarios y mandar las notificaciones del juego
        Utils.setEndStreakWorker(this)
        Utils.setStreakNotification(this)
        //Una vez terminado el nivel, se despliega un Popup indicando la cantidad de gemas que ha ganado.
        val intent = Intent(this, Popup::class.java)
        b.putString("titlePopup", "Felicidades!")
        b.putString("customized", "")
        b.putString("bodyPopup", "Haz ganado " + infoFragment2.pointsInt.toString() + " gemas")
        intent.putExtras(b)
        startActivity(intent)
    }

    //Función cuando se termina el juego en el tercer nivel
    fun gameOverLvl3(){
        //Mediante la clase Utils se llama al objeto usuario.
        val user = Utils.getCurrentUser(this)
        user.points += infoFragment3.pointsInt
        //Se agregan los puntos obtenidos en el juego al usuario actual.
        mySharedPreferences.editData(user, "currentUser")
        //Mediante la clase de Utils se llama a la funcion para poder determinar los streak diarios y mandar las notificaciones del juego
        Utils.setEndStreakWorker(this)
        Utils.setStreakNotification(this)
        //Una vez terminado el nivel, se despliega un Popup indicando la cantidad de gemas que ha ganado.
        val intent = Intent(this, Popup::class.java)
        b.putString("titlePopup", "Felicidades!")
        b.putString("customized", "")
        b.putString("bodyPopup", "Haz ganado " + infoFragment3.pointsInt.toString() + " gemas")
        intent.putExtras(b)
        startActivity(intent)
    }

}