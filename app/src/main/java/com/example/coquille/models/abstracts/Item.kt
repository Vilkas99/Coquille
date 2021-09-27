package com.example.coquille.models.abstracts

abstract class Item constructor(name: String, itemIcon: String, description: String, coolDown : Int)  {
    val itemName = name
    val itemIcon = itemIcon
    val description = description
    val coolDown = coolDown

    abstract fun OnUse() //Needs to be Overwritten

}