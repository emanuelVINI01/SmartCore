package com.emanuelvini.smart.core.bukkit

import com.emanuelvini.smart.core.bukkit.logger.BukkitLogger
import com.google.common.io.ByteStreams
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener

class SmartCore : JavaPlugin() {

    private val pluginLogger = BukkitLogger()

    private var ack = false

    override fun onEnable() {
        pluginLogger.warn("Plugin habilitado com sucesso, aguardando ACK do BungeeCord para continuar execução.")
        val ackListener = ACKWaiter()
        server.messenger.registerOutgoingPluginChannel(this, "sc:main");
        server.messenger.registerIncomingPluginChannel(this, "sc:main", ackListener);
        server.pluginManager.registerEvents(ackListener, this)
        Thread {
            while (!ack) {
            }
            server.messenger.unregisterIncomingPluginChannel(this, "sc:main", ackListener)
            HandlerList.getHandlerLists().forEach {
                it.unregister(ackListener)
            }
            pluginLogger.log("${ChatColor.GREEN}ACK recebido com sucesso, plugin funcionando")
        }.start()
    }

    internal inner class ACKWaiter : PluginMessageListener, Listener {
        override fun onPluginMessageReceived(channel: String, player: Player, message: ByteArray) {
            val m = ByteStreams.newDataInput(message)
            val subChannel = m.readUTF()
            val version = m.readUTF()
            if (channel == "sc:main" && subChannel == "ack") {
                ack = true
                pluginLogger.log("Sincronização realizada com a versão §f$version")
            }
        }

        @EventHandler
        fun onJoin(event: PlayerLoginEvent) {
            server.scheduler.runTaskLater(this@SmartCore, {
                event.player.kickPlayer("§cMensagem ACK não recebida.")
            }, 20L * 5)
        }

    }

}