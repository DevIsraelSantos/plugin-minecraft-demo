package br.com.israel;

import org.bukkit.plugin.java.JavaPlugin;

import br.com.israel.commands.PingCommand;
import br.com.israel.commands.PlayCommand;
import br.com.israel.listeners.PlayerJoinListener;
import br.com.israel.services.MessageService;
import br.com.israel.services.ParOuImparService;

public class PluginMinecraftDemo extends JavaPlugin {
    private MessageService messageService;
    private ParOuImparService parOuImparService;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.messageService = new MessageService(getConfig());
        this.parOuImparService = new ParOuImparService();

        getLogger().info("Plugin iniciado! - ");

        getCommand("ping").setExecutor(new PingCommand(this));
        getCommand("play").setExecutor(new PlayCommand(this));

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin desligado!");
    }

    public MessageService getMessageService() {
        return this.messageService;
    }

    public ParOuImparService getParOuImparService() {
        return this.parOuImparService;
    }

}
