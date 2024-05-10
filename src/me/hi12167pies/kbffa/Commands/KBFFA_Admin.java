package me.hi12167pies.kbffa.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.hi12167pies.kbffa.Config.Arenas;

public class KBFFA_Admin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length == 2) {
            String map = args[1];
            switch (args[0]) {
                case "create":
                    player.sendMessage("set " + map + " spawnpoint to your current location");
                    Arenas.setSpawn(map, player.getLocation());
                    break;
                case "delete":
                    if (Arenas.mapExist(map)) {
                        player.sendMessage("deleted map" + map);
                        Arenas.deleteMap(map);
                    } else {
                        player.sendMessage("map does not exist.");
                    }
                    break;
                case "setvoid":
                    player.sendMessage("set void for map " + map);
                    Arenas.setVoid(map, player.getLocation().getY());
                    break;
                case "settop":
                    player.sendMessage("set top for map " + map);
                    Arenas.setTop(map, player.getLocation().getY());
                    break;
            }
        }

        return true;
    }
}
