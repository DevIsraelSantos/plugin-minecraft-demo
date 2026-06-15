package br.com.israel;

import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.israel.commands.PingCommand;
import br.com.israel.commands.PlayCommand;
import br.com.israel.commands.ScoreboardCommand;
import br.com.israel.commands.StatsCommand;
import br.com.israel.listeners.BlockBreakListener;
import br.com.israel.listeners.PlayerJoinListener;
import br.com.israel.repositories.PlayerStatsRepository;
import br.com.israel.services.DatabaseService;
import br.com.israel.services.EffectService;
import br.com.israel.services.MessageService;
import br.com.israel.services.ParOuImparService;
import br.com.israel.services.PlayerStatsService;
import br.com.israel.services.ScoreboardService;

public class PluginMinecraftDemo extends JavaPlugin {
    private MessageService messageService;
    private ParOuImparService parOuImparService;
    private EffectService effectService;
    private PlayerStatsService playerStatsService;
    private DatabaseService databaseService;
    private PlayerStatsRepository playerStatsRepository;
    private ScoreboardService scoreboardService;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        registerServices();
        registerCommands();
        registerListeners();

        getLogger().info("Plugin iniciado!");
    }

    private void registerServices() {
        FileConfiguration config = getConfig();
        try {

            this.databaseService = new DatabaseService(this);
            this.playerStatsRepository = new PlayerStatsRepository(this.databaseService);
            this.messageService = new MessageService(config);
            this.parOuImparService = new ParOuImparService();
            this.effectService = new EffectService(this);
            this.playerStatsService = new PlayerStatsService(this.playerStatsRepository);
            this.scoreboardService = new ScoreboardService();

        } catch (SQLException exception) {
            throw new RuntimeException("Falha ao inicializar banco de dados", exception);
        }
    }

    private void registerCommands() {
        PingCommand.register(this);
        PlayCommand.register(this);
        StatsCommand.register(this);
        ScoreboardCommand.register(this);
    }

    private void registerListeners() {
        PlayerJoinListener.register(this);
        BlockBreakListener.register(this);
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

    public PlayerStatsService getPlayerStatsService() {
        return this.playerStatsService;
    }

    public ScoreboardService getScoreboardService() {
        return scoreboardService;
    }
}
