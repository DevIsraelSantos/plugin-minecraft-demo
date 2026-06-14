package br.com.israel.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.com.israel.PluginMinecraftDemo;

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

        sender.sendMessage(plugin.getMessageService().getPingMessage());

        return true;
    }

}
