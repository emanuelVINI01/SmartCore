package com.emanuelvini.smart.core.bungee

import com.emanuelvini.smart.core.bungee.logger.BungeeLogger
import com.google.common.io.ByteStreams
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.plugin.Plugin
import java.util.concurrent.TimeUnit


class SmartCore : Plugin() {

    private val pluginLogger = BungeeLogger()

    override fun onEnable() {
        proxy.registerChannel("sc:main");
        proxy.scheduler.schedule(this, {
            val out = ByteStreams.newDataOutput()
            out.writeUTF("ack")

            out.writeUTF(description.version)

            proxy.players.firstOrNull()?.server?.info?.sendData("sc:main", out.toByteArray())
        }, 1L, 20L, TimeUnit.SECONDS)
        pluginLogger.log("${ChatColor.GREEN}Plugin habilitado com sucesso.")
    }

}