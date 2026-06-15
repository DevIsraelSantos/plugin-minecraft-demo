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
                SELECT games, wins
                FROM player_stats
                WHERE uuid = ?
                """;

        PreparedStatement statement = databaseService
                .getConnection()
                .prepareStatement(sql);

        statement.setString(1, playerId.toString());

        ResultSet result = statement.executeQuery();

        if (!result.next()) {
            return new PlayerStats(0, 0);
        }

        return new PlayerStats(
                result.getInt("games"),
                result.getInt("wins"));
    }

    public void save(
            UUID playerId,
            PlayerStats stats)
            throws SQLException {

        String sql = """
                INSERT INTO player_stats (
                    uuid,
                    games,
                    wins
                )
                VALUES (?, ?, ?)
                ON CONFLICT(uuid)
                DO UPDATE SET
                    games = excluded.games,
                    wins = excluded.wins
                """;

        PreparedStatement statement = databaseService
                .getConnection()
                .prepareStatement(sql);

        statement.setString(1, playerId.toString());
        statement.setInt(2, stats.getGames());
        statement.setInt(3, stats.getWins());

        statement.executeUpdate();
    }
}
