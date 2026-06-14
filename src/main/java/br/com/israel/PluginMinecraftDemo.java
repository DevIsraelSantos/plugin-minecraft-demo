package br.com.israel;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMinecraftDemo extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin iniciado!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin desligado!");
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if (command.getName().equalsIgnoreCase("ping")) {
            sender.sendMessage("Pong!");
            return true;
        }

        return false;
    }
}