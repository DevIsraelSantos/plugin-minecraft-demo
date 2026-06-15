package br.com.israel.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import br.com.israel.models.PlayerStats;
import br.com.israel.services.DatabaseService;

public class PlayerStatsRepository {

    private final DatabaseService databaseService;

    public PlayerStatsRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public PlayerStats findById(UUID playerId) throws SQLException {

        String sql = """
                SELECT
                    games,
                    wins,
                    blocks_broken,
                    scoreboard_enabled
                FROM player_stats
                WHERE uuid = ?
                """;

        PreparedStatement statement = databaseService
                .getConnection()
                .prepareStatement(sql);

        statement.setString(1, playerId.toString());

        ResultSet result = statement.executeQuery();

        if (!result.next()) {
            return new PlayerStats(0, 0, 0, true);
        }

        return new PlayerStats(
                result.getInt("games"),
                result.getInt("wins"),
                result.getInt("blocks_broken"),
                result.getInt("scoreboard_enabled") == 1);
    }

    public void save(
            UUID playerId,
            PlayerStats stats)
            throws SQLException {

        String sql = """
                INSERT INTO player_stats (
                    uuid,
                    games,
                    wins,
                    blocks_broken,
                    scoreboard_enabled
                )
                VALUES (?, ?, ?, ?, ?)
                ON CONFLICT(uuid)
                DO UPDATE SET
                    games = excluded.games,
                    wins = excluded.wins,
                    blocks_broken = excluded.blocks_broken,
                    scoreboard_enabled = excluded.scoreboard_enabled
                """;

        PreparedStatement statement = databaseService
                .getConnection()
                .prepareStatement(sql);

        statement.setString(1, playerId.toString());
        statement.setInt(2, stats.getGames());
        statement.setInt(3, stats.getWins());
        statement.setInt(4, stats.getBlocksBroken());
        statement.setInt(5, stats.isScoreboardEnabled() ? 1 : 0);

        statement.executeUpdate();
    }
}
