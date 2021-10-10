package com.example.coquille.utils


import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView
import com.example.coquille.R
import com.example.coquille.models.MemoryGameCard


class MemoryGameViewAdapter(private val imagesForCards: Array<MemoryGameCard>, var listener : (View, View, Int) -> Unit) : RecyclerView.Adapter<MemoryGameViewAdapter.CardHolder>(){

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    class CardHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardFront : View = view.findViewById(R.id.card_front_view)
        val cardBack : View = view.findViewById(R.id.card_back_view)
        val imageForCard : ImageView = view.findViewById(R.id.imageForCard)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.memory_game_card, parent,false) //Crear view con base en el layout de la tarjeta

        return CardHolder(view)
    }

    override fun getItemCount() = imagesForCards.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {

        holder.imageForCard.setImageResource(imagesForCards[position].getImageId())
        holder.itemView.setOnClickListener{listener(holder.cardFront, holder.cardBack, position)}
    }

}