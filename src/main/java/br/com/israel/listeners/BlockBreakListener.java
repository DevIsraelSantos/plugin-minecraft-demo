package br.com.israel.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import br.com.israel.PluginMinecraftDemo;
import br.com.israel.models.PlayerStats;

public class BlockBreakListener
        implements Listener {

    private final PluginMinecraftDemo plugin;

    public BlockBreakListener(
            PluginMinecraftDemo plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(
            BlockBreakEvent event) {

        PlayerStats stats = plugin.getPlayerStatsService()
                .incrementBlocks(
                        event.getPlayer()
                                .getUniqueId());

        if (stats.isScoreboardEnabled()) {

            plugin.getScoreboardService()
                    .update(
                            event.getPlayer(),
                            stats.getBlocksBroken());
        }
    }

    public static void register(
            PluginMinecraftDemo plugin) {

        plugin.getServer()
                .getPluginManager()
                .registerEvents(
                        new BlockBreakListener(plugin),
                        plugin);
    }
}
