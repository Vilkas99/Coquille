package com.example.coquille.controllers

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.coquille.R
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils
import com.example.coquille.utils.PointsSystem
import com.example.coquille.utils.HeartSystem
import com.example.coquille.models.MemoryGame
import com.example.coquille.utils.MemoryGameViewAdapter




class MemoryGameActivity : AppCompatActivity(){

    private val mySharedPreferences : MySharedPreferences = MySharedPreferences(this)
    private lateinit var pointsView: View
    private var ignoringInput: Boolean = true
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager : GridLayoutManager
    private lateinit var heartViews : List<ImageView>
    private lateinit var hearts : HeartSystem
    private lateinit var points : PointsSystem
    private lateinit var adapter : MemoryGameViewAdapter
    private var gameState : MemoryGame = MemoryGame()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_game)

        //Hearts system
        heartViews = listOf(findViewById(R.id.memory_card_first_heart), findViewById(R.id.memory_card_second_heart), findViewById(R.id.memory_card_third_heart))
        hearts = HeartSystem(3, heartViews)

        //Points system
        pointsView = findViewById(R.id.points_frame_layout)
        points = PointsSystem(Utils.getCurrentUser(this).points, pointsView)

        //RecyclerView and it's respective adapter
        recyclerView = findViewById(R.id.cardsRecyclerView)
        adapter = MemoryGameViewAdapter(gameState.getCards()){firstView: View, secondView: View, indexOfSelectedCard : Int -> this.onCardSelection(firstView, secondView, indexOfSelectedCard)}
        recyclerView.adapter = adapter

        //Layout Manager and decoration for RecyclerView
        val numberOfColumns = 4
        val pixelsBetweenItems = 50
        layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(GridSpacingItemDecoration(numberOfColumns, (pixelsBetweenItems / resources.displayMetrics.density).toInt(), true))

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

            ignoringInput = false}, 3000)


    }

    private fun onCardSelection(firstView: View, secondView: View, indexOfSelectedCard : Int){

        if(gameState.isGamedOver()){
            ignoringInput = true
        }

        if(ignoringInput || gameState.getCards()[indexOfSelectedCard].isCleared() || gameState.getPastSelectedCardIndex() == indexOfSelectedCard){
            return
        }

        if(gameState.getPastSelectedCardIndex() == -1){
            gameState.setCardAsSelected(indexOfSelectedCard)

        }

        if(firstView.visibility == View.GONE){
            flipCard(this, firstView, secondView)
        }
        else{
            flipCard(this, secondView, firstView)
        }


        when(gameState.areCardsAPair(indexOfSelectedCard)){

            0 -> {
                ignoringInput = true
                hearts.reduceHeart()
                Handler(Looper.getMainLooper()).postDelayed({


                    flipCard(this,
                        recyclerView.getChildViewHolder(recyclerView.getChildAt(gameState.pastSelectedCard)).itemView.findViewById<View>(R.id.card_back_view),
                        recyclerView.getChildViewHolder(recyclerView.getChildAt(gameState.pastSelectedCard)).itemView.findViewById<View>(R.id.card_front_view))
                    flipCard(this, secondView, firstView)


                }, 1000)

                Handler(Looper.getMainLooper()).postDelayed({ ignoringInput = false }, 1480)

            }

            1, 2 -> {}

        }

        if(gameState.isGamedOver()) {

            Log.d("obtainedPoints", gameState.getObtainedPoints().toString())
            val user = Utils.getCurrentUser(this)
            user.points += gameState.getObtainedPoints()
            mySharedPreferences.editData(user, "currentUser")

            val text = if (gameState.didUserWon()) {
                "¡Ganaste!"
            } else {
                "Perdiste xd"
            }
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }



    }

    private fun flipCard(context: Context, visibleView: View, inVisibleView: View) {

        visibleView.visibility = View.VISIBLE

        val scale = context.resources.displayMetrics.density
        val cameraDist = 8000 * scale

        visibleView.cameraDistance = cameraDist
        inVisibleView.cameraDistance = cameraDist

        val flipOutAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_out_card) as AnimatorSet
        flipOutAnimatorSet.setTarget(inVisibleView)

        val flipInAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_in_card) as AnimatorSet
        flipInAnimatorSet.setTarget(visibleView)

        flipOutAnimatorSet.start()
        flipInAnimatorSet.start()

        flipInAnimatorSet.doOnEnd { inVisibleView.visibility = View.GONE }

    }


    class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean):
        ItemDecoration()
    {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column
            if (includeEdge) {
                outRect.left =
                    spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right =
                    (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right =
                    spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }


}