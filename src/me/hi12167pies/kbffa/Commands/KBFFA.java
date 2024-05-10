package me.hi12167pies.kbffa.Commands;

import me.hi12167pies.kbffa.Handlers.ArenaHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KBFFA implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length == 1) {
            if (args[0].equals("leave")) {
                ArenaHandler.leave(player);
            } else sender.sendMessage(command.getUsage());
        } else if (args.length == 2) {
            if (args[0].equals("join")) {
                ArenaHandler.join(player, args[1]);
            } else sender.sendMessage(command.getUsage());
        } else sender.sendMessage(command.getUsage());

        return true;
    }
}
