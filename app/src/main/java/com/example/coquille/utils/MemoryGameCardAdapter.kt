package com.example.coquille.utils


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.coquille.R
import com.example.coquille.models.MemoryGameCard

/*
Adapter customizado que contendrá los objetos de tipo CardHolder y será la interfaz entre ellos
y el RecyclerView.
@private member cards : Array<MemoryGameCard> -> Tarjetas que se desplegarán.
@private member listener : lambda -> Función que se asignará como click listener a cada uno de los
items del RecyclerView

 */
class MemoryGameCardAdapter(private val cards: Array<MemoryGameCard>, var listener : (View, View, Int) -> Unit)
    : RecyclerView.Adapter<MemoryGameCardAdapter.CardHolder>(){

    /*
     * ViewHolder customizado. Representa una sola carta contenida dentro del RecyclerView.
     * Guarda una referencia a las dos views que componen a una tarjeta y a su respectiva imagen
     * @param view : View -> view previamente inflada que representa el layout de la tarjeta
     */

    class CardHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardFront : View = view.findViewById(R.id.card_front_view)
        val cardBack : View = view.findViewById(R.id.card_back_view)
        val imageForCard : ImageView = view.findViewById(R.id.imageForCard)

    }

    /*
    Función sobreescrita que se encarga de inicializar los items que se integrarán al RecyclerView
    @param parent : ViewGroup -> Referencia al RecyclerView que va a contener el nuevo CardHolder
    @param viewType : Int -> Indicador del tipo de vista que se agregará (No aplica)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {

        //Crear view con base en el layout de la tarjeta
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.memory_game_card, parent,false)

        return CardHolder(view)
    }

    //Función sobreescrita que le dice al RecyclerView cuántos items tendrá que desplegar
    override fun getItemCount() = cards.size

    //Función sobreescrita que asigna la función lambda a los CardHolders ya creados y su respectiva imagen
    override fun onBindViewHolder(holder: CardHolder, position: Int) {

        holder.imageForCard.setImageResource(cards[position].getImageId())
        holder.itemView.setOnClickListener{listener(holder.cardFront, holder.cardBack, position)}
    }

}