package com.example.coquille.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.coquille.R
import com.example.coquille.databinding.ActivityBookGameBinding
import com.example.coquille.models.Question
import com.example.coquille.utils.Data


class BookGame : AppCompatActivity() {
    private lateinit var binding: ActivityBookGameBinding
    lateinit var questionsLevel : Array<Question>
    lateinit var pagesLevel : Array<String>

    var indexCuento = 0;
    var indexPregunta = 0;
    lateinit var backPage : ImageView
    lateinit var nextPage : ImageView
    lateinit var textCuento : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val bundle =intent.getStringExtra("level").toString()
        textCuento= binding.textPage

        when(bundle){
            "El león y el ratón" -> {
                pagesLevel = Data.getCuento(bundle)
                questionsLevel = Data.getPreguntas(bundle)
                binding.gameTitle.setText("El leon y el raton")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.lion))
                backPage = binding.backPage
                nextPage = binding.nextPage
                currentPage(indexCuento, textCuento)
            }

            "El patito feo" -> {
                pagesLevel = Data.getCuento(bundle)
                questionsLevel = Data.getPreguntas(bundle)
                binding.gameTitle.setText("El patito feo")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.duck))
                backPage = binding.backPage
                nextPage = binding.nextPage
                currentPage(indexCuento, textCuento)
            }

            "El pájaro y la ballena" -> {
                pagesLevel = Data.getCuento(bundle)
                questionsLevel = Data.getPreguntas(bundle)
                binding.gameTitle.setText("El pajaro y la ballena")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.whale))
                backPage = binding.backPage
                nextPage = binding.nextPage
                currentPage(indexCuento, textCuento)
            }
            else -> println("Nivel inexistente")
        }

    }


    fun checkIndex(index : Int, backPage : ImageView, nextPage : ImageView) {
        when(index) {
            0 -> backPage.visibility = View.GONE
            pagesLevel.size - 1 -> nextPage.visibility = View.GONE
            else -> {
                backPage.visibility = View.VISIBLE
                nextPage.visibility = View.VISIBLE
            }
        }
    }

    fun currentPage(index : Int, pageID : TextView){
        checkIndex(index, backPage, nextPage)
        pageID.setText(pagesLevel[index])
    }

    fun movePage(view : View){
        if(view.visibility == View.VISIBLE && view.resources.getResourceName(view.id) == "com.example.coquille:id/nextPage"){
            indexCuento += 1
            currentPage(indexCuento, textCuento)
        } else if (view.visibility == View.VISIBLE && view.resources.getResourceName(view.id) == "com.example.coquille:id/backPage"){
            indexCuento -= 1
            currentPage(indexCuento, textCuento)
        }
    }

    fun routeToPreview(view : View){
        val intent = Intent(this, PreviewGame::class.java)
        val nameGame :String = "book"
        val b : Bundle = Bundle()
        b.putString("indexArray", nameGame)
        intent.putExtras(b)
        startActivity(intent)
    }
}

