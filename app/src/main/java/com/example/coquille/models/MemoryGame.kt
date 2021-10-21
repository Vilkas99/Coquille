package com.example.coquille.models

import com.example.coquille.R
import com.example.coquille.models.abstracts.Game

class MemoryGame(private val difficulty: Int = 0) : Game("Memorama", "dummy.jpg"){

    private lateinit var cards : Array<MemoryGameCard>
    private val cardImages : Array<Int> = arrayOf(R.drawable.ic_unicorn, R.drawable.ic_spider, R.drawable.ic_fairy, R.drawable.ic_dragon, R.drawable.ic_centaur,
    R.drawable.ic_food, R.drawable.ic_spell_book, R.drawable.ic_castle, R.drawable.ic_bow, R.drawable.ic_map, R.drawable.ic_dragon_egg, R.drawable.ic_potion,
    R.drawable.ic_pouch, R.drawable.ic_treant_tree, R.drawable.ic_crystal, R.drawable.ic_scroll_paper, R.drawable.ic_armor)
    var lastSelectedCard = -1
    var pastSelectedCard = -1
    var clearedPairs = 0
    var hearts = 3
    var points = 0
    private val pointsPerPair = 12 + (5*difficulty)
    var userWon = false

    init {
        setCardsForGame()
    }

    private fun setCardsForGame(){

        cardImages.shuffle()
        cards = Array(12 + (difficulty*4)) { MemoryGameCard() }
        var i = 0
        var j = 0

        while(i < cards.size){

            cards[i].setImageId(cardImages[j])
            cards[i + 1].setImageId(cardImages[j])
            i += 2
            j += 1

        }

        cards.shuffle()

    }

    fun getCards() : Array<MemoryGameCard>{
        return cards
    }


    fun areCardsAPair(currentlySelected : Int) : Int {

        /*
        0 - No son iguales
        1 - Son iguales
        2 - Al manos una de las tarjetas ya está volteada y adivinada
        */

        if(cards[lastSelectedCard].isCleared() || cards[currentlySelected].isCleared() || lastSelectedCard == currentlySelected){
            return 2
        }

        if(lastSelectedCard != -1 && cards[lastSelectedCard].getImageId() == cards[currentlySelected].getImageId()){
            cards[lastSelectedCard].setASCleared()
            cards[currentlySelected].setASCleared()
            clearedPairs++
            points += pointsPerPair
            lastSelectedCard = -1
            return 1
        }

        pastSelectedCard = lastSelectedCard
        lastSelectedCard = -1
        hearts--
        return 0

    }

    fun setCardAsSelected(index : Int){
        lastSelectedCard = index
    }

    fun getPastSelectedCardIndex() : Int {
        return lastSelectedCard
    }

    fun getObtainedPoints() : Int{
        return points
    }

    fun getGracePeriod() : Long{
        return 6500 - (difficulty.toLong() * 750)
    }

    fun isGamedOver() : Boolean {

        if(clearedPairs == cards.size/2) {
            userWon = true
            return true
        }

        if(hearts == 0){
            return true
        }

        return false

    }

    fun didUserWon() : Boolean {
        return userWon
    }


}