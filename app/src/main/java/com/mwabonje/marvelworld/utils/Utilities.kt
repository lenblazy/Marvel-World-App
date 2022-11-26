package com.mwabonje.marvelworld.utils

import com.mwabonje.marvelworld.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class Utilities @Inject constructor() {

    var time = System.currentTimeMillis() / 1000

    fun md5Hash(): String {
        val str =  time.toString() + BuildConfig.PRV_KEY + BuildConfig.PUB_KEY
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

}