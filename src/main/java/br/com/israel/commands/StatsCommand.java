package br.com.israel.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.israel.PluginMinecraftDemo;
import br.com.israel.helpers.CommandHelper;
import br.com.israel.models.PlayerStats;
import net.kyori.adventure.text.Component;

public class StatsCommand implements CommandExecutor {
    private final PluginMinecraftDemo plugin;

    public static void register(PluginMinecraftDemo plugin) {
        StatsCommand command = new StatsCommand(plugin);

        plugin.getCommand("stats").setExecutor(command);
    }

    public StatsCommand(PluginMinecraftDemo plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {
        try {
            Player player = CommandHelper.getPlayer(sender);
            PlayerStats stats = plugin.getPlayerStatsService().getStats(player.getUniqueId());

            sender.sendMessage(plugin.getMessageService().getPlayerStatsMessage(stats));

            return true;
        } catch (IllegalArgumentException exception) {
            sender.sendMessage(Component.text(exception.getMessage()));
            return true;
        }
    }
}
