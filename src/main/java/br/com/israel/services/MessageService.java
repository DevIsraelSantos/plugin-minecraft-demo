package br.com.israel.services;

import org.bukkit.configuration.file.FileConfiguration;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class MessageService {
    private final FileConfiguration config;

    public MessageService(FileConfiguration config) {
        this.config = config;
    }

    public Component getWelcomeMessage() {
        String message = config.getString("welcome-message", "Bem vindo ao servidor!");
        return success(message);
    }

    public Component getPingMessage() {
        String message = config.getString("ping-response", "Pong!");
        return success(message);

    }

    private Component success(String message) {
        return Component.text(
                message,
                NamedTextColor.GREEN);
    }

}
