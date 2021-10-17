package com.example.coquille.utils

import com.example.coquille.R
import com.example.coquille.models.CardContent
import com.example.coquille.models.Question


object Data {

    val cardCuentos1 = CardContent("¡Prepárate para leer historias divertidas! A lo largo de cada lectura se te harán preguntas sencillas y podrás obtener puntos.\nPodrás utilizar el botón de la parte inferior para obtener pistas")
    val cardCuentos2 = CardContent("El león y el ratón", 1, R.drawable.lion)
    val cardCuentos3 = CardContent("El patito feo", 2, R.drawable.duck)
    val cardCuentos4 = CardContent("El pájaro y la ballena", 5, R.drawable.whale)

    val cardSecuencia1 = CardContent("Bienvenido al juego de la secuencia, analiza los patrones y selecciona la siguiente figura que lo completa")
    val cardSecuencia2 = CardContent("Bosque encantado", 2, R.drawable.ic_tree)
    val cardSecuencia3 = CardContent("Viaje a la edad media", 3, R.drawable.ic_castle)
    val cardSecuencia4 = CardContent("Animales curiosos", 5, R.drawable.ic_peacock)

    val cardMemoria1 = CardContent("Encuentra los todos los pares de tarjetas que puedas sin equivocarte y pon a prueba tu memoria. ¡Encuéntralos todos para ganar un bonus!")
    val cardMemoria2 = CardContent("Normal", 2, R.drawable.ic_camel)
    val cardMemoria3 = CardContent("Difícil", 3, R.drawable.ic_crab)
    val cardMemoria4 = CardContent("Nivel Experto", 5, R.drawable.ic_fire)


    val cardTortuga1 = CardContent("¡Bievenid@ al juego de la tortuga! ¡Apaga todos los fuegos con tus poderes de tortuga antes de que se acabe el tiempo! Cuidado con los changos porque ellos no te quieren dejar pasar")
    val cardTortuga2 = CardContent("Tutorial", 1, R.drawable.player_circle)
    val cardTortuga3 = CardContent("Bomberos al rescate", 2, R.drawable.ic_fairy)
    val cardTortuga4 = CardContent("¡Maestro de las llamas!", 5, R.drawable.ic_fire)


    val cardDibuja1 = CardContent("Observa detenidamente y selecciona la imagen que es diferente a todas las demás.")
    val cardDibuja2 = CardContent("En busca de plantas", 1, R.drawable.ic_chinese_evergreen)
    val cardDibuja3 = CardContent("Un mundo fantástico", 3, R.drawable.ic_dragon)
    val cardDibuja4 = CardContent("¡En marcha!", 5, R.drawable.ic_husky_dog)

    val cardsCuentos = arrayOf(cardCuentos1, cardCuentos2, cardCuentos3, cardCuentos4)
    val cardsDibuja = arrayOf(cardDibuja1, cardDibuja2, cardDibuja3, cardDibuja4)
    val cardsMemoria = arrayOf(cardMemoria1, cardMemoria2,cardMemoria3, cardMemoria4)
    val cardsTortuga = arrayOf(cardTortuga1, cardTortuga2, cardTortuga3, cardTortuga4)
    val cardsSecuencia = arrayOf(cardSecuencia1, cardSecuencia2, cardSecuencia3, cardSecuencia4)


    val morning = getEmojiByUnicode(0x1F304)
    val pajaro = getEmojiByUnicode(0x1F426)
    val ballena = getEmojiByUnicode(0x1F433)
    val agua = getEmojiByUnicode(0x1F30A)
    val luna = getEmojiByUnicode(0x1F319)
    val bote= getEmojiByUnicode(0x1F6E5)
    val mundo = getEmojiByUnicode(0x1F30E)
    val hoja = getEmojiByUnicode(0x1F342)
    val nieve = getEmojiByUnicode(0x2744)
    val pez = getEmojiByUnicode(0x1F41F)
    val sandia = getEmojiByUnicode(0x1F349)
    val pulpo = getEmojiByUnicode(0x1F991)
    val check = getEmojiByUnicode(0x2705)
    val cross = getEmojiByUnicode(0x274C)

    val pato = getEmojiByUnicode(0x1F986)
    val huevo = getEmojiByUnicode(0x1F95A)
    val patito = getEmojiByUnicode(0x1F425)
    val gris = getEmojiByUnicode(0x26AA)
    val azul = getEmojiByUnicode(0x1F535)
    val cafe = getEmojiByUnicode(0x1F7E4)
    val amarillo = getEmojiByUnicode(0x1F7E1)
    val naranja = getEmojiByUnicode(0x1F7E0)
    val moon = getEmojiByUnicode(0x1F311)
    val arbol = getEmojiByUnicode(0x1F332)
    val gato = getEmojiByUnicode(0x1F408)
    val gallina = getEmojiByUnicode(0x1F414)
    val flores = getEmojiByUnicode(0x1F33A)
    val cisne = getEmojiByUnicode(0x1F9A2)

    val sol = getEmojiByUnicode(0x2600)
    val leon = getEmojiByUnicode(0x1F981)
    val raton = getEmojiByUnicode(0x1F42D)
    val please = getEmojiByUnicode(0x1F97A)
    val risa = getEmojiByUnicode(0x1F923)
    val tiempo = getEmojiByUnicode(0x23F1)
    val corazon = getEmojiByUnicode(0x2764)



    val pajaroBallenaCuento = arrayOf("Una vez hubo un $pajaro que amaba($corazon) a una $ballena. Y una $ballena que amaba($corazon) a un $pajaro.",
       "Al $pajaro le encantaba la hermosa sonrisa de la $ballena. Le encantaba cómo nadaba elegantemente por el $agua.",
       "A la $ballena le encantaban las bonitas plumas azules del $pajaro. Le gustaba mirar cómo se elevaba hacia el cielo.",
       "Durante el verano, el $pajaro y la $ballena se encontraron en la bahía. Hablaron y hablaron. Hablaron de la $luna, de las olas, y de los $bote en el océano.",
       "El $pajaro contó chistes que hicieron reír ($risa) a la $ballena. La $ballena cantó hermosas canciones que hicieron llorar al $pajaro.",
       "\"Un día podrías conocer a mi familia en el océano ($agua)\", dijo la $ballena.\n\"Y tú podrías conocer a mis amigos en la tierra\", dijo el $pajaro.",
       "Todo era perfecto.\nPero el $mundo no para de girar solo porque un $pajaro y una $ballena se enamoran ($corazon).",
       "El verano se transformó en otoño ($hoja), y el otoño se transformó en invierno ($nieve). El océano se volvió frío, y todas las ballenas partieron hacia aguas más cálidas",
       "\"Ven conmigo donde hay aguas cálidas\", dijo la $ballena. \"Es un lugar maravilloso. Siempre es cálido, y hay muchísimos $pez para comer\".",
       "\"Me encanta comer $pez\", dijo el $pajaro. \"Y me encanta estar contigo ($corazon). Te seguiré a cualquier lugar. Pero primero, enséñame a ser una ballena\".",
       "\"¡Así!\", dijo la $ballena. \"¡Sígueme!\", y se sumergió en el agua.\n\"¡De acuerdo!\" dijo el $pajaro, y él también se sumergió en el agua.",
       "Se sumergió más y más profundo. \"¡Estoy nadando!\" rió. \"¡Soy una $ballena!\"",
       "Pero pronto no pudo respirar y regresó jadeando a la superficie. El $pajaro intentó e intentó e intentó nadar, pero se quedó sin aliento cada vez.",
       "\"No creo que un $pajaro pueda ser una $ballena\", dijo el $pajaro.",
       "\"Mejor ven conmigo. Vivo arriba en los acantilados. Es un lugar maravilloso. Es cálido y acogedor, y cada mañana puedes ver el amanecer ($morning)\".",
       "\"Me encanta ver el amanecer ($morning)\", dijo la $ballena. \"Y me encanta estar contigo ($corazon). Te seguiré a cualquier lugar. Pero primero, enséñame a ser un $pajaro\".",
       "\"¡Así!\", dijo el $pajaro. \"¡Sígueme!\", y batió sus alas y se elevó en el cielo. \"¡De acuerdo!\" dijo la $ballena. Cerró los ojos con fuerza y batió sus aletas como el $pajaro.",
       "Aleteó y aleteó, arriba y abajo. El agua ($agua) salpicó en todos lados. \"¡Estoy volando!\" rió. \"¡Soy un $pajaro!\".",
       "Pero cuando la $ballena abrió los ojos, no estaba elevándose en el cielo. Todavía estaba en el agua ($agua). Ella intentó e intentó e intentó volar, pero no pudo.",
       "\"No creo que una $ballena pueda ser un $pajaro\", dijo la $ballena.\n\"Tú no puedes volar y yo no puedo nadar. ¿Dónde podremos vivir juntos?\" dijo el $pajaro.",
       "\"Nos quedaremos aquí ... ¡en las olas ($agua)!\" dijo la $ballena.\nPero el $pajaro sacudió la cabeza tristemente.",
       "\"A ti te encanta nadar profundo en el océano ($agua)\", dijo. \"Eso es lo que más te gusta hacer. Nunca serás feliz aquí\".",
       "\"Y a ti te encanta volar y planear en el cielo\", dijo la $ballena. \"Eso es lo que más te gusta hacer. Tú tampoco serás feliz aquí\".",
       "Y entonces, como el $pajaro y la $ballena se querían tanto, se dijeron adiós ... pero nunca se olvidaron el uno del otro.",
       "Cada vez que la $ballena miraba un $pajaro volando alto en el cielo, pensaba en su $pajaro. Ella esperaba que él estuviera disfrutando de los cielos de esa manera.",
       "Y cada vez que el $pajaro veía una $ballena sumergiéndose profundo en el océano ($agua), pensaba en su $ballena. Él esperaba que ella estuviera disfrutando del océano de esa manera."
    )
    val res1Pajaro = Question("¿De qué color es el $pajaro?", arrayOf("Café ($cafe)", "Azul ($azul)", "Amarillo ($amarillo)"), 1, 5)
    val res2Pajaro = Question("¿Qué les gusta comer a la $ballena y al $pajaro?", arrayOf("Pez ($pez)", "Pulpo ($pulpo)", "Sandías ($sandia)"), 0, 11)
    val res3Pajaro = Question("Al final la $ballena y el $pajaro se pelearon", arrayOf("Verdadero ($check)", "Falso ($cross)"), 1, 26)

    val patitoFeo = arrayOf("En la granja había un gran alboroto: los polluelos de Mamá Pata ($pato) estaban rompiendo el cascarón.",
        "Uno a uno, comenzaron a salir. Mamá Pata ($pato) estaba tan emocionada con sus patitos ($patito) que no notó que uno de sus $huevo, permanecía intacto.",
        "A las pocas horas ($tiempo), el último $huevo comenzó a romperse. Mamá Pata ($pato) y todos los polluelos ($patito), se encontraban a la expectativa de conocer al pequeño.",
        "De repente, salió un $patito muy alegre. Todos quedaron sorprendidos, este $patito no era pequeño ni amarillo y tampoco estaba cubierto de suaves plumas.",
        "Este patito era grande y de color $gris. Aunque nadie dijo nada, todos pensaron lo mismo: \"Este patito es demasiado feo\".",
        "Pasaron los días y todos los animales de la granja se burlaban de él. El $patito feo se sintió muy triste y una noche($luna) escapó de la granja.",
        "El $patito feo recorrió el bosque ($arbol) y encontró el hogar de una humilde anciana que vivía con una $gato y una $gallina.",
        "El $patito se quedó con ellos durante un tiempo ($tiempo), pero como no estaba contento, pronto se fue.",
        "Finalmente, llegó la primavera ($flores). El patito feo vio a una familia de $cisne nadando en el estanque y quiso acercárseles.",
        "Pero recordó cómo todos se burlaban de él y agachó la cabeza. Cuando miró su reflejo en el agua($agua) se quedó asombrado",
        "Él no era un $patito feo, sino un apuesto y joven $cisne. Ahora sabía por qué se veía tan diferente a sus hermanos y hermanas.",
        "¡Ellos eran $patito, pero él era un $cisne!"
    )
    val res1Patito = Question("¿De qué color era el último patito ($patito) que salió?", arrayOf("Gris ($gris)", "Naranja ($naranja)", "Amarillo ($amarillo)"), 0, 6)
    val res2Patito = Question("¿Qué era realmente el patito ($patito) feo?", arrayOf("Pájaro ($pajaro)", "Pato ($pato)", "Cisne ($cisne)"), 2, 12)

    val leonRatonCuento = arrayOf("En un día muy soleado ($sol), dormía plácidamente un $leon cuando un pequeño $raton pasó por su lado y lo despertó.",
        "El $leon tomó al $raton con sus enormes garras y cuando estaba a punto de aplastarlo, escuchó al $raton decirle: ",
        "-\"Déjame ir, puede que algún día llegues a necesitarme ($please).\"",
        "Fue tanta la risa ($risa) que estas palabras le causaron, que el $leon decidió soltarlo.",
        "Al cabo de unas pocas horas ($tiempo), el $leon quedó atrapado en las redes de unos cazadores.",
        "El $raton, fiel a su promesa, acudió en su ayuda. Sin tiempo($tiempo) que perder, comenzó a morder la red hasta dejar al $leon en libertad.",
        "El $leon agradeció al $raton por haberlo salvado y desde ese día comprendió que todos los seres son importantes($corazon)."
        )
    val res1Raton = Question("¿Quién fue atrapado por la red de unos cazadores", arrayOf("Ratón ($raton)", "Pez ($pez)", "León ($leon)"), 2, 7)


    fun getEmojiByUnicode(unicode: Int): String{
        return String(Character.toChars(unicode))
    }

    fun getCuento(nombreCuento: String): Array<String>{
        val dummy = arrayOf("")

        when(nombreCuento){
            "El león y el ratón" -> return leonRatonCuento
            "El patito feo" -> return patitoFeo
            "El pájaro y la ballena" -> return pajaroBallenaCuento
        }

        return dummy
    }

    fun getPreguntas(nombreCuento: String): Array<Question>{
        val dummy = arrayOf(Question("", arrayOf(""), 0, 0))
        when(nombreCuento){
            "El león y el ratón" -> return arrayOf(res1Raton)
            "El patito feo" -> return arrayOf(res1Patito, res2Patito)
            "El pájaro y la ballena" -> return arrayOf(res1Pajaro, res2Pajaro, res3Pajaro)
        }
        return dummy
    }

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