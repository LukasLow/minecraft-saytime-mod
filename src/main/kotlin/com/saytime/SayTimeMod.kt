package com.saytime

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.text.Text
import org.slf4j.LoggerFactory
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object SayTimeMod : ModInitializer {
    private val logger = LoggerFactory.getLogger("saytime-mod")

    override fun onInitialize() {
        logger.info("SayTimeMod geladen.")

        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            dispatcher.register(
                CommandManager.literal("saytime")
                    .executes { context ->
                        val now = LocalTime.now()
                        val timeString = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                        context.source.sendFeedback(
                            { Text.literal("🕒 Aktuelle Serverzeit: $timeString") },
                            false
                        )
                        1
                    }
            )
        }
    }
}
