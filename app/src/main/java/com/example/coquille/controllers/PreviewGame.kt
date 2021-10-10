package com.example.coquille.controllers

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.coquille.R
import com.example.coquille.databinding.ActivityPreviewGameBinding
import com.example.coquille.models.CardContent
import com.example.coquille.utils.Data
import com.example.coquille.models.User
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView

class PreviewGame() : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewGameBinding

    lateinit var cardsGame : Array<CardContent>

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        val bundle =intent.getStringExtra("indexArray").toString()
        when(bundle){
            "sequence" -> {
                cardsGame = Data.getCard("Secuencia")
                binding.gameTitle.setText("Secuencia")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.sequence_logo))

                binding.bodyDescription.setText(cardsGame[0].textCard)

                binding.titleTextCard1.setText(cardsGame[1].textCard)
                binding.iconCard1.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[1].srcImg!!))
                setDifficulty(this, cardsGame[1].difficulty!!, binding.difficultyLayout1, resources.getDrawable(R.drawable.ic_diamond_difficulty))


                binding.titleTextCard2.setText(cardsGame[2].textCard)
                binding.iconCard2.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[2].srcImg!!))
                setDifficulty(this, cardsGame[2].difficulty!!, binding.difficultyLayout2, resources.getDrawable(R.drawable.ic_diamond_difficulty))

                binding.titleTextCard3.setText(cardsGame[3].textCard)
                binding.iconCard3.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[3].srcImg!!))
                println(cardsGame[3].difficulty)
                setDifficulty(this, cardsGame[3].difficulty!!, binding.difficultyLayout3, resources.getDrawable(R.drawable.ic_diamond_difficulty))
            }
            "memory" -> {
                cardsGame = Data.getCard("Memoria")
                binding.gameTitle.setText("Juego de memoria? xd")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.memory_logo))

                binding.bodyDescription.setText(cardsGame[0].textCard)

                binding.titleTextCard1.setText(cardsGame[1].textCard)
                binding.iconCard1.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[1].srcImg!!))
                setDifficulty(this, cardsGame[1].difficulty!!, binding.difficultyLayout1, resources.getDrawable(R.drawable.ic_diamond_difficulty))


                binding.titleTextCard2.setText(cardsGame[2].textCard)
                binding.iconCard2.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[2].srcImg!!))
                setDifficulty(this, cardsGame[2].difficulty!!, binding.difficultyLayout2, resources.getDrawable(R.drawable.ic_diamond_difficulty))

                binding.titleTextCard3.setText(cardsGame[3].textCard)
                binding.iconCard3.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[3].srcImg!!))
                println(cardsGame[3].difficulty)
                setDifficulty(this, cardsGame[3].difficulty!!, binding.difficultyLayout3, resources.getDrawable(R.drawable.ic_diamond_difficulty))
            }
            "tortuga" -> {
                cardsGame = Data.getCard("Tortuga")
                binding.gameTitle.setText("Juego de tortuga? xd")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.ic_tortuguita_logo))

                binding.bodyDescription.setText(cardsGame[0].textCard)

                binding.titleTextCard1.setText(cardsGame[1].textCard)
                binding.iconCard1.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[1].srcImg!!))
                setDifficulty(this, cardsGame[1].difficulty!!, binding.difficultyLayout1, resources.getDrawable(R.drawable.ic_diamond_difficulty))


                binding.titleTextCard2.setText(cardsGame[2].textCard)
                binding.iconCard2.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[2].srcImg!!))
                setDifficulty(this, cardsGame[2].difficulty!!, binding.difficultyLayout2, resources.getDrawable(R.drawable.ic_diamond_difficulty))

                binding.titleTextCard3.setText(cardsGame[3].textCard)
                binding.iconCard3.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[3].srcImg!!))
                println(cardsGame[3].difficulty)
                setDifficulty(this, cardsGame[3].difficulty!!, binding.difficultyLayout3, resources.getDrawable(R.drawable.ic_diamond_difficulty))
            }
            "book" -> {
                cardsGame = Data.getCard("Cuentos")
                binding.gameTitle.setText("Cuentos locos xd")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.ic_book_logo))

                binding.bodyDescription.setText(cardsGame[0].textCard)

                binding.titleTextCard1.setText(cardsGame[1].textCard)
                binding.iconCard1.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[1].srcImg!!))
                setDifficulty(this, cardsGame[1].difficulty!!, binding.difficultyLayout1, resources.getDrawable(R.drawable.ic_diamond_difficulty))


                binding.titleTextCard2.setText(cardsGame[2].textCard)
                binding.iconCard2.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[2].srcImg!!))
                setDifficulty(this, cardsGame[2].difficulty!!, binding.difficultyLayout2, resources.getDrawable(R.drawable.ic_diamond_difficulty))

                binding.titleTextCard3.setText(cardsGame[3].textCard)
                binding.iconCard3.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[3].srcImg!!))
                println(cardsGame[3].difficulty)
                setDifficulty(this, cardsGame[3].difficulty!!, binding.difficultyLayout3, resources.getDrawable(R.drawable.ic_diamond_difficulty))
            }
            "colorful" -> {
                cardsGame = Data.getCard("Dibuja")
                binding.gameTitle.setText("Juego de colores? xd")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.ic_colorful_logo))

                binding.bodyDescription.setText(cardsGame[0].textCard)

                binding.titleTextCard1.setText(cardsGame[1].textCard)
                binding.iconCard1.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[1].srcImg!!))
                setDifficulty(this, cardsGame[1].difficulty!!, binding.difficultyLayout1, resources.getDrawable(R.drawable.ic_diamond_difficulty))


                binding.titleTextCard2.setText(cardsGame[2].textCard)
                binding.iconCard2.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[2].srcImg!!))
                setDifficulty(this, cardsGame[2].difficulty!!, binding.difficultyLayout2, resources.getDrawable(R.drawable.ic_diamond_difficulty))

                binding.titleTextCard3.setText(cardsGame[3].textCard)
                binding.iconCard3.setImageDrawable(ContextCompat.getDrawable(this, cardsGame[3].srcImg!!))
                println(cardsGame[3].difficulty)
                setDifficulty(this, cardsGame[3].difficulty!!, binding.difficultyLayout3, resources.getDrawable(R.drawable.ic_diamond_difficulty))
            }
        }
    }


    fun setDifficulty(context : Context, difficulty : Int, layout : LinearLayout, icon : Drawable){
        for (i in 1..difficulty){
            val dinamicImgeView = ImageView(context)
            dinamicImgeView.layoutParams = LayoutParams(150.toDp(), 150.toDp()
            ).apply {
                marginStart = 2.toDp()
                gravity = Gravity.CENTER_VERTICAL
            }
            dinamicImgeView.setImageDrawable(icon)
            layout.addView(dinamicImgeView)
        }
    }

    fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()


    fun routeToHome(view: View) {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }

    fun routeToGame(view: View) {
        when(binding.gameTitle.text.toString()){
            "Secuencia" -> {
                val intent = Intent(this, Sequence_game::class.java)
                startActivity(intent)
            }
            "Juego de memoria? xd" -> {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
            "Juego de tortuga? xd" -> {
                val intent = Intent(this, profile::class.java)
                startActivity(intent)
            }
            "Cuentos locos xd" -> {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
            "Juego de colores? xd" -> {
                val intent = Intent(this, activity_home::class.java)
                startActivity(intent)
            }
        }


    }

}