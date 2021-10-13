package com.example.coquille.controllers.Turtle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.coquille.R
import com.example.coquille.controllers.Turtle.levels.TurtleLevel1_Fragment
import com.example.coquille.controllers.Turtle.levels.TurtleLvl2Fragment
import com.example.coquille.controllers.activity_home
import com.example.coquille.databinding.ActivityTurtleBinding
import com.example.coquille.models.games.Turtle.Level
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
    lateinit var freezeSharkView : View
    lateinit var waterView : View
    lateinit var slowMotionView : View

    //Level fragments
    val level1Fragment = TurtleLevel1_Fragment()
    val level2Fragment = TurtleLvl2Fragment()

    //currentLevel
    lateinit var myLevel : Level

    var padding  = -50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = MySharedPreferences(this)

        //Binding
        binding = ActivityTurtleBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        freezeSharkView = binding.freeze
        val freezeValue = Position(Utils.getId(freezeSharkView), "freezeShark",0,0)
        freezeSharkView.setTag(freezeValue)

        waterView = binding.water
        val waterValue = Position(Utils.getId(waterView), "water",0,0)
        waterView.setTag(waterValue)

        slowMotionView = binding.slowMotion
        val slowValue = Position(Utils.getId(slowMotionView), "slowMotion",0,0)
        slowMotionView.setTag(slowValue)


        chooseLevel()



    }

    //TODO: Vincular los objetos con la interacción del usuario


    fun chooseLevel() {
        var currentLevel = level1Fragment as Fragment
        val bundle =intent.getStringExtra("turtleLevel").toString()

        when(bundle){
            "level1" ->
                currentLevel = level1Fragment

            "level2" ->
                currentLevel = level2Fragment

        }

        supportFragmentManager.beginTransaction().apply {
            replace(binding.turtleLevel.id, currentLevel)
            commit()
        }
    }

    fun setCurrentLevel(level : Level){
        positions = level.positions
        firePositions = level.firePos
        sharkPositions = level.sharkPos
        currentPosView = level.initialPos
        gameState = TurtleGame(level, 0)

        myLevel = level

        timerSequence(level.totalTime as Long,1000, this)
    }


    fun handleInteraction(view: View?){
        val tag = view?.getTag() as Position
        if( tag.state == "position"){
            if (gameState.handleInteraction("move",this, view)) {
                currentPosView.setBackgroundResource(R.drawable.ic_pos_circle)
                Utils.stopAnimation(currentPosView as LottieAnimationView)
                Utils.stopAnimation(binding.wowIcon )

                view.setBackgroundResource(R.drawable.ic_pos_circle)
                Utils.createAnimation(view as LottieAnimationView, R.raw.turtle, 30, padding)

                currentPosView = gameState.currentPosition

                setMovementPosition(positions, false, false)
            }

        } else if (tag.state == "fire" && gameState.playerState == "removeFire"){
            setMovementPosition(firePositions, false, true)
            if (gameState.handleInteraction("removeFire",this, view)){
                firePositions = gameState.firePositions


                view.setBackgroundResource(R.drawable.ic_pos_circle)
                Utils.stopAnimation(view as LottieAnimationView)

                waterView.setBackgroundResource(R.drawable.habilidad_2_lock)
                binding.puntosText.setText(gameState.points.toString())

                randomNice()

            }

        } else if (tag.state == "slowMotion"){
            if (gameState.handleInteraction("slowMotion", this, null)){
                slowMotionView.setBackgroundResource(R.drawable.habilidad_3_lock)

                Utils.stopAnimation(currentPosView as LottieAnimationView)
                Utils.createAnimation(currentPosView as LottieAnimationView, R.raw.turtle_fire, 40, padding)

                setMovementPosition(positions, true, false)
            }
        } else if (tag.state == "freezeShark"){
            if (gameState.handleInteraction("freezeShark",this, null)){
                freezeSharkView.setBackgroundResource(R.drawable.habilidad_1_lock)
                sharkPositions.forEach{ shark ->
                    Utils.stopAnimation(shark as LottieAnimationView)
                    Utils.createAnimation(shark as LottieAnimationView, R.raw.shark_freeze, 50, padding)
                }

                randomNice()
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





    fun timerSequence(time: Long, intervalo: Long, context : Context){
        var sharkTime = 0
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
                    gameState.currentTime    += 1

                    sharkTime += 1
                    fireTime += 1

                    if (sharkTime >= myLevel.timeForSharks && (gameState.currentState != "freeze" && gameState.currentState != "slow") ){
                        setSharkPositions()
                        sharkTime = 0
                    }

                    if (fireTime >= myLevel.timeForFire && (gameState.currentState != "freeze" && gameState.currentState != "slow")){
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

    fun routeToHomeButton(view : View) {
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
            freezeSharkView.setBackgroundResource(R.drawable.habilidad_1)
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
                    Utils.stopAnimation(shark as LottieAnimationView)
                    Utils.createAnimation(shark as LottieAnimationView, R.raw.shark, 70, padding)
                }
                gameState.currentState = "normal"
            }
        } else if (gameState.currentState == "slow"){
            if (gameState.slowDuration == 0){
                Utils.stopAnimation(currentPosView as LottieAnimationView)
                Utils.createAnimation(currentPosView as LottieAnimationView, R.raw.turtle, 70, padding)
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

        Utils.createAnimation(newPossiblePosition as LottieAnimationView, R.raw.fire_animation, 30, padding)
        firePositions.add(newPossiblePosition)
        gameState.firePositions = firePositions
    }

    fun randomNice(){
        val nextIndexRandom = (0 until 10).random()
        if (nextIndexRandom > 3){
            val randomIndexAnimation = (0 until 2).random()
            when (randomIndexAnimation){
                0 -> Utils.createAnimation(binding.wowIcon, R.raw.nice_emoji, 1, 460)
                1 -> Utils.createAnimation(binding.wowIcon, R.raw.epic_emoji, 1, 460)
                2 -> Utils.createAnimation(binding.wowIcon, R.raw.love_emoji, 1, 460)
            }
        }
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
                Utils.stopAnimation(view as LottieAnimationView)

                val newSharkData = newPossibleSharkPos.getTag() as Position
                newSharkData.state = "monkey"
                Utils.createAnimation(newPossibleSharkPos as LottieAnimationView, R.raw.shark, 40, padding)

                newSharks.add(newPossibleSharkPos)
            }
            sharkPositions = newSharks
            gameState.sharkPositions = newSharks
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