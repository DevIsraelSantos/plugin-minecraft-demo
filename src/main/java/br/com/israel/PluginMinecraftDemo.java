package br.com.israel;

import org.bukkit.plugin.java.JavaPlugin;

import br.com.israel.commands.PingCommand;
import br.com.israel.commands.PlayCommand;
import br.com.israel.listeners.PlayerJoinListener;
import br.com.israel.services.EffectService;
import br.com.israel.services.MessageService;
import br.com.israel.services.ParOuImparService;

public class PluginMinecraftDemo extends JavaPlugin {
    private MessageService messageService;
    private ParOuImparService parOuImparService;
    private EffectService effectService;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        registerServices();
        registerCommands();
        registerListeners();

        getLogger().info("Plugin iniciado!");
    }

    private void registerServices() {
        this.messageService = new MessageService(getConfig());
        this.parOuImparService = new ParOuImparService();
        this.effectService = new EffectService(getConfig(), this);
    }

    private void registerCommands() {
        PingCommand.register(this);
        PlayCommand.register(this);
    }

    private void registerListeners() {
        PlayerJoinListener.register(this);
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

    public EffectService getEffectService() {
        return this.effectService;
    }

}
