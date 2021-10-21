package com.example.coquille.controllers


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.coquille.models.games.Book.ViewElements
import android.widget.*
import com.example.coquille.R
import com.example.coquille.databinding.ActivityBookGameBinding
import com.example.coquille.models.Question
import com.example.coquille.utils.Data
import com.google.android.material.button.MaterialButton
import android.os.Handler;
import android.os.Looper
import com.example.coquille.models.games.Book.Book
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Popup
import com.example.coquille.utils.Utils



class BookGame : AppCompatActivity() {
    //Declaration of global variables
    private val mySharedPreferences : MySharedPreferences = MySharedPreferences(this)

    private lateinit var binding: ActivityBookGameBinding
    val handler = Handler(Looper.getMainLooper())
    val b: Bundle = Bundle()

    lateinit var questionsLevel: Array<Question>
    lateinit var pagesLevel: Array<String>
    var indexCuento = 0;
    var indexPregunta = 0;
    lateinit var backPage: ImageView
    lateinit var nextPage: ImageView
    lateinit var textCuento: TextView
    var controlers = ViewElements(this)
    var game = Book(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Binding between the xml and the activity
        binding = ActivityBookGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        //The PreviewGame sends us the information with a bundle
        val bundle =intent.getStringExtra("level").toString()
        //binding a variable with elements from the xml (Textview used for the text of the book)
        textCuento= binding.textPage

        //We update the score on screen [it initializes in zero because it's a new game)
        binding.points.setText(game.points.toString())

        //The bundle determines the level the user chose
        when(bundle){
            //Setting the correct information displayed on screen
            "El león y el ratón" -> {
                //Getting the level's information from an Object
                pagesLevel = Data.getCuento(bundle) //Array of strings
                questionsLevel = Data.getPreguntas(bundle) //Array of strings
                binding.gameTitle.setText("El leon y el raton")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.lion))
                backPage = binding.backPage
                nextPage = binding.nextPage
                //Starting the game
                currentPage(indexCuento, textCuento)
            }

            "El patito feo" -> {
                //Getting the level's information from an Object
                pagesLevel = Data.getCuento(bundle) //Array of strings
                questionsLevel = Data.getPreguntas(bundle) //Array of strings
                binding.gameTitle.setText("El patito feo")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.duck))
                backPage = binding.backPage
                nextPage = binding.nextPage
                //Starting the game
                currentPage(indexCuento, textCuento)
            }

            "El pájaro y la ballena" -> {
                //Getting the level's information from an Object
                pagesLevel = Data.getCuento(bundle) //Array of strings
                questionsLevel = Data.getPreguntas(bundle) //Array of strings
                binding.gameTitle.setText("El pajaro y la ballena")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.whale))
                backPage = binding.backPage
                nextPage = binding.nextPage
                //Starting the game
                currentPage(indexCuento, textCuento)
            }
            else -> println("Nivel inexistente")
        }

    }

    //Function that is in charge of determining the visibility of the buttons
    fun checkIndexPages(index : Int, backPage : ImageView, nextPage : ImageView) {
        when(index) {
            //This means we are in the first page of the reading
            0 -> backPage.visibility = View.GONE
            pagesLevel.size - 1 -> nextPage.visibility = View.GONE
            else -> {
                //This means we are in the last page of the reading
                backPage.visibility = View.VISIBLE
                nextPage.visibility = View.VISIBLE
            }
        }
    }

    //Function that checks the answer selected by the user
    fun checkAnswer(radioGroup: RadioGroup, button: MaterialButton){
        val radioID = radioGroup.checkedRadioButtonId
        //The Answer selected corresponds to the correct answer of the question
        if(radioID == questionsLevel[indexPregunta].correctAnswer){
            //We call the activity Poup with the help of a bundle, to send information from activity to activity
            val intent = Intent(this, Popup::class.java)
            b.putString("titlePopup", "Correcto!")
            b.putString("customized", "")
            b.putString("bodyPopup", "70 Gemas")
            intent.putExtras(b)
            startActivity(intent)
            //Everytime the user gets the correct answer, he/she obtains 70 points
            game.updatePoints(70)
            //We display the total score of the user in screen
            binding.points.setText(game.points.toString())
        } else { //The answer selected is wrong
            //We call the same popup but sending different information (text with the actual correct answer)
            val intent = Intent(this, Popup::class.java)
            b.putString("titlePopup", "Incorrecto")
            b.putString("bodyPopup", "")
            b.putString("customized","Respuesta correcta:"+ questionsLevel[indexPregunta].textAnswers[questionsLevel[indexPregunta].correctAnswer])
            intent.putExtras(b)
            startActivity(intent)
        }
        //We set a delay of 2.5 seconds for the popup to show in screen and leave
        handler.postDelayed({
            //Verifying if the reading is finished
            if(game.finishedGame(pagesLevel, questionsLevel[indexPregunta].indexQuestion)){ //Reading finished
                //We show in screen the toast letting the user know that the reading is finished
                Toast.makeText(applicationContext, "LECTURA TERMINADA :D", Toast.LENGTH_SHORT).show()
                //We call the function back to return to the preview screen of the game
                back()
            } else{ //Reading not finished yet
                //We increase the index of the questions of the reading
                indexPregunta++
                //We call the method backToStory from the class ViewElements
                controlers.backToStory(binding.layoutPage, radioGroup, button)
                //Continue with the reading
                currentPage(indexCuento, textCuento)
            }
        }, 2500)

    }

    //Function that creates the correspondent question and displays it on screen
    fun createQuestion(){
        binding.layoutPage.orientation = LinearLayout.VERTICAL
        //We update the text fot the question text
        binding.textPage.setText(questionsLevel[indexPregunta].textQuestion)
        //We call our variable controlers (of type ViewElement [Class]) to create the radioGroup for the correspondent answers of the questions and the button
        val radioGroup = controlers.createRadioGroup(questionsLevel[indexPregunta].textAnswers)
        val button = controlers.createButton()
        //We add this two components to the current page
        binding.layoutPage.addView(radioGroup)
        binding.layoutPage.addView(button)
        //We set a listener to the button which will check the answer of the user
        button.setOnClickListener{
            checkAnswer(radioGroup, button)
        }
    }

    //Function in charge of starting the logic of the game
    fun currentPage(index : Int, pageID : TextView){
        //First we determine if the current page (of the reading) is a question (since the questions are displayed in the middle of the reading multiple times)
        if(indexCuento == questionsLevel[indexPregunta].indexQuestion  || indexCuento+1 == questionsLevel[indexPregunta].indexQuestion){
            //Creation of the question
            createQuestion()
            //We make our buttons backPage and nextPage invisible
            backPage.visibility = View.GONE
            nextPage.visibility = View.GONE
        } else{ //In case the current page is the continuation of the reading
            //We determine the visibility of the buttons
            checkIndexPages(index, backPage, nextPage)
            //We set the text of the reading in the TextView
            pageID.setText(pagesLevel[index])
        }

    }

    //Function that allows the user to change from page to page
    fun movePage(view : View){
        //Moving to the next Page
        if(view.visibility == View.VISIBLE && view.resources.getResourceName(view.id) == "com.example.coquille:id/nextPage"){
            indexCuento += 1
            currentPage(indexCuento, textCuento)
            //Moving to a preview page
        } else if (view.visibility == View.VISIBLE && view.resources.getResourceName(view.id) == "com.example.coquille:id/backPage"){
            indexCuento -= 1
            currentPage(indexCuento, textCuento)
        }
    }

    //Function that saves the points won locally
    fun userPoints(){
        val user = Utils.getCurrentUser(this)
        user.points += game.points
        mySharedPreferences.editData(user, "currentUser")
    }

    //Function that saves the total points of the user locally and goes back to the preview game
    fun back(){
        userPoints()
        Utils.setEndStreakWorker(this)
        Utils.setStreakNotification(this)
        val intent = Intent(this, PreviewGame::class.java)
        val nameGame :String = "book"
        val b : Bundle = Bundle()
        b.putString("indexArray", nameGame)
        intent.putExtras(b)
        startActivity(intent)
    }

    //Function implemented when the "back" button is tapped
    fun routeToPreview(view : View){
        back()
    }

}

