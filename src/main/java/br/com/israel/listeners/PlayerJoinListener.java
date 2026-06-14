package br.com.israel.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.israel.PluginMinecraftDemo;

public class PlayerJoinListener implements Listener {
    private final PluginMinecraftDemo plugin;

    public static void register(PluginMinecraftDemo plugin) {
        plugin.getServer()
                .getPluginManager()
                .registerEvents(
                        new PlayerJoinListener(plugin),
                        plugin);
    }

    public PlayerJoinListener(PluginMinecraftDemo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(plugin.getMessageService().getWelcomeMessage());
    }
}
