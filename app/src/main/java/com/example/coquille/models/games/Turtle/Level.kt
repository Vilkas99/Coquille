package com.example.coquille.models.games.Turtle

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import com.airbnb.lottie.LottieAnimationView
import com.example.coquille.R
import com.example.coquille.utils.Utils

class Level(totalTime : Long, timeForSharks : Int, timeForFire : Int) {
    //Variables en donde almacenaremos la referencia visual a las posiciones, a las ubicaciones de los fuegos y a la de los tiburones
    var positions = mutableListOf<View>()
    var firePos = mutableListOf<View>()
    var sharkPos = mutableListOf<View>()

    // La clase requiere del tiempo total del nivel, el tiempo de aparición para lo tiburones y los fueguitos
    var totalTime = totalTime
    var timeForSharks = timeForSharks
    var timeForFire = timeForFire


    lateinit var initialPos : View

    //Función que dado un layout, se encarga de encontrar las posiciones del juego y de los fuegos y tiburones presentes.
    fun createElements(myLayout: ConstraintLayout) {
        //Por cada elemento en el layout...
        myLayout.forEach { view ->
            //Si el elemento posee una tag...
            if (view.tag != null){
                val tag = view.getTag() as String
                //Si la tag contiene "fire"
                if(tag.contains("fire")){
                    //Obtenemos su fila y columna
                    val (row, col) = getRowAndCol(tag.slice(4 until tag.length))
                    //Creamos un nuevo tag haciendo uso de la clase "Position", brindandole el id, el estado, y la columna y fila
                    val fireNewTag = Position(Utils.getId(view), "fire", row, col )
                    //Añadimos la vista a nuestro arreglo de fuegos
                    firePos.add(view)
                    //Le asignamos su tag
                    view.setTag(fireNewTag)
                    //Generamos la animación en cuestión
                    Utils.createAnimation(view as LottieAnimationView, R.raw.fire_animation, 100, -50)
                }//Si la tag contiene "shark"
                else if(tag.contains("shark")){
                    //Obtenemos su fila y columna
                    val (row, col) = getRowAndCol(tag.substring(5))
                    //Creamos un nuevo tag haciendo uso de la clase "Position", brindandole el id, el estado, y la columna y fila
                    val sharkNewTag = Position(Utils.getId(view), "shark", row, col)
                    view.setTag(sharkNewTag)
                    sharkPos.add(view)
                    Utils.createAnimation(view as LottieAnimationView, R.raw.shark, 100, -50)
                } else { //Si no entramos en ningun caso anterior, significa que hemos encontrado una posición normal
                    val (row, col) = getRowAndCol(tag) //Obtenemos su fila y columna
                    val id = Utils.getId(view) //Obtenemos su ID
                    val position = Position(id, "position", row, col) //Le generamos su tag de Posición
                    view.setTag(position) // Se lo asignamos

                    if (id == "com.example.coquille:id/mainPos"){ //Si su ID es equivalente al de la posición inicial...
                        initialPos = view //Le asignamos a la posición inicial su valor
                        val position = Position(id, "player", row, col) //Le modificamos su estado
                        view.setTag(position) //Se los asignamos nuevamente
                        //Y le generamos la animación de tortuga en esa posición
                        Utils.createAnimation(initialPos as LottieAnimationView, R.raw.turtle, 100, -50)
                    }

                }
                //Sin importar su categoría, le añadimos al arreglo de posiciones
                positions.add(view)
            }


        }
    }

    //Función que se encarga de obtener la fila y columna a través del tag en cuestión
    fun getRowAndCol(tag : String) : Pair<Int, Int>{
        val row = (tag[0].toInt() - '0'.toInt())
        val col = (tag[2].toInt() - '0'.toInt())

        val result = Pair(row, col)

        return result
    }

}