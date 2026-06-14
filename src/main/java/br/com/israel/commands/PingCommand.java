package br.com.israel.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.israel.PluginMinecraftDemo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class PingCommand implements CommandExecutor {

    private final PluginMinecraftDemo plugin;

    public PingCommand(PluginMinecraftDemo plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {

        String message = plugin.getConfig().getString("ping-response", "Pong!");

        sender.sendMessage(Component.text(message, NamedTextColor.GREEN));

        return true;
    }

}
