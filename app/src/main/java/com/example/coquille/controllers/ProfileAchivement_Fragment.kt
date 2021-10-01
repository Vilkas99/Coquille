package com.example.coquille.controllers

import android.app.AlertDialog
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coquille.R
import com.example.coquille.models.User
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils


class ProfileAchivement_Fragment: Fragment(R.layout.fragment_profile_achivement_) {

    lateinit var sharedPreferences: MySharedPreferences

    lateinit var primerLogro : ImageView
    lateinit var segundoLogro : ImageView

    lateinit var primerLogroTitulo: TextView
    lateinit var segundoLogroTitulo: TextView

    lateinit var primerLogroCosto: TextView
    lateinit var segundoLogroCosto: TextView

    lateinit var usuario : User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile_achivement_, container, false)

        sharedPreferences = MySharedPreferences(requireContext())

        primerLogroTitulo = rootView.findViewById(R.id.logro1Titulo)
        segundoLogroTitulo = rootView.findViewById(R.id.logro2Titulo)

        primerLogro = rootView.findViewById(R.id.logro1)
        segundoLogro = rootView.findViewById(R.id.logro2)

        primerLogroCosto = rootView.findViewById(R.id.logro1Costo)
        segundoLogroCosto = rootView.findViewById(R.id.logro2Costo)

        usuario = Utils.getCurrentUser(requireContext())

        primerLogro.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (lookForCollectable(primerLogroTitulo.text.toString())){
                    updateAvatarConfirmation(R.drawable.ic_profile_pic_orc)
                } else {
                    val costo = Integer.parseInt(primerLogroCosto.text.toString())
                    val titulo = primerLogroTitulo.text.toString()
                    acquireCollectable(titulo, costo, R.drawable.ic_profile_pic_orc)
                }

            }
        })

        /*segundoLogro.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                acquireCollectable()
            }
        })*/

        return rootView
    }


    fun acquireCollectable(collectableName: String, collectableCost: Int, collectableID: Int){
        if (usuario.points >= collectableCost){
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("¿Estás segur@ de adquirir este avatar?")
                .setCancelable(false)
                .setPositiveButton("Sí"){dialog, id ->
                    usuario.points -= collectableCost
                    val collectable = Utils.getCollectable(requireContext(), collectableName)

                    if (collectable.title == "Dummy" ){
                        Toast.makeText(requireContext(), "No se encontró el avatar...", Toast.LENGTH_SHORT).show()
                    } else {
                        val currentCollectables = usuario.collectables
                        currentCollectables.add(collectable)
                        Toast.makeText(requireContext(), "Avatar desbloqueado", Toast.LENGTH_SHORT).show()
                        updateAvatarConfirmation(collectableID)
                    }

                    sharedPreferences.editData(usuario, "currentUser")

                }
                .setNegativeButton("No"){ dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        } else {
            Toast.makeText(requireContext(), "No tienes los suficientes puntos para comprar este avatar", Toast.LENGTH_SHORT).show()
        }
    }

    fun lookForCollectable(collectableName: String) : Boolean{
        val currentCollectables = usuario.collectables
        currentCollectables.forEach{ collectable ->
            if(collectable.title == collectableName){
                return true
            }
        }

        return false
    }

    fun updateAvatarConfirmation(idDrawable : Int){
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("¿Te gustaría cambiar tu avatar?")
            .setCancelable(false)
            .setPositiveButton("Sí"){dialog, id ->
                (activity as profile).updateAvatar(idDrawable)

            }
            .setNegativeButton("No"){ dialog, id ->
                dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }


}