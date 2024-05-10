package me.hi12167pies.kbffa.Events;

import me.hi12167pies.kbffa.Handlers.ArenaHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static me.hi12167pies.kbffa.Config.Arenas;
import static me.hi12167pies.kbffa.Config.MainClass;

public class BlockPlace implements Listener {
    @EventHandler
    void a(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE) return;

        if (!ArenaHandler.playing(player)) return;

        if (e.getBlockPlaced().getY() > Arenas.getTop(ArenaHandler.getMap(player))) {
            e.setCancelled(true);
            return;
        }

        if (e.getPlayer().getItemInHand().getAmount() < 31) {
            e.getPlayer().getItemInHand().setAmount(64);
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(MainClass, new Runnable() { @Override public void run() {
            e.getBlock().getChunk().load();
            e.getBlock().setType(Material.REDSTONE_BLOCK);
        }}, 100);
        Bukkit.getScheduler().scheduleSyncDelayedTask(MainClass, new Runnable() { @Override public void run() {
            e.getBlock().getChunk().load();
            e.getBlock().setType(Material.AIR);
        }}, 120);
    }
}
