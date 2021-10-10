package com.example.coquille.models



class MemoryGameCard(private var imageId: Int = 0, private var flipped: Boolean = false,
                     private var selected: Boolean = false) {

    private var cleared = false

    fun setImageId(inIconId : Int){
        imageId = inIconId
    }

    fun setFlipped(inFlipped : Boolean){
        flipped = inFlipped
    }

    fun setAsSelected(inSelected : Boolean){
        selected = inSelected
    }

    fun getImageId() : Int {
        return imageId
    }

    fun getFlipped() : Boolean{
        return flipped
    }

    fun isSelected() : Boolean{
        return selected
    }

    fun isCleared() : Boolean{
        return cleared
    }

    fun setASCleared() {
        cleared = true
    }

}
