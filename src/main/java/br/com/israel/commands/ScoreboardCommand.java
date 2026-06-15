package br.com.israel.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.israel.PluginMinecraftDemo;
import br.com.israel.helpers.CommandHelper;
import br.com.israel.models.PlayerStats;
import net.kyori.adventure.text.Component;

public class ScoreboardCommand implements CommandExecutor {

    private final PluginMinecraftDemo plugin;

    public ScoreboardCommand(PluginMinecraftDemo plugin) {
        this.plugin = plugin;
    }

    public static void register(PluginMinecraftDemo plugin) {
        ScoreboardCommand command = new ScoreboardCommand(plugin);

        plugin.getCommand("scoreboard").setExecutor(command);
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {

        try {
            Player player = CommandHelper.getPlayer(sender);

            if (args.length != 1) {
                throw new IllegalArgumentException(
                        "Uso: /scoreboard <true|false>");
            }

            boolean enabled = Boolean.parseBoolean(args[0]);

            PlayerStats stats = plugin
                    .getPlayerStatsService()
                    .getStats(player.getUniqueId());

            stats.setScoreboardEnabled(enabled);

            plugin.getPlayerStatsService().save(player.getUniqueId(), stats);

            if (enabled) {
                plugin.getScoreboardService().update(
                        player,
                        stats.getBlocksBroken());

                sender.sendMessage(
                        Component.text("Scoreboard ativado."));
            } else {
                plugin.getScoreboardService().hide(player);

                sender.sendMessage(
                        Component.text("Scoreboard desativado."));
            }

            return true;

        } catch (IllegalArgumentException exception) {
            sender.sendMessage(
                    Component.text(exception.getMessage()));

            return true;
        }
    }
}
