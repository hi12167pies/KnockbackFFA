package me.hi12167pies.kbffa.Handlers;

import me.hi12167pies.kbffa.Config;
import me.hi12167pies.kbffa.Events.EntityDamageByEntity;
import me.hi12167pies.kbffa.Utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.util.HashMap;

import static me.hi12167pies.kbffa.Config.Arenas;
import static me.hi12167pies.kbffa.Config.MainClass;

public class ArenaHandler {
    public static HashMap<Player, String> Playing = new HashMap<>();

    public static boolean playing(Player player) {
        return Playing.containsKey(player);
    }

    public static String getMap(Player player) {
        return Playing.get(player);
    }

    public static void join(Player player, String map) {
        if (!Arenas.mapExist(map)) return;
        Playing.put(player, map);
        reset(player, false);
        runStartCommands(player);
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Playing.containsKey(p))
                p.sendMessage(MainClass.getConfig().getString("messages.join")
                        .replace("&", "§")
                        .replace("<player>", player.getName()));
        }
    }

    public static void leave(Player player) {
        if (MainClass.getConfig().getBoolean("bungeecord.enabled")) {
            player.sendMessage("error:cannot_leave_in_bungeecord");
            return;
        }
        Playing.remove(player);
        runEndCommands(player);
        player.getInventory().clear();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Playing.containsKey(p))
                p.sendMessage(MainClass.getConfig().getString("messages.leave")
                        .replace("&", "§")
                        .replace("<player>", player.getName()));
        }
    }

    public static Boolean inBorder(Player player) {
        if (!playing(player)) return false;

        int XBorder = MainClass.getConfig().getInt("border.x");
        int ZBorder = MainClass.getConfig().getInt("border.z");

        if (player.getLocation().getY() < Arenas.getVoid(getMap(player)))
            return false;
        else if (player.getLocation().getZ() > Arenas.getSpawn(getMap(player)).getZ() + ZBorder)
            return false;
        else if (player.getLocation().getZ() < Arenas.getSpawn(getMap(player)).getZ() - ZBorder)
            return false;
        else if (player.getLocation().getX() > Arenas.getSpawn(getMap(player)).getX() + XBorder)
            return false;
        else if (player.getLocation().getX() < Arenas.getSpawn(getMap(player)).getX() - XBorder)
            return false;

        return true;
    }

    public static void reset(Player player) {
        reset(player, true);
    }
    public static void reset(Player player, boolean sendmsg) {
        player.getInventory().clear();
        Enchantment[] kb = {Enchantment.KNOCKBACK};
        Enchantment[] inf = {Enchantment.ARROW_INFINITE};
        int[] level = {1};
        player.getInventory().setItem(0, new ItemBuilder(Material.STICK).hideAttr().ench(kb, level).build());
        player.getInventory().setItem(1, new ItemBuilder(Material.BOW).unBreak().hideUnBreak().hideAttr().ench(inf, level).build());
        player.getInventory().setItem(2, new ItemBuilder(Material.SANDSTONE).amount(64).build());
        player.getInventory().setItem(8, new ItemBuilder(Material.ENDER_PEARL).amount(5).build());
        player.getInventory().setItem(35, new ItemBuilder(Material.ARROW).build());
        player.setHealth(20);

        if (sendmsg) {
            String killMSG;
            if (EntityDamageByEntity.combatTag.containsKey(player)) {
                EntityDamageByEntity.combatTag.get(player).getInventory().addItem(
                        new ItemBuilder(Material.ENDER_PEARL).build()
                );
                killMSG = MainClass.getConfig().getString("messages.kill.a")
                        .replace("&", "§")
                        .replace("<player>", player.getName())
                        .replace("<attacker>", EntityDamageByEntity.combatTag.get(player).getName());
            }else
                killMSG = MainClass.getConfig().getString("messages.kill.no_reason")
                        .replace("&", "§")
                        .replace("<player>", player.getName());

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Playing.containsKey(p))
                    p.sendMessage(killMSG);
            }
        }

        EntityDamageByEntity.combatTag.remove(player);
        player.teleport(Arenas.getSpawn(getMap(player)));
    }

    public static void runStartCommands(Player player) {
        if (!MainClass.getConfig().getBoolean("commands.enabled.join")) return;
        for (String s : MainClass.getConfig().getStringList("commands.join_arena")) {
            String cmd = s;
            if (cmd.startsWith("/")) cmd = s.substring(1);
            player.performCommand(cmd);
        }
    }
    public static void runEndCommands(Player player) {
        if (!MainClass.getConfig().getBoolean("commands.enabled.leave")) return;
        for (String s: MainClass.getConfig().getStringList("commands.leave_arena")) {
            String cmd = s;
            if (cmd.startsWith("/")) cmd = s.substring(1);
            player.performCommand(cmd);
        }
    }
}
