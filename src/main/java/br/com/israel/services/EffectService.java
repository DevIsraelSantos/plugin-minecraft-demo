package br.com.israel.services;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import br.com.israel.PluginMinecraftDemo;

public class EffectService {
    private final FileConfiguration config;
    private final PluginMinecraftDemo plugin;

    public EffectService(FileConfiguration config, PluginMinecraftDemo plugin) {
        this.config = config;
        this.plugin = plugin;
    }

    public void playWinEffect(Player player) {
        player.playSound(
                player.getLocation(),
                Sound.ENTITY_FIREWORK_ROCKET_LAUNCH,
                1.0f,
                1.0f);

        player.spawnParticle(
                Particle.FIREWORK,
                player.getLocation().add(0, 1.5, 0),
                100,
                0.6,
                0.6,
                0.6,
                0.15);

        plugin.getServer().getScheduler().runTaskLater(
                plugin,
                () -> {
                    player.playSound(
                            player.getLocation(),
                            Sound.ENTITY_FIREWORK_ROCKET_BLAST,
                            1.0f,
                            1.2f);

                    player.spawnParticle(
                            Particle.TOTEM_OF_UNDYING,
                            player.getLocation().add(0, 1.5, 0),
                            60,
                            0.4,
                            0.4,
                            0.4,
                            0.1);
                },
                8L);

        plugin.getServer().getScheduler().runTaskLater(
                plugin,
                () -> {
                    player.playSound(
                            player.getLocation(),
                            Sound.ENTITY_PLAYER_LEVELUP,
                            1.0f,
                            1.4f);

                    player.spawnParticle(
                            Particle.HAPPY_VILLAGER,
                            player.getLocation().add(0, 1.2, 0),
                            40,
                            0.8,
                            0.8,
                            0.8,
                            0);
                },
                16L);

    }

    public void playLoseEffect(Player player) {
        player.playSound(
                player.getLocation(),
                Sound.ENTITY_VILLAGER_NO,
                1.0f,
                1.0f);
    }
}
