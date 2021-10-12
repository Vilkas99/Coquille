package com.example.coquille.controllers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.core.view.forEach
import com.airbnb.lottie.LottieAnimationView
import com.example.coquille.R
import com.example.coquille.databinding.ActivityTurtleBinding
import com.example.coquille.models.games.Turtle.Position
import com.example.coquille.models.games.Turtle.TurtleGame
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils

class Turtle : AppCompatActivity() {

    lateinit var sharedPref: MySharedPreferences

    lateinit var gameState: TurtleGame
    lateinit var binding: ActivityTurtleBinding

    //Positions
    lateinit var positions: MutableList<View>
    lateinit var currentPosView: View

    //Obstacles
    lateinit var firePositions: MutableList<View>
    lateinit var sharkPositions: MutableList<View>

    //Abilities
    lateinit var freezeMonkeyView : View
    lateinit var waterView : View
    lateinit var slowMotionView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = MySharedPreferences(this)

        //Binding
        binding = ActivityTurtleBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        //Current Pos
        positions = mutableListOf<View>()
        currentPosView = binding.circuloOrigin
        var currentPos = Position(Utils.getId(currentPosView), "player", 0, 1)
        currentPosView.setTag(currentPos)

        positions.add(currentPosView)
        createAnimation(binding.circuloOrigin, R.raw.turtle, 30)

        //Get FireElements

        val fire1 = Position(Utils.getId(binding.circulo7), "fire", 3, 1 )
        binding.circulo7.setTag(fire1)
        positions.add(binding.circulo7)
        createAnimation(binding.circulo7, R.raw.fire_animation, 30)

        val fire2 = Position(Utils.getId(binding.circulo9), "fire", 2, 0)
        binding.circulo9.setTag(fire2)
        positions.add(binding.circulo9)
        createAnimation(binding.circulo9, R.raw.fire_animation, 30)

        val fire3 = Position(Utils.getId(binding.circulo1), "fire", 0, 0)
        binding.circulo1.setTag(fire3)
        positions.add(binding.circulo1)
        createAnimation(binding.circulo1, R.raw.fire_animation, 30)

        firePositions = mutableListOf(binding.circulo1, binding.circulo9, binding.circulo7)

        //Get monkeyPositions
        val shark1 = Position(Utils.getId(binding.circulo8), "monkey", 3, 0)
        binding.circulo8.setTag(shark1)
        positions.add(binding.circulo8)
        createAnimation(binding.circulo8, R.raw.shark, 30)

        val shark2 = Position(Utils.getId(binding.circulo2), "monkey", 0, 2)
        binding.circulo2.setTag(shark2)
        positions.add(binding.circulo2)
        createAnimation(binding.circulo2, R.raw.shark,30)

        sharkPositions = mutableListOf(binding.circulo8, binding.circulo2)

        //Get all elements from XML
        createElements(binding.mainLayout)

        gameState = TurtleGame(currentPosView,  0, 120, firePositions, sharkPositions)
        timerSequence(50000,1000, this)
    }

    //TODO: Vincular los objetos con la interacción del usuario


    fun handleInteraction(view: View?){
        val tag = view?.getTag() as Position
        if( tag.state == "position"){
            if (gameState.handleInteraction("move",this, view)) {
                currentPosView.setBackgroundResource(R.drawable.ic_pos_circle)
                stopAnimation(currentPosView as LottieAnimationView)

                view.setBackgroundResource(R.drawable.ic_pos_circle)
                createAnimation(view as LottieAnimationView, R.raw.turtle, 30)

                currentPosView = gameState.currentPosition

                setMovementPosition(positions, false, false)
            }

        } else if (tag.state == "fire" && gameState.playerState == "removeFire"){
            setMovementPosition(firePositions, false, true)
            if (gameState.handleInteraction("removeFire",this, view)){
                firePositions = gameState.firePositions

                view.setBackgroundResource(R.drawable.ic_pos_circle)
                stopAnimation(view as LottieAnimationView)

                waterView.setBackgroundResource(R.drawable.habilidad_2_lock)
                binding.puntosText.setText(gameState.points.toString())

            }

        } else if (tag.state == "slowMotion"){
            if (gameState.handleInteraction("slowMotion", this, null)){
                slowMotionView.setBackgroundResource(R.drawable.habilidad_3_lock)

                stopAnimation(currentPosView as LottieAnimationView)
                createAnimation(currentPosView as LottieAnimationView, R.raw.turtle_fire, 40)

                setMovementPosition(positions, true, false)
            }
        } else if (tag.state == "freezeMonkey"){
            if (gameState.handleInteraction("freezeMonkey",this, null)){
                freezeMonkeyView.setBackgroundResource(R.drawable.habilidad_1_lock)
                sharkPositions.forEach{ shark ->
                    stopAnimation(shark as LottieAnimationView)
                    createAnimation(shark as LottieAnimationView, R.raw.shark_freeze, 50)

                }
            }
        } else if (tag.state == "player"){
            gameState.playerState = "moving"
            val possiblePositions =  gameState.returnNeighbour(positions)
            setMovementPosition(possiblePositions, true, false)
        } else if(tag.state == "water"){
            gameState.playerState = "removeFire"
            setMovementPosition(firePositions, true, true)
        }
    }

    fun createAnimation(view : LottieAnimationView, animation: Int, repeatCount : Int){
        view.setAnimation(animation)
        view.playAnimation()
        view.setPadding(-70, -70, -70, -70)
        view.repeatCount = repeatCount;
    }

    fun stopAnimation(view: LottieAnimationView){
        view.repeatCount = 0
        view.setImageDrawable(null)
    }

    fun createElements(myLayout: ConstraintLayout) {
        myLayout.forEach { view ->
            val id = Utils.getId(view)

            if (id == "com.example.coquille:id/water") {
                waterView = view
                val value = Position(Utils.getId(waterView), "water",0,0)
                waterView.setTag(value)
            }
            else if (id == "com.example.coquille:id/freeze"){
                freezeMonkeyView = view
                val value = Position(Utils.getId(freezeMonkeyView), "freezeMonkey",0,0)
                freezeMonkeyView.setTag(value)
            }
            else if (id == "com.example.coquille:id/slowMotion"){
                slowMotionView = view
                val value = Position(Utils.getId(slowMotionView), "slowMotion",0,0)
                slowMotionView.setTag(value)
            }
            else {
                if (!firePositions.contains(view) && !sharkPositions.contains(view) && currentPosView != view) {
                    if (view !is Guideline && view !is CardView && view !is TextView ){
                        val (row, col) = getRowAndCol(view.getTag() as String)
                        val position = Position(id, "position", row, col)
                        view.setTag(position)
                        positions.add(view)
                    }

                }
            }
        }
    }

    fun getRowAndCol(tag : String) : Pair<Int, Int>{
        val row = (tag[0].toInt() - '0'.toInt())
        val col = (tag[2].toInt() - '0'.toInt())

        val result = Pair(row, col)

        return result
    }

    fun timerSequence(time: Long, intervalo: Long, context : Context){
        var monkeyTime = 0
        var fireTime = 0
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                    evaluateCoolDown()
                    evaluateGameState()

                    if (gameState.currentState == "won" || gameState.currentState == "finished"){
                        val currentUserUpdated = Utils.getCurrentUser(context)
                        currentUserUpdated.points += gameState.points
                        sharedPref.editData(currentUserUpdated, "currentUser")
                        routeToHome()
                        cancel()
                    }

                    gameState.removeCoolDown()
                    gameState.totalTime += 1

                    monkeyTime += 1
                    fireTime += 1

                    if (monkeyTime >= 5 && (gameState.currentState != "freeze" && gameState.currentState != "slow") ){
                        setSharkPositions()
                        monkeyTime = 0
                    }

                    if (fireTime >= 15 && (gameState.currentState != "freeze" && gameState.currentState != "slow")){
                        setFirePositions()
                        fireTime = 0
                    }

                    binding.tiempoText.setText((p0 / 1000).toString())
                    gameState.evaluateGameState(context, false)

            }

            override fun onFinish() {
                gameState.evaluateGameState(context, true)
            }
        }.start()
    }

    fun routeToHome() {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }

    fun evaluateCoolDown(){
        if (gameState.coolDownWater > 0) binding.cooldownWater.setText(gameState.coolDownWater.toString())
        else {
            binding.cooldownWater.text = ""
            waterView.setBackgroundResource(R.drawable.habilidad_2)
        }

        if (gameState.coolDownFreezeMonkey > 0) binding.cooldownFreeze.setText(gameState.coolDownFreezeMonkey.toString())
        else {
            binding.cooldownFreeze.text = ""
            freezeMonkeyView.setBackgroundResource(R.drawable.habilidad_1)
        }

        if (gameState.coolDownSlowMotion > 0) binding.cooldownSlowMotion.setText(gameState.coolDownSlowMotion.toString())
        else {
            binding.cooldownSlowMotion.text = ""
            slowMotionView.setBackgroundResource(R.drawable.habilidad_3)
        }
    }

    fun evaluateGameState(){
        if (gameState.currentState == "freeze"){
            if (gameState.freezeDuration == 0){
                sharkPositions.forEach{ shark ->
                    stopAnimation(shark as LottieAnimationView)
                    createAnimation(shark as LottieAnimationView, R.raw.shark, 70)
                }
                gameState.currentState = "normal"
            }
        } else if (gameState.currentState == "slow"){
            if (gameState.slowDuration == 0){
                stopAnimation(currentPosView as LottieAnimationView)
                createAnimation(currentPosView as LottieAnimationView, R.raw.turtle, 70)
                gameState.currentState = "normal"
            }
        }
    }

    fun setFirePositions(){
        var newPossiblePosition: View
        do {
            val nextIndexRandom = (0 until positions.size).random()
            newPossiblePosition = positions[nextIndexRandom]
        } while ( newPossiblePosition in firePositions || newPossiblePosition in sharkPositions || newPossiblePosition == currentPosView)

        var data = newPossiblePosition.getTag() as Position
        data.state = "fire"

        createAnimation(newPossiblePosition as LottieAnimationView, R.raw.fire_animation, 30)
        firePositions.add(newPossiblePosition)
        gameState.firePositions = firePositions
    }

    fun setSharkPositions(){
        if(gameState.currentState != "freeze"){
            var newSharks = mutableListOf<View>()
            sharkPositions.forEach{ view ->
                var newPossibleSharkPos: View
                do {
                    val nextIndexRandom = (0 until positions.size).random()
                    newPossibleSharkPos = positions[nextIndexRandom]
                } while ( newPossibleSharkPos in firePositions || newPossibleSharkPos in sharkPositions || newPossibleSharkPos == currentPosView)

                val originalData = view.getTag() as Position
                originalData.state = "position"
                stopAnimation(view as LottieAnimationView)

                val newSharkData = newPossibleSharkPos.getTag() as Position
                newSharkData.state = "monkey"
                createAnimation(newPossibleSharkPos as LottieAnimationView, R.raw.shark, 40)

                newSharks.add(newPossibleSharkPos)
            }
            sharkPositions = newSharks
            gameState.monkeyPositions = newSharks
        }

    }

    fun setMovementPosition(positions : MutableList<View>, nextPos : Boolean, fire: Boolean){
            positions.forEach{position ->
                if(positionEvaluation(position) || fire)
                    if (nextPos)
                        position.setBackgroundResource(R.drawable.ic_next_pos_circle)
                    else
                        position.setBackgroundResource(R.drawable.ic_pos_circle)
            }
    }


    fun positionEvaluation(position: View) : Boolean{
        return !firePositions.contains(position) && !sharkPositions.contains(position) && currentPosView != position
    }

}