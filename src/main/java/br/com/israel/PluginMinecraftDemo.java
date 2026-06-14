package br.com.israel;

import org.bukkit.plugin.java.JavaPlugin;

import br.com.israel.commands.PingCommand;
import br.com.israel.listeners.PlayerJoinListener;
import br.com.israel.services.MessageService;

public class PluginMinecraftDemo extends JavaPlugin {
    private MessageService messageService;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        messageService = new MessageService(
                getConfig());

        getLogger().info("Plugin iniciado! - ");

        getCommand("ping").setExecutor(new PingCommand(this));

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin desligado!");
    }

    public MessageService getMessageService() {
        return messageService;
    }
}
