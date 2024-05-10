package me.hi12167pies.kbffa.Events;

import me.hi12167pies.kbffa.Config;
import me.hi12167pies.kbffa.Handlers.ArenaHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    @EventHandler
    void a(PlayerJoinEvent e) {
        if (Config.MainClass.getConfig().getBoolean("bungeecord.enabled")) {
            ArenaHandler.join(e.getPlayer(), Config.MainClass.getConfig().getString("bungeecord.map"));
        }
    }
}
