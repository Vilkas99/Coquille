package com.example.coquille.models

//Clase que representa una carta del juego de Memoria
class MemoryGameCard(private var imageId: Int = 0) {

    //Estado de la carta. cleared = true cuando la pareja de cartas ha sido descubierta
    private var cleared = false

    //Setter del id de la imágen que tendrá la carta al frente
    fun setImageId(inIconId : Int){
        imageId = inIconId
    }

    //Getter del id de la imágen que tendrá la carta al frente
    fun getImageId() : Int {
        return imageId
    }

    //Getter para el estado de la carta
    fun isCleared() : Boolean{
        return cleared
    }

    //Función que hace que esta carta cambie su estado cuando su par sea encontrado
    fun setAsCleared() {
        cleared = true
    }

}
