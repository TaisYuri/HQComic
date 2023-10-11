package com.example.hqmarvel.helper

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class ApiHelper {

    companion object{
        //Metodo para pegar timestamp
        fun getCurrentTimeStamp() = Timestamp(System.currentTimeMillis()).toString()

        //Metodo para gerar HASH
        fun generateMD5Hash(input: String): String{
            val md = MessageDigest.getInstance("MD5")
            val hash = md.digest(input.toByteArray())
            val bigInteger = BigInteger(1,hash).toString(16).padStart(32, '0')
            return bigInteger
        }
    }
}