package com.example.coquille.models

import com.example.coquille.R
import com.example.coquille.models.abstracts.Game

//Clase que modela el estado de la partida para el juego de Memorama
class MemoryGame(private val difficulty: Int = 0) :
    Game("Memorama", "dummy.jpg"){

    private lateinit var cards : Array<MemoryGameCard> //Tarjetas con las que se jugarán
    private val cardImages : Array<Int> = arrayOf(R.drawable.ic_unicorn, R.drawable.ic_spider,
        R.drawable.ic_fairy, R.drawable.ic_flying_pink_dragon, R.drawable.ic_centaur,
        R.drawable.ic_food, R.drawable.ic_spell_book, R.drawable.ic_castle, R.drawable.ic_bow,
        R.drawable.ic_map, R.drawable.ic_dragon_egg, R.drawable.ic_potion,
        R.drawable.ic_pouch, R.drawable.ic_treant_tree, R.drawable.ic_crystal,
        R.drawable.ic_scroll_paper, R.drawable.ic_armor) //Posibles imágenes para las cartas

    var lastSelectedCard = -1 //Carta seleccionada en el momento
    var pastSelectedCard = -1 //Backup de lastselectedcard
    var clearedPairs = 0
    var hearts = 3
    var points = 0
    private val pointsPerPair = 12 + (5*difficulty)

    var userWon = false //Booleano que representa si el usuario ganó o no la partida

    //Inicializador. Cuando instanciemos un objeto de MemoryGame, tenemos que hacer el setup
    //de las cartas del juego
    init {
        setCardsForGame()
    }

    /*
    Función que se encarga de:
    a) Crear las cartas con las que se jugará. La cantidad depende de la dificultad seleccionada
    b) Asignar una misma imagen a un par de cartas
    c) Barajar las cartas
     */
    private fun setCardsForGame(){

        cardImages.shuffle()
        cards = Array(12 + (difficulty*4)) { MemoryGameCard() } //a
        var i = 0
        var j = 0

        while(i < cards.size){

            cards[i].setImageId(cardImages[j]) //b
            cards[i + 1].setImageId(cardImages[j])
            i += 2
            j += 1

        }

        cards.shuffle() //c

    }

    //Getter para las tarjetas ya creadas, usado para darle los items que desplegar al RecyclerView
    fun getCards() : Array<MemoryGameCard>{
        return cards
    }

    /*
    Función que comprueba si la carta de índice currentlySelected es del mismo tipo que la última
    carta que se seleccionó.
    @param currentlySelected : Int -> Índice de la carta que compararemos con la última que tenemos
    registrada como seleccionada
     */
    fun areCardsAPair(currentlySelected : Int) : Int {

        /*
        0 - No son iguales
        1 - Son iguales
        2 - Al manos una de las tarjetas ya está volteada y adivinada
        */

        if(cards[lastSelectedCard].isCleared() || cards[currentlySelected].isCleared() ||
            lastSelectedCard == currentlySelected){
            return 2
        }

        if(lastSelectedCard != -1 &&
            cards[lastSelectedCard].getImageId() == cards[currentlySelected].getImageId()){

            cards[lastSelectedCard].setAsCleared()
            cards[currentlySelected].setAsCleared()
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

    //Setter que asigna el índice de la última carta que tocó el usuario a la variable lastSelectedCard
    fun setCardAsSelected(index : Int){
        lastSelectedCard = index
    }

    //Getter para la última carta que tocó el usuario
    fun getPastSelectedCardIndex() : Int {
        return lastSelectedCard
    }

    //Getter de los puntos totales que se obtuvieron tras el gameover
    fun getObtainedPoints() : Int{
        return points
    }

    //Getter de la longitud en ms del periodo que tiene el usuario
    //para ver las cartas antes de que estas se oculten
    fun getGracePeriod() : Long{
        return 6500 - (difficulty.toLong() * 750)
    }

    //Función que regresa un booleano que indica si el par correspondiente a la tarjeta en el índice
    //index del arreglo de cartas ya fue descubierto
    fun isCardCleared(index : Int) : Boolean{
        return cards[index].isCleared()
    }

    /*Función que regresa un booleano que indica si la partida actual ya acabó.
      El juego acaba cuando:
      a) Ya se descubrieron todos los pares de cartas. En este caso, el usuario ganó la partida.
      b) Se agotaron las vidas del usuario. En este caso, el usuario perdió la partida.

      Si no se cumplen ninguno de los dos casos, el juego no ha acabado.
    */
    fun isGamedOver() : Boolean {

        //a
        if(clearedPairs == cards.size/2) {
            userWon = true
            return true
        }

        //b
        if(hearts == 0){
            return true
        }

        return false

    }

    //Getter del booleano que representa si el usuario ganó o no o la partida
    fun didUserWon() : Boolean {
        return userWon
    }


}