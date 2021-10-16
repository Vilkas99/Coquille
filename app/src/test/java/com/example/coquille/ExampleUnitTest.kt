package com.example.coquille


import com.example.coquille.models.Collectable
import com.example.coquille.models.Settings
import com.example.coquille.models.User
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun seCreaElUsuario(){

        var user = User("Prueba", "asdawdqw", R.raw.profile_king, 1900, MutableList(1){ Collectable("asdasd", 20, "wuajaaaaa") }, Settings())
        assertEquals(user.lastPlayedDate, LocalDateTime.of(2021, 10, 11, 13, 5, 0))
    }



}