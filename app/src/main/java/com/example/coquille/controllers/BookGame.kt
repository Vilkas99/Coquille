package com.example.coquille.controllers

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.coquille.models.Book.ViewElements
import android.widget.*
import com.example.coquille.R
import com.example.coquille.databinding.ActivityBookGameBinding
import com.example.coquille.models.Question
import com.example.coquille.utils.Data
import com.google.android.material.button.MaterialButton
import android.os.Handler;
import android.os.Looper
import androidx.cardview.widget.CardView
import com.example.coquille.models.Book.Book
import com.example.coquille.utils.Popup


class BookGame : AppCompatActivity() {
    private lateinit var binding: ActivityBookGameBinding
    lateinit var questionsLevel : Array<Question>
    lateinit var pagesLevel : Array<String>
    lateinit var dialog : Dialog
    lateinit var buttonClose : Button

    var indexCuento = 0;
    var indexPregunta = 0;
    lateinit var backPage : ImageView
    lateinit var nextPage : ImageView
    lateinit var hintItem : CardView
    lateinit var textCuento : TextView
    var controlers = ViewElements(this)
    val handler = Handler(Looper.getMainLooper())
    val b:Bundle = Bundle()
    var listCorrect : Array<String> = arrayOf("¡Correcto!", "+20 Gemas")

    var game = Book(0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val bundle =intent.getStringExtra("level").toString()
        textCuento= binding.textPage
        hintItem = binding.itemHint
        dialog = Dialog(this)
        //buttonClose = dialog.findViewById<Button>(R.id.botonContinuar)

        when(bundle){
            "El león y el ratón" -> {
                pagesLevel = Data.getCuento(bundle)
                questionsLevel = Data.getPreguntas(bundle)
                binding.gameTitle.setText("El leon y el raton")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.lion))
                backPage = binding.backPage
                nextPage = binding.nextPage
                game.hints = 1
                currentPage(indexCuento, textCuento)
            }

            "El patito feo" -> {
                pagesLevel = Data.getCuento(bundle)
                questionsLevel = Data.getPreguntas(bundle)
                binding.gameTitle.setText("El patito feo")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.duck))
                backPage = binding.backPage
                nextPage = binding.nextPage
                game.hints = 1
                currentPage(indexCuento, textCuento)
            }

            "El pájaro y la ballena" -> {
                pagesLevel = Data.getCuento(bundle)
                questionsLevel = Data.getPreguntas(bundle)
                binding.gameTitle.setText("El pajaro y la ballena")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.whale))
                backPage = binding.backPage
                nextPage = binding.nextPage
                game.hints = 2
                currentPage(indexCuento, textCuento)
            }
            else -> println("Nivel inexistente")
        }

    }

    fun checkIndexPages(index : Int, backPage : ImageView, nextPage : ImageView) {
        when(index) {
            0 -> backPage.visibility = View.GONE
            pagesLevel.size - 1 -> nextPage.visibility = View.GONE
            else -> {
                backPage.visibility = View.VISIBLE
                nextPage.visibility = View.VISIBLE
            }
        }
    }

    fun checkAnswer(radioGroup: RadioGroup, button: MaterialButton){
        val radioID = radioGroup.checkedRadioButtonId
        if(radioID == questionsLevel[indexPregunta].correctAnswer){
            val intent = Intent(this, Popup::class.java)
            b.putString("titlePopup", listCorrect[0])
            b.putString("bodyPopup", listCorrect[1])
            intent.putExtras(b)
            startActivity(intent)
            /*buttonClose.setOnClickListener {
                dialog.dismiss()
            }*/
        } else {
            Toast.makeText(applicationContext, "RESPUESTA INCORRECTA D:", Toast.LENGTH_SHORT).show()
        }
        handler.postDelayed({
            if(game.finishedGame(pagesLevel, questionsLevel[indexPregunta].indexQuestion)){
                Toast.makeText(applicationContext, "LECTURA TERMINADA :D", Toast.LENGTH_SHORT).show()
                back()
            } else{
                indexPregunta++
                controlers.backToStory(binding.layoutPage, radioGroup, button)
                currentPage(indexCuento, textCuento)
            }

            }, 1000)
    }

    fun createQuestion(){
        binding.layoutPage.orientation = LinearLayout.VERTICAL
        binding.textPage.setText(questionsLevel[indexPregunta].textQuestion)
        val radioGroup = controlers.createRadioGroup(questionsLevel[indexPregunta].textAnswers)
        val button = controlers.createButton()
        binding.layoutPage.addView(radioGroup)
        binding.layoutPage.addView(button)
        button.setOnClickListener{
            checkAnswer(radioGroup, button)
        }
    }

    fun currentPage(index : Int, pageID : TextView){
        if(indexCuento == questionsLevel[indexPregunta].indexQuestion  || indexCuento+1 == questionsLevel[indexPregunta].indexQuestion){
            createQuestion()
            backPage.visibility = View.GONE
            nextPage.visibility = View.GONE
            hintItem.visibility = View.VISIBLE
        } else{
            hintItem.visibility = View.GONE
            checkIndexPages(index, backPage, nextPage)
            pageID.setText(pagesLevel[index])
        }

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

    fun back(){
        val intent = Intent(this, PreviewGame::class.java)
        val nameGame :String = "book"
        val b : Bundle = Bundle()
        b.putString("indexArray", nameGame)
        intent.putExtras(b)
        startActivity(intent)
    }

    fun routeToPreview(view : View){
        back()
    }
}

