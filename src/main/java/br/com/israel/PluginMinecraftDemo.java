package br.com.israel;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.israel.commands.PingCommand;
import br.com.israel.listeners.PlayerJoinListener;
import net.md_5.bungee.api.ChatColor;

public class PluginMinecraftDemo extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info(ChatColor.YELLOW + " Plugin iniciado!");

        getCommand("ping").setExecutor(new PingCommand());

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
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
