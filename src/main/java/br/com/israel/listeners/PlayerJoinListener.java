package br.com.israel.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.israel.PluginMinecraftDemo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class PlayerJoinListener implements Listener {
    private final PluginMinecraftDemo plugin;

    public PlayerJoinListener(PluginMinecraftDemo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String message = plugin.getConfig().getString("welcome-message", "Bem vindo ao servidor!");

        event.getPlayer().sendMessage(
                Component.text(message, NamedTextColor.GREEN));
    }
}
