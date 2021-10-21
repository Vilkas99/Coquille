package com.example.coquille.controllers

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.coquille.R
import com.example.coquille.models.MemoryGame
import com.example.coquille.utils.*


class MemoryGameActivity : AppCompatActivity(){

    private val mySharedPreferences : MySharedPreferences = MySharedPreferences(this)
    private lateinit var pointsView: View
    private var ignoringInput: Boolean = true
    private lateinit var recyclerView: RecyclerView //Vista que mostrará las cartas
    private lateinit var layoutManager : GridLayoutManager //Encargado de ordenar las cartas en una matriz
    private lateinit var heartViews : List<ImageView>
    private lateinit var hearts : HeartSystem
    private lateinit var points : PointsSystem
    private lateinit var adapter : MemoryGameCardAdapter
    private lateinit var gameState : MemoryGame

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_game)

        //Gamestate - Creamos el estado del juego con la dificultad que seleccionó el usuario
        gameState = MemoryGame(intent.getIntExtra("level", 0))

        //Backbutton - Botón que nos hará navegar a la vista previa al juego
        findViewById<ImageView>(R.id.goBack).setOnClickListener { _ -> back() }

        //Hearts system - Conexión con las ImageView que muestran los contenedores de corazón
        //que representan las vidas del usuario
        heartViews = listOf(findViewById(R.id.memory_card_first_heart),
            findViewById(R.id.memory_card_second_heart), findViewById(R.id.memory_card_third_heart))
        hearts = HeartSystem(3, heartViews)

        //Points system - Conexión con la vista que despliega los puntos del usuario
        pointsView = findViewById(R.id.points_frame_layout)
        points = PointsSystem(Utils.getCurrentUser(this).points, pointsView)

        /*RecyclerView - Conexión con la vista donde se desplegarán las cartas
          Se necesita un adapter para indicarle al RecyclerView como crear las cartas que va
          a utilizar y qué atributos y comportamiento tendrán al ser tocadas.
         */
        recyclerView = findViewById(R.id.cardsRecyclerView)
        adapter = MemoryGameCardAdapter(gameState.getCards()) {
                firstView: View, secondView: View, indexOfSelectedCard : Int ->
                this.onCardSelection(firstView, secondView, indexOfSelectedCard)
        }
        recyclerView.adapter = adapter

        /*Layout Manager y decoración para el RecyclerView
          Con base al decorador, el RecyclerView añadirá márgenes entre las cartas que contiene,
          basándose en el número de columnas a usar y los dp entre cartas
         */
        val numberOfColumns = 4
        val pixelsBetweenItems = 50
        layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(GridSpacingItemDecoration(numberOfColumns,
            (pixelsBetweenItems / resources.displayMetrics.density).toInt()))

        //Se muestran primero todas las cartas del juego para que el usuario pueda recordarlas
        Handler(Looper.getMainLooper()).postDelayed({

            for(i in 0 until adapter.itemCount) {
                flipCard(
                    this,
                    recyclerView.getChildViewHolder(recyclerView.getChildAt(i)).itemView.findViewById<View>(
                        R.id.card_back_view
                    ),
                    recyclerView.getChildViewHolder(recyclerView.getChildAt(i)).itemView.findViewById<View>(
                        R.id.card_front_view
                    )
                )
            }

            //Durante este periodo, el usuario no puede interactuar con las cartas
            //La duración de dicho periodo dependerá de la dificultad escogida
            ignoringInput = false}, gameState.getGracePeriod())

    }

    /*
    Función que maneja qué hacer, con base en el estado actual del juego y la entrada del usuario,
    cuando el usuario toca alguna de las cartas.
    Esta función es asignada a cada una de las tarjetas cuando se crean en la GridView

    @param firstView : View -> La cara frontal de la carta.
    @param secondView : View -> La cara trasera de la carta.
    @param indexOfSelectedCard : Int -> Índice de la carta tocada
     */
    private fun onCardSelection(firstView: View, secondView: View, indexOfSelectedCard : Int){

        //Si se acabó el juego, ya no se acepta input del usuario
        if(gameState.isGamedOver()){
            ignoringInput = true
        }

        //Si estamos ignorando la entrada, o el par de la carta tocada ya fue dscubierto o
        //se volvió a tocar la última carta tocada, no hacemos nada y esperamos la siguiente entrada
        if(ignoringInput || gameState.isCardCleared(indexOfSelectedCard) ||
            gameState.getPastSelectedCardIndex() == indexOfSelectedCard){
            return
        }

        //Si no hay una carta seleccionada, la carta que se tocó se vuelve la seleccionada
        if(gameState.getPastSelectedCardIndex() == -1){
            gameState.setCardAsSelected(indexOfSelectedCard)
        }

        //Hacemos la cara frontal de la carta visible
        flipCard(this, firstView, secondView)

        //Preguntamos al estado del juego si la última carta seleccionada y la que acaba de tocar
        //el usuario son la misma
        when(gameState.areCardsAPair(indexOfSelectedCard)){

            //Si no forman un par, vuelvemos a esconder las cartas y actualizamos la vista de vidas.
            0 -> {
                ignoringInput = true
                hearts.reduceHeart()
                Handler(Looper.getMainLooper()).postDelayed({


                    flipCard(this,
                        recyclerView.getChildViewHolder(recyclerView.getChildAt(gameState.pastSelectedCard)).itemView.findViewById<View>(R.id.card_back_view),
                        recyclerView.getChildViewHolder(recyclerView.getChildAt(gameState.pastSelectedCard)).itemView.findViewById<View>(R.id.card_front_view))
                    flipCard(this, secondView, firstView)


                }, 1000)

                //Bloqueamos la entrada del usuario hasta que las animaciones terminen por completo
                //para evitar bugs visuales.
                Handler(Looper.getMainLooper()).postDelayed({ ignoringInput = false }, 1480)

            }

            //Si forman un par o no hay carta con la cual comparar
            // o tocó una carta que ya fue volteada, dejamos las tarjetas como están
            1, 2 -> {}

        }

        if(gameState.isGamedOver()) {
            handleGameOver()
        }

    }

    /*
    Función que se encarga de guardar los puntos que ganó el usuario, reportarle que los ganó
    y guardarlos en los datos de su perfil.
    Además, aquí se llama a las funciones encarga de refrescar el periodo de 24 horas que determina
    el streak del usuario
     */
    private fun handleGameOver(){

        //Se guardan los puntos
        val user = Utils.getCurrentUser(this)
        user.points += gameState.getObtainedPoints()
        mySharedPreferences.editData(user, "currentUser")

        //Actualizamos los temporizadores para ayudar al usuario a mantener la racha
        Utils.setEndStreakWorker(this)
        Utils.setStreakNotification(this)

        //Preparamos el PopUp que reporta los puntos que ganó el usuario
        var bundle = Bundle()
        val intent = Intent(this, Popup::class.java)
        val text = if (gameState.didUserWon()) {
            "Ganaste"
        } else {
            "Perdiste"
        }

        bundle.putString("titlePopup", "Fin del juego\n$text")
        bundle.putString("bodyPopup", "Obtuviste ${gameState.points} puntos")
        bundle.putString("customized", "")
        intent.putExtras(bundle)
        startActivity(intent) //Mostramos el popup

    }

    /*
    Función que ejecuta la animación de girar las cartas y mostrar la otra cara.
    @param context : Context -> Contexto actual
    @param visibleView : View -> La cara que queremos hacer visible.
    @param inVisibleView : View -> La cara que queremos ocultar.
     */
    private fun flipCard(context: Context, visibleView: View, inVisibleView: View) {

        visibleView.visibility = View.VISIBLE

        //Ajustamos la distancia de cámara simulada a la vista
        val scale = context.resources.displayMetrics.density
        val cameraDist = 8000 * scale

        visibleView.cameraDistance = cameraDist
        inVisibleView.cameraDistance = cameraDist

        //Asignamos las animaciones a su vista correspondiente

        val flipOutAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_out_card)
                as AnimatorSet
        flipOutAnimatorSet.setTarget(inVisibleView)

        val flipInAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_in_card)
                as AnimatorSet
        flipInAnimatorSet.setTarget(visibleView)

        flipOutAnimatorSet.start()
        flipInAnimatorSet.start()

        //Ocultamos la vista cuando se acaban las animaciones
        flipInAnimatorSet.doOnEnd { inVisibleView.visibility = View.GONE }

    }

    /*
    Clase que se encarga de realizar el cálculo de la posición para las cartas dentro de su contenedor
     */
    class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int):
        ItemDecoration()
    {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

            val position = parent.getChildAdapterPosition(view) // Posición del item dentro de la RV
            val column = position % spanCount // La columna donde irá el item actual
                outRect.left =
                    spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right =
                    (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // Si no está en la fila de arriba, ponle margen arriba
                    outRect.top = spacing
                }
                outRect.bottom = spacing // Margen de abajo

        }
    }

    // Función para volver a la vista previa al juego,
    // ligada al botón de la esquina superior izquierda
    private fun back(){

        val intent = Intent(this, PreviewGame::class.java)
        val nameGame = "memory"
        val b = Bundle()
        b.putString("indexArray", nameGame)
        intent.putExtras(b)
        startActivity(intent)

    }


}