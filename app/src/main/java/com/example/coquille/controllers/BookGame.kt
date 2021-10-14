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
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Popup
import com.example.coquille.utils.Utils


class BookGame : AppCompatActivity() {
    private lateinit var binding: ActivityBookGameBinding
    lateinit var questionsLevel: Array<Question>
    lateinit var pagesLevel: Array<String>
    lateinit var dialog: Dialog
    lateinit var myPopup: Popup

    var indexCuento = 0;
    var indexPregunta = 0;
    lateinit var backPage: ImageView
    lateinit var nextPage: ImageView
    lateinit var textCuento: TextView
    var controlers = ViewElements(this)
    val handler = Handler(Looper.getMainLooper())
    val b: Bundle = Bundle()
    var listCorrect: Array<String> = arrayOf("¡Correcto!", "+20 Gemas")
    private val mySharedPreferences : MySharedPreferences = MySharedPreferences(this)


    var game = Book(0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val bundle =intent.getStringExtra("level").toString()
        textCuento= binding.textPage
        dialog = Dialog(this)
        myPopup = Popup()

        binding.points.setText(game.points.toString())

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
            val intent = Intent(this, myPopup::class.java)
            b.putString("titlePopup", listCorrect[0])
            b.putString("bodyPopup", listCorrect[1])
            myPopup
            intent.putExtras(b)
            startActivity(intent)
            super.onPause()
            game.updatePoints(70)
            binding.points.setText(game.points.toString())

        } else {
            val intent = Intent(this, myPopup::class.java)
            super.onPause()
            b.putString("titlePopup", "Incorrecto, respuesta correcta:")
            b.putString("bodyPopup", questionsLevel[indexPregunta].textAnswers[questionsLevel[indexPregunta].correctAnswer])
            intent.putExtras(b)
            startActivity(intent)
        }


        if(game.finishedGame(pagesLevel, questionsLevel[indexPregunta].indexQuestion)){
            Toast.makeText(applicationContext, "LECTURA TERMINADA :D", Toast.LENGTH_SHORT).show()
            back()
        } else{
            indexPregunta++
            controlers.backToStory(binding.layoutPage, radioGroup, button)
            currentPage(indexCuento, textCuento)
        }

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
        } else{
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

    fun userPoints(){
        val user = Utils.getCurrentUser(this)
        user.points += game.points
        mySharedPreferences.editData(user, "currentUser")
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
        userPoints()
        back()
    }

}

