package io.github.redrain0o0.neoriftlink;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(Neoriftlink.MODID)
public class Neoriftlink {
    public static final String MODID = "neoriftlink";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Neoriftlink(IEventBus modBus) {
        NeoForge.EVENT_BUS.addListener(Neoriftlink::onPlayerJoin);
    }

    private static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        LOGGER.info(player.getName().getString() + " joined, send discord webhook");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }


}
