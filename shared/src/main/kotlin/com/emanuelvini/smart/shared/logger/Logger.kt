package com.emanuelvini.smart.shared.logger

interface Logger {



    fun log(string : String)

    fun warn(string : String) {
        log("§e$string")
    }

    fun error(string : String) {
        log("§c$string")
    }

}