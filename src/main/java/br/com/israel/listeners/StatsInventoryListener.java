package br.com.israel.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import br.com.israel.PluginMinecraftDemo;
import br.com.israel.inventories.StatsInventory;
import br.com.israel.models.PlayerStats;
import net.kyori.adventure.text.Component;

public class StatsInventoryListener implements Listener {

    private final PluginMinecraftDemo plugin;

    public StatsInventoryListener(PluginMinecraftDemo plugin) {

        this.plugin = plugin;
    }

    public static void register(PluginMinecraftDemo plugin) {
        plugin.getServer().getPluginManager().registerEvents(new StatsInventoryListener(plugin), plugin);

    }

    @EventHandler
    public void onInventoryClick(
            InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        if (!event.getView().title().equals(Component.text(StatsInventory.TITLE))) {

            return;
        }

        event.setCancelled(true);

        if (event.getSlot() != 15) {
            return;
        }

        PlayerStats stats = plugin.getPlayerStatsService().getStats(player.getUniqueId());

        stats.setScoreboardEnabled(!stats.isScoreboardEnabled());

        plugin.getPlayerStatsService().save(player.getUniqueId(), stats);

        if (stats.isScoreboardEnabled()) {

            plugin.getScoreboardService().update(player, stats.getBlocksBroken());

        } else {

            plugin.getScoreboardService().hide(player);
        }

        new StatsInventory().open(player, stats);
    }
}
