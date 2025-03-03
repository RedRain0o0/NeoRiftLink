package io.github.redrain0o0.neoriftlink;

import com.mojang.logging.LogUtils;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import java.awt.*;
import java.io.IOException;

@Mod(Neoriftlink.MODID)
public class Neoriftlink {
    public static final String MODID = "neoriftlink";
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final DiscordWebhook WEBHOOKMIKU = new DiscordWebhook("https://discord.com/api/webhooks/1345952580559175801/7M-bxcxfAkydwQKtPr6wVSJ81Amak9tYkNv6RVfpDXhMmkDpxcfpb6COKYwyuN-RRzNC");
    private static final DiscordWebhook WEBHOOKRAD = new DiscordWebhook("https://discord.com/api/webhooks/1345952805990436965/c4cYNNMSPJ1WO82T0qXZjUKxtaSMRiK4ncWeR_3LDb4CQzuJ7Cdo3DX4V0KZEHpKA3RV");

    public Neoriftlink(IEventBus modBus) {
        NeoForge.EVENT_BUS.addListener(Neoriftlink::onPlayerJoin);
        NeoForge.EVENT_BUS.addListener(Neoriftlink::onPlayerLeave);
        NeoForge.EVENT_BUS.addListener(Neoriftlink::onPlayerChat);
        NeoForge.EVENT_BUS.addListener(Neoriftlink::onPlayerDie);
        //NeoForge.EVENT_BUS.addListener(Neoriftlink::onPlayerAdvancement);
    }

    private static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        LOGGER.info(player.getName().getString() + " joined, send discord webhook");
        WEBHOOKMIKU.setUsername("Server");
        //WEBHOOKRAD.setUsername("Server");
        WEBHOOKMIKU.setContent(player.getName().getString() + " has joined the game!");
        //WEBHOOKRAD.setContent(player.getName().getString() + " has joined the game!");
        WEBHOOKMIKU.execute();
    }

    private static void onPlayerChat(ServerChatEvent event) {
        Player player = event.getPlayer();
        Component message = event.getMessage();
        String rawText = event.getRawText();
        String username = event.getUsername();
        LOGGER.info(player.getName().getString() + " (" + username + ") said " + rawText + ", send discord webhook");

    }

    private static void onPlayerDie(LivingDeathEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            DamageSource source = event.getSource();
            LOGGER.info(player.getName().getString() + " died to " + source + ", send discord webhook");
        }
    }

    private static void onPlayerAdvancement(AdvancementEvent event) {
        Player player = event.getEntity();
        AdvancementHolder advancement = event.getAdvancement();
        LOGGER.info(player.getName().getString() + " get advancement " + advancement + ", send discord webhook");
    }

    private static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        LOGGER.info(player.getName().getString() + " left, send discord webhook");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }


}
