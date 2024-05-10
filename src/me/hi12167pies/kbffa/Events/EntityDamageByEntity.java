package me.hi12167pies.kbffa.Events;

import me.hi12167pies.kbffa.Handlers.ArenaHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;

import static me.hi12167pies.kbffa.Config.Arenas;

public class EntityDamageByEntity implements Listener {
    public static HashMap<Player, Player> combatTag = new HashMap<>();

    @EventHandler
    void a(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        if (ArenaHandler.playing(player)) {
            e.setDamage(0);
        }
    }

    @EventHandler
    void a(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (!(e.getDamager() instanceof Player)) return;

        Player attacker = (Player) e.getDamager();
        Player victim = (Player) e.getEntity();

        if (victim.getLocation().getY() > Arenas.getTop(ArenaHandler.getMap(victim))) {
            e.setCancelled(true);
            return;
        }

        combatTag.put(victim, attacker);
    }
}
