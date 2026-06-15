package br.com.israel.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.com.israel.models.PlayerStats;

public class PlayerStatsService {

    private final Map<UUID, PlayerStats> playerStats = new HashMap<>();

    public PlayerStatsService() {
    }

    public PlayerStats getStats(UUID playerId) {
        return playerStats.getOrDefault(
                playerId,
                new PlayerStats(0, 0));
    }

    public PlayerStats registerGame(
            UUID playerId,
            boolean won) {

        PlayerStats stats = getStats(playerId);

        if (won) {
            stats.addVictory();
        } else {
            stats.addDefeat();
        }

        playerStats.put(playerId, stats);

        return stats;
    }
}
