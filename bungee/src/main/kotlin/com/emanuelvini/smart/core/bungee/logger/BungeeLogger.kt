package com.emanuelvini.smart.core.bungee.logger

import com.emanuelvini.smart.shared.logger.Logger
import net.md_5.bungee.api.ProxyServer

class BungeeLogger : Logger {
    override fun log(string: String) {
        ProxyServer.getInstance().console.sendMessage(("§b[SmartCore] §a[BUNGEE]§7 $string"))
    }
}