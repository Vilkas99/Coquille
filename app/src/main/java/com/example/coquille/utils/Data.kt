package com.example.coquille.utils
import com.example.coquille.R
import com.example.coquille.models.CardContent
import com.example.coquille.models.Collectable

object Data {

    val cardCuentos1 = CardContent("Prueba de la descripción ahhhhh D: CUENTOS")
    val cardCuentos2 = CardContent("Hansel & CUENTOS", 3, R.drawable.ic_fire)
    val cardCuentos3 = CardContent("Los 3 cochinitos", 2, R.drawable.ic_colorful_logo)
    val cardCuentos4 = CardContent("Prueba de juego aaa", 5, R.drawable.ic_fire)

    val cardSecuencia1 = CardContent("Prueba de la descripción ahhhhh D: SECUENCIA")
    val cardSecuencia2 = CardContent("Hansel & SECUENCIA", 3, R.drawable.ic_fire)
    val cardSecuencia3 = CardContent("Los 3 cochinitos", 2, R.drawable.ic_colorful_logo)
    val cardSecuencia4 = CardContent("Prueba de juego aaa", 5, R.drawable.ic_fire)

    val cardMemoria1 = CardContent("Prueba de la descripción ahhhhh D: MEMORIA")
    val cardMemoria2 = CardContent("Hansel & MEMORIA", 3, R.drawable.ic_candyhouse)
    val cardMemoria3 = CardContent("Los 3 cochinitos", 2, R.drawable.ic_colorful_logo)
    val cardMemoria4 = CardContent("Prueba de juego aaa", 5, R.drawable.ic_fire)


    val cardTortuga1 = CardContent("Prueba de la descripción ahhhhh D: TORTUGA")
    val cardTortuga2 = CardContent("Hansel & TORTUGA", 3, R.drawable.ic_candyhouse)
    val cardTortuga3 = CardContent("Los 3 cochinitos", 2, R.drawable.ic_colorful_logo)
    val cardTortuga4 = CardContent("Prueba de juego aaa", 5, R.drawable.ic_fire)


    val cardDibuja1 = CardContent("Prueba de la descripción ahhhhh D: DIBUJO")
    val cardDibuja2 = CardContent("Hansel & DIBUJO", 3, R.drawable.ic_candyhouse)
    val cardDibuja3 = CardContent("Los 3 cochinitos", 2, R.drawable.ic_colorful_logo)
    val cardDibuja4 = CardContent("Prueba de juego aaa", 5, R.drawable.ic_fire)

    val cardsCuentos = arrayOf(cardCuentos1, cardCuentos2, cardCuentos3, cardCuentos4)
    val cardsDibuja = arrayOf(cardDibuja1, cardDibuja2, cardDibuja3, cardDibuja4)
    val cardsMemoria = arrayOf(cardMemoria1, cardMemoria2,cardMemoria3, cardMemoria4)
    val cardsTortuga = arrayOf(cardTortuga1, cardTortuga2, cardTortuga3, cardTortuga4)
    val cardsSecuencia = arrayOf(cardSecuencia1, cardSecuencia2, cardSecuencia3, cardSecuencia4)

    fun getCard(gameName: String): Array<CardContent>{

        val dummy = arrayOf<CardContent>(CardContent("", 0))


        when(gameName){
            "Secuencia" -> return(cardsSecuencia)
            "Dibuja" -> return(cardsDibuja)
            "Memoria" -> return(cardsMemoria)
            "Tortuga" -> return(cardsTortuga)
            "Cuentos" -> return(cardsCuentos)
        }

        return dummy
    }

}