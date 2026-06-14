package br.com.israel;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.israel.commands.PingCommand;
import br.com.israel.listeners.PlayerJoinListener;

public class PluginMinecraftDemo extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getLogger().info("Plugin iniciado! - ");

        getCommand("ping").setExecutor(new PingCommand(this));

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin desligado!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ping")) {
            sender.sendMessage("Pong!");
            return true;
        }

        return false;
    }
}
