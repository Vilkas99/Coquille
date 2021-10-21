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

    //Obtenemos las referencias al logro, su titulo y costo.
    lateinit var primerLogro : ImageView
    lateinit var primerLogroTitulo: TextView
    lateinit var primerLogroCosto: TextView

    lateinit var usuario : User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile_achivement_, container, false)

        sharedPreferences = MySharedPreferences(requireContext())

        primerLogroTitulo = rootView.findViewById(R.id.logro1Titulo)

        primerLogro = rootView.findViewById(R.id.logro1)

        primerLogroCosto = rootView.findViewById(R.id.logro1Costo)

        usuario = Utils.getCurrentUser(requireContext())

        //Establecemos la función "OnClick" del objeto "primerLogro"..
        primerLogro.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //Si el usuario ya posee ese avatar.
                if (lookForCollectable(primerLogroTitulo.text.toString())){
                    //Ejecutamos la confirmación.
                    updateAvatarConfirmation(R.raw.profile_orc)
                } else { //Si aun no lo posee...

                    //Obtenemos su costo y título..
                    val costo = Integer.parseInt(primerLogroCosto.text.toString())
                    val titulo = primerLogroTitulo.text.toString()
                    //Ejecutamos la compra.
                    acquireCollectable(titulo, costo, R.raw.profile_orc)
                }

            }
        })


        return rootView
    }


    //FUnción que se ejecuta cuando el usuario desea adquirir un nuevo avatar.
    fun acquireCollectable(collectableName: String, collectableCost: Int, collectableID: Int){
        //Si el usuario posee los puntos suficientes.
        if (usuario.points >= collectableCost){
            //Creamos una alerta de diálogo
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("¿Estás segur@ de adquirir este avatar?")
                .setCancelable(false)
                .setPositiveButton("Sí"){dialog, id -> //Si el usuario acepta...
                    usuario.points -= collectableCost //Disminuimos sus puntos.
                    //Obtenemos el "collectable" que acaba de adquirir...
                    val collectable = Utils.getCollectable(requireContext(), collectableName)

                    if (collectable.title == "Dummy" ){ //Si obtuvimos un "Dummy", significa que no encontramos al avatar
                        //Le notificamos al usuario.
                        Toast.makeText(requireContext(), "No se encontró el avatar...", Toast.LENGTH_SHORT).show()
                    } else { //Si no fue "Dummy" entonces hemos encontrado al avatar...
                        val currentCollectables = usuario.collectables //Obtenemos todos los coleccionables del usuario
                        currentCollectables.add(collectable) //Le añadimos el coleccionable que acaba de comprar.
                        //Le notificamos al usuario con un toast que su compra ha sido realizada con éxito.
                        Toast.makeText(requireContext(), "Avatar desbloqueado", Toast.LENGTH_SHORT).show()
                        //Ejecutamos la función "updateAvatarConfirmation"
                        updateAvatarConfirmation(collectableID)
                    }

                    //Actualizamos la info de nuestro sharedPreferences con los nuevos valores que le asignamos al usuario.
                    sharedPreferences.editData(usuario, "currentUser")

                }
                .setNegativeButton("No"){ dialog, id -> //Si el usuario selecciona "No" entonces cerramos el diálogo.
                    dialog.dismiss()
                }

            val alert = builder.create() //Creamos la alerta
            alert.show() //La mostramos.
        } else { //Si el usuario no tiene la cantidad de puntos suficiente, se lo notificamos.
            Toast.makeText(requireContext(), "No tienes los suficientes puntos para comprar este avatar", Toast.LENGTH_SHORT).show()
        }
    }

    //Función que se encarga de evaluar si el coleccionable en cuestión ya ha sido obtenido por el usuario.
    fun lookForCollectable(collectableName: String) : Boolean{
        val currentCollectables = usuario.collectables
        currentCollectables.forEach{ collectable ->
            if(collectable.title == collectableName){
                return true
            }
        }

        return false
    }

    //Función que se encarga de actualizar el avatar del usuario.
    fun updateAvatarConfirmation(animation : Int){
        val builder = AlertDialog.Builder(requireContext()) //Generamos un builder de alerta.
        builder.setMessage("¿Te gustaría cambiar tu avatar?")
            .setCancelable(false)
            .setPositiveButton("Sí"){dialog, id -> //Si el usuario selecciona que sí...
                (activity as profile).updateAvatar(animation) //Llamamos a la función updateAvatar de la actividad "profile" y le brindamos la animación en cuestión

            }
            .setNegativeButton("No"){ dialog, id -> //Si el usuario selecciona que "No", entonces cerramos la ventana de diálogo.
                dialog.dismiss()
            }

        val alert = builder.create() //Creamos la alerta.
        alert.show() //La mostramos.
    }
}