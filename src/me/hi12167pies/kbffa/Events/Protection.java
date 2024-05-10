package me.hi12167pies.kbffa.Events;

import me.hi12167pies.kbffa.Handlers.ArenaHandler;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Protection implements Listener {
    @EventHandler
    void a(PlayerDropItemEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if (ArenaHandler.playing(e.getPlayer()))
            e.setCancelled(true);
    }
    @EventHandler
    void a(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if (ArenaHandler.playing(e.getPlayer()))
            e.setCancelled(true);
    }
}
