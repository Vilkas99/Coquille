package com.example.coquille.controllers

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
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
import android.widget.LinearLayout.LayoutParams;
import com.example.coquille.controllers.Colorful.Repeated_figures
import com.example.coquille.controllers.Colorful.Repeated_figuresLvl2
import com.example.coquille.controllers.Colorful.Repeated_figuresLvl3
import com.example.coquille.controllers.Sequence.Sequence_game
import com.example.coquille.controllers.Turtle.Turtle
import com.example.coquille.controllers.Turtle.tutorial.TurtleTutorial
import com.example.coquille.utils.Utils

class PreviewGame() : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewGameBinding

    lateinit var cardsGame: Array<CardContent>

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        val bundle = intent.getStringExtra("indexArray").toString()
        when (bundle) {
            "sequence" -> {
                cardsGame = Data.getCard("Secuencia")
                binding.gameTitle.setText("Secuencia")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.sequence_logo))

                binding.bodyDescription.setText(cardsGame[0].textCard)

                binding.titleTextCard1.setText(cardsGame[1].textCard)
                binding.iconCard1.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[1].srcImg!!
                    )
                )
                setDifficulty(
                    this,
                    cardsGame[1].difficulty!!,
                    binding.difficultyLayout1,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )


                binding.titleTextCard2.setText(cardsGame[2].textCard)
                binding.iconCard2.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[2].srcImg!!
                    )
                )
                setDifficulty(
                    this,
                    cardsGame[2].difficulty!!,
                    binding.difficultyLayout2,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )

                binding.titleTextCard3.setText(cardsGame[3].textCard)
                binding.iconCard3.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[3].srcImg!!
                    )
                )
                println(cardsGame[3].difficulty)
                setDifficulty(
                    this,
                    cardsGame[3].difficulty!!,
                    binding.difficultyLayout3,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )
            }
            "memory" -> {
                cardsGame = Data.getCard("Memoria")
                binding.gameTitle.setText("Memorama")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.memory_logo))

                binding.bodyDescription.setText(cardsGame[0].textCard)

                binding.titleTextCard1.setText(cardsGame[1].textCard)
                binding.iconCard1.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[1].srcImg!!
                    )
                )
                setDifficulty(
                    this,
                    cardsGame[1].difficulty!!,
                    binding.difficultyLayout1,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )


                binding.titleTextCard2.setText(cardsGame[2].textCard)
                binding.iconCard2.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[2].srcImg!!
                    )
                )
                setDifficulty(
                    this,
                    cardsGame[2].difficulty!!,
                    binding.difficultyLayout2,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )

                binding.titleTextCard3.setText(cardsGame[3].textCard)
                binding.iconCard3.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[3].srcImg!!
                    )
                )
                println(cardsGame[3].difficulty)
                setDifficulty(
                    this,
                    cardsGame[3].difficulty!!,
                    binding.difficultyLayout3,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )
            }
            "tortuga" -> {
                cardsGame = Data.getCard("Tortuga")
                binding.gameTitle.setText("Las aventuras de Tuga")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.ic_tortuguita_logo))

                binding.bodyDescription.setText(cardsGame[0].textCard)

                binding.titleTextCard1.setText(cardsGame[1].textCard)
                binding.iconCard1.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[1].srcImg!!
                    )
                )
                setDifficulty(
                    this,
                    cardsGame[1].difficulty!!,
                    binding.difficultyLayout1,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )


                binding.titleTextCard2.setText(cardsGame[2].textCard)
                binding.iconCard2.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[2].srcImg!!
                    )
                )
                setDifficulty(
                    this,
                    cardsGame[2].difficulty!!,
                    binding.difficultyLayout2,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )

                binding.titleTextCard3.setText(cardsGame[3].textCard)
                binding.iconCard3.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[3].srcImg!!
                    )
                )
                println(cardsGame[3].difficulty)
                setDifficulty(
                    this,
                    cardsGame[3].difficulty!!,
                    binding.difficultyLayout3,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )
            }
            "book" -> {
                cardsGame = Data.getCard("Cuentos")
                binding.gameTitle.setText("Cuentos asombrosos")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.ic_book_logo))

                binding.bodyDescription.setText(cardsGame[0].textCard)

                binding.titleTextCard1.setText(cardsGame[1].textCard)
                binding.iconCard1.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[1].srcImg!!
                    )
                )
                setDifficulty(
                    this,
                    cardsGame[1].difficulty!!,
                    binding.difficultyLayout1,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )


                binding.titleTextCard2.setText(cardsGame[2].textCard)
                binding.iconCard2.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[2].srcImg!!
                    )
                )
                setDifficulty(
                    this,
                    cardsGame[2].difficulty!!,
                    binding.difficultyLayout2,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )

                binding.titleTextCard3.setText(cardsGame[3].textCard)
                binding.iconCard3.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[3].srcImg!!
                    )
                )
                println(cardsGame[3].difficulty)
                setDifficulty(
                    this,
                    cardsGame[3].difficulty!!,
                    binding.difficultyLayout3,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )
            }
            "colorful" -> {
                cardsGame = Data.getCard("Dibuja")
                binding.gameTitle.setText("Juego de colores? xd")
                binding.iconHeader.setImageDrawable(resources.getDrawable(R.drawable.ic_colorful_logo))

                binding.bodyDescription.setText(cardsGame[0].textCard)

                binding.titleTextCard1.setText(cardsGame[1].textCard)
                binding.iconCard1.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[1].srcImg!!
                    )
                )
                setDifficulty(
                    this,
                    cardsGame[1].difficulty!!,
                    binding.difficultyLayout1,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )


                binding.titleTextCard2.setText(cardsGame[2].textCard)
                binding.iconCard2.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[2].srcImg!!
                    )
                )
                setDifficulty(
                    this,
                    cardsGame[2].difficulty!!,
                    binding.difficultyLayout2,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )

                binding.titleTextCard3.setText(cardsGame[3].textCard)
                binding.iconCard3.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        cardsGame[3].srcImg!!
                    )
                )
                println(cardsGame[3].difficulty)
                setDifficulty(
                    this,
                    cardsGame[3].difficulty!!,
                    binding.difficultyLayout3,
                    resources.getDrawable(R.drawable.ic_diamond_difficulty)
                )
            }
        }
    }


    fun setDifficulty(context: Context, difficulty: Int, layout: LinearLayout, icon: Drawable) {
        for (i in 1..difficulty) {
            val dinamicImgeView = ImageView(context)
            dinamicImgeView.layoutParams = LayoutParams(
                150.toDp(), 150.toDp()
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
        val card = view.resources.getResourceName(view.id)
        when (binding.gameTitle.text.toString()) {
            "Secuencia" -> {
                val intent = Intent(this, Sequence_game::class.java)
                val bTurtle = Bundle()
                var level = "tutorial"


                when (card) {
                    "com.example.coquille:id/card1" -> {
                        level = "level1"
                    }
                    "com.example.coquille:id/card2" -> {
                        level = "level2"
                    }
                    "com.example.coquille:id/card3" -> {
                        level = "level3"
                    }
                    else -> println("Nivel inexistente")
                }

                bTurtle.putString("sequenceLevel", level)
                intent.putExtras(bTurtle)

                startActivity(intent)
            }
            "Memorama" -> {

                val intent = Intent(this, MemoryGameActivity::class.java)
                var bMemoryGame = Bundle()
                var level = 0

                when (card) {
                    "com.example.coquille:id/card1" -> {
                        level = 0
                    }
                    "com.example.coquille:id/card2" -> {
                        level = 1
                    }
                    "com.example.coquille:id/card3" -> {
                        level = 2
                    }
                    else -> println("Nivel inexistente")
                }

                intent.putExtra("level", level)
                startActivity(intent)

            }
            "Las aventuras de Tuga" -> {
                var intent = Intent(this, Turtle::class.java)
                val bTurtle = Bundle()
                var level = "tutorial"


                when (card) {
                    "com.example.coquille:id/card1" -> {
                        level = "tutorial"
                        intent = Intent(this, TurtleTutorial::class.java)
                    }
                    "com.example.coquille:id/card2" -> {
                        level = "level1"
                    }
                    "com.example.coquille:id/card3" -> {
                        level = "level2"
                    }
                    else -> println("Nivel inexistente")
                }

                bTurtle.putString("turtleLevel", level)
                intent.putExtras(bTurtle)

                startActivity(intent)
            }
            "Cuentos asombrosos" -> {
                when (card) {
                    "com.example.coquille:id/card1" -> {
                        if(Utils.getCurrentUser(this).settings.sfx) {
                            val player = MediaPlayer.create(this, R.raw.profile_sound)
                            player.start()
                        }
                        val intent = Intent(this, BookGame::class.java)
                        val nameLevel: String = "El león y el ratón"
                        val b: Bundle = Bundle()
                        b.putString("level", nameLevel)
                        intent.putExtras(b)
                        startActivity(intent)
                    }
                    "com.example.coquille:id/card2" -> {
                        if(Utils.getCurrentUser(this).settings.sfx) {
                            val player = MediaPlayer.create(this, R.raw.profile_sound)
                            player.start()
                        }
                        val intent = Intent(this, BookGame::class.java)
                        val nameLevel: String = "El patito feo"
                        val b: Bundle = Bundle()
                        b.putString("level", nameLevel)
                        intent.putExtras(b)
                        startActivity(intent)
                    }
                    "com.example.coquille:id/card3" -> {
                        if(Utils.getCurrentUser(this).settings.sfx) {
                            val player = MediaPlayer.create(this, R.raw.profile_sound)
                            player.start()
                        }
                        val intent = Intent(this, BookGame::class.java)
                        val nameLevel: String = "El pájaro y la ballena"
                        val b: Bundle = Bundle()
                        b.putString("level", nameLevel)
                        intent.putExtras(b)
                        startActivity(intent)
                    }
                    else -> println("Nivel inexistente")
                }

            }
            "Juego de colores? xd" -> {
                when (card) {
                    "com.example.coquille:id/card1" -> {
                        if(Utils.getCurrentUser(this).settings.sfx) {
                            val player = MediaPlayer.create(this, R.raw.profile_sound)
                            player.start()
                        }
                        val intent = Intent(this, Repeated_figures::class.java)
                        val nameLevel: String = "Dibujo"
                        val b: Bundle = Bundle()
                        b.putString("level", nameLevel)
                        intent.putExtras(b)
                        startActivity(intent)
                    }
                    "com.example.coquille:id/card2" -> {
                        if(Utils.getCurrentUser(this).settings.sfx) {
                            val player = MediaPlayer.create(this, R.raw.profile_sound)
                            player.start()
                        }
                        val intent = Intent(this, Repeated_figuresLvl2::class.java)
                        val nameLevel: String = "Dibujo"
                        val b: Bundle = Bundle()
                        b.putString("level", nameLevel)
                        intent.putExtras(b)
                        startActivity(intent)
                    }
                    "com.example.coquille:id/card3" -> {
                        if(Utils.getCurrentUser(this).settings.sfx) {
                            val player = MediaPlayer.create(this, R.raw.profile_sound)
                            player.start()
                        }
                        val intent = Intent(this, Repeated_figuresLvl3::class.java)
                        val nameLevel: String = "Khe-so :O"
                        val b: Bundle = Bundle()
                        b.putString("level", nameLevel)
                        intent.putExtras(b)
                        startActivity(intent)
                    }
                    else -> println("Nivel inexistente")
                }
            }


        }

    }
}