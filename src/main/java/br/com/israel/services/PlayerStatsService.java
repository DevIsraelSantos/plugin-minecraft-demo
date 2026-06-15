package br.com.israel.services;

import java.sql.SQLException;
import java.util.UUID;

import br.com.israel.models.PlayerStats;
import br.com.israel.repositories.PlayerStatsRepository;

public class PlayerStatsService {

    private final PlayerStatsRepository repository;

    public PlayerStatsService(PlayerStatsRepository repository) {
        this.repository = repository;
    }

    public PlayerStats getStats(UUID playerId) {
        try {
            return repository.findById(playerId);
        } catch (SQLException exception) {
            throw new RuntimeException("Falha ao buscar estatísticas do jogador", exception);
        }
    }

    public PlayerStats registerGame(UUID playerId, boolean won) {
        try {
            PlayerStats stats = this.getStats(playerId);

            if (won) {
                stats.addVictory();
            } else {
                stats.addDefeat();
            }

            repository.save(playerId, stats);

            return stats;
        } catch (SQLException exception) {
            throw new RuntimeException("Falha ao buscar estatísticas do jogador", exception);
        }
    }
}
