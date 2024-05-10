package me.hi12167pies.kbffa.Events;

import me.hi12167pies.kbffa.Handlers.ArenaHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Move implements Listener {
    @EventHandler
    void e(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE) return;

        if (!ArenaHandler.playing(player)) return;

        if (!ArenaHandler.inBorder(player))
            ArenaHandler.reset(player);
    }
}
