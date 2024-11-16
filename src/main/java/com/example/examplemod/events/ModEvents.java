package com.example.examplemod.events;

import org.slf4j.Logger;

import com.example.examplemod.ExampleMod;
import com.mojang.logging.LogUtils;

import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.server.command.ConfigCommand;

@EventBusSubscriber(modid = ExampleMod.MODID)
public class ModEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("hello")
                .then(Commands.literal("io").executes((command) -> {
                    LOGGER.info("OIOIOI!");
                    return 0;
                })));
        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        event.getOriginal().getPersistentData().putIntArray(ExampleMod.MODID + "homepos",
                event.getOriginal().getPersistentData().getIntArray(ExampleMod.MODID + "homepos"));
    }
}