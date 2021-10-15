package com.example.coquille.controllers.Colorful

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.coquille.R
import com.example.coquille.controllers.PreviewGame
import com.example.coquille.databinding.ActivityRepeatedFiguresBinding
import com.example.coquille.utils.FigureConstants
import com.example.coquille.utils.HeartSystem
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils
import kotlin.random.Random

class Repeated_figures : AppCompatActivity() {

    private val mySharedPreferences : MySharedPreferences = MySharedPreferences(this)
    private lateinit var binding: ActivityRepeatedFiguresBinding
    private lateinit var heartViews : List<ImageView>
    private lateinit var hearts : HeartSystem
    var points: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repeated_figures)
        binding = ActivityRepeatedFiguresBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        //Hearts system
        heartViews = listOf(findViewById(R.id.memory_card_first_heart), findViewById(R.id.memory_card_second_heart), findViewById(R.id.memory_card_third_heart))
        hearts = HeartSystem(3, heartViews)

        genSequence()



        binding.imageR1.setOnClickListener {
            if(binding.button.tag == binding.imageR1.tag && hearts.currentHearts > 0){
                points += 50
                binding.textPoints.text = points.toString()
                println("Estos son tus puntos boludo: " + points)
                genSequence()
            }
            else{
                hearts.reduceHeart()
            }
        }

        binding.imageR2.setOnClickListener {
            if(binding.button.tag == binding.imageR2.tag && hearts.currentHearts > 0){
                points += 50
                binding.textPoints.text = points.toString()
                println("Estos son tus puntos boludo: " + points)
                genSequence()
            }
            else{
                hearts.reduceHeart()
            }
        }

        binding.imageR3.setOnClickListener {
            if(binding.button.tag == binding.imageR3.tag && hearts.currentHearts > 0){
                points += 50
                binding.textPoints.text = points.toString()
                println("Estos son tus puntos boludo: " + points)
                genSequence()
            }
            else{
                hearts.reduceHeart()
            }
        }

        binding.imageR4.setOnClickListener {
            if(binding.button.tag == binding.imageR4.tag && hearts.currentHearts > 0){
                points += 50
                binding.textPoints.text = points.toString()
                println("Estos son tus puntos boludo: " + points)
                genSequence()
            }
            else{
                hearts.reduceHeart()
            }
        }

        binding.imageR5.setOnClickListener {
            if(binding.button.tag == binding.imageR5.tag && hearts.currentHearts > 0){
                points += 50
                binding.textPoints.text = points.toString()
                println("Estos son tus puntos boludo: " + points)
                genSequence()
            }
            else{
                hearts.reduceHeart()
            }
        }

        binding.imageR6.setOnClickListener {
            if(binding.button.tag == binding.imageR6.tag && hearts.currentHearts > 0){
                points += 50
                binding.textPoints.text = points.toString()
                println("Estos son tus puntos boludo: " + points)
                genSequence()
            }
            else{
                hearts.reduceHeart()
            }
        }

        timerSequence(10000, 1000)

    }

    fun genSequence( ) {

        val randomIndex = Random.nextInt(0, FigureConstants.words.size)

        val imageToUse = FigureConstants.words[randomIndex] //3 "varawiiii!!!"
        val imageResource = resources.getIdentifier(imageToUse, null, packageName)

        val imageToUse2 = FigureConstants.words2[randomIndex] //2 "pocion"
        val imageResource2 = resources.getIdentifier(imageToUse2, null, packageName)

        val imageToUse3 = FigureConstants.words3[randomIndex] //1 "araña"
        val imageResource3 = resources.getIdentifier(imageToUse3, null, packageName)

        // Se llena la lista mutable con los valores de los imagesResourse
        val list = mutableListOf(imageResource, imageResource2, imageResource2, imageResource2, imageResource3, imageResource3)
        // ["pocion", "araña", "varawiiii!!!"]
        val list2 = mutableListOf<Int>()

        while (list.size > 0){

            //Se genera un numero aleatorio 0 al tamaño de la listaMutable [2]
            val randomIndex2 = Random.nextInt(0, list.size)
            //Se obtiene ese valor de la lista mutable "araña"
            val comparacion = list[randomIndex2]
            //Cuando se obtiene el numero se remueve de la lista
            list.removeAt(randomIndex2)
            // ["pocion", "varawiiii!!!"]
            // Se agrega el valor removido "araña" a un boton junto a su tag
            list2.add(comparacion)

        }

        binding.imageR1.setBackgroundResource(list2[0])
        binding.imageR2.setBackgroundResource(list2[1])
        binding.imageR3.setBackgroundResource(list2[2])
        binding.imageR4.setBackgroundResource(list2[3])
        binding.imageR5.setBackgroundResource(list2[4])
        binding.imageR6.setBackgroundResource(list2[5])

        binding.button.tag = imageResource

        binding.imageR1.tag = list2[0]
        binding.imageR2.tag = list2[1]
        binding.imageR3.tag = list2[2]
        binding.imageR4.tag = list2[3]
        binding.imageR5.tag = list2[4]
        binding.imageR6.tag = list2[5]

    }

    fun routeToPreview(view : View){
        val intent = Intent(this, PreviewGame::class.java)
        val nameGame :String = "colorful"
        val b : Bundle = Bundle()
        b.putString("indexArray", nameGame)
        intent.putExtras(b)
        startActivity(intent)
    }

    fun timerSequence(time: Long, intervalo: Long){
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                binding.timer.setText("00:" + p0 / 1000)
            }

            override fun onFinish() {
                binding.imageR1.setOnClickListener(null)
                binding.imageR2.setOnClickListener(null)
                binding.imageR3.setOnClickListener(null)
                binding.imageR4.setOnClickListener(null)
                binding.imageR5.setOnClickListener(null)
                binding.imageR6.setOnClickListener(null)
                userPoints()
            }
        }.start()
    }

    fun userPoints(){
        val user = Utils.getCurrentUser(this)
        user.points += points
        mySharedPreferences.editData(user, "currentUser")
    }

}