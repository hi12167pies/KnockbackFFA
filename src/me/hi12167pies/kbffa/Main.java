package me.hi12167pies.kbffa;

import me.hi12167pies.kbffa.Commands.KBFFA;
import me.hi12167pies.kbffa.Commands.KBFFA_Admin;
import me.hi12167pies.kbffa.Events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginCommand("kbffa").setExecutor(new KBFFA());
        Bukkit.getPluginCommand("kbffamap").setExecutor(new KBFFA_Admin());

        Bukkit.getPluginManager().registerEvents(new Move(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        Bukkit.getPluginManager().registerEvents(new Protection(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new Join(), this);
    }
}
