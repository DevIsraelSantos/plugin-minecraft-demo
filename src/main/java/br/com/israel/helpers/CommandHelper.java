package br.com.israel.helpers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;

public class CommandHelper {
    private CommandHelper() {
    }

    public static Player getPlayer(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            throw new IllegalArgumentException("Este commando só pode ser usado por jogadores");
        }

        return player;
    }

    public static boolean validatePlayer(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(
                    Component.text("Este comando só pode ser usado por jogadores."));

            return false;
        }

        return true;
    }

}
