package com.emanuelvini.smart.core.bukkit.logger

import com.emanuelvini.smart.shared.logger.Logger
import org.bukkit.Bukkit

class BukkitLogger : Logger {
    override fun log(string: String) {
        Bukkit.getConsoleSender().sendMessage("§b[SmartCore] §a[BUKKIT]§7 $string")
    }
}