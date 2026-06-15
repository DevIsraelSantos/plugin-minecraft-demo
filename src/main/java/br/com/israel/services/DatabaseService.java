package br.com.israel.services;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.israel.PluginMinecraftDemo;

public class DatabaseService {

    private final Connection connection;

    public DatabaseService(
            PluginMinecraftDemo plugin)
            throws SQLException {

        File databaseFile = new File(
                plugin.getDataFolder(),
                "database.db");

        this.connection = DriverManager.getConnection(
                "jdbc:sqlite:" + databaseFile.getAbsolutePath());

        createTables();
    }

    public Connection getConnection() {
        return connection;
    }

    private void createTables()
            throws SQLException {

        String sql = """
                CREATE TABLE IF NOT EXISTS player_stats (
                    uuid TEXT PRIMARY KEY,
                    games INTEGER NOT NULL,
                    wins INTEGER NOT NULL
                )
                """;

        connection.createStatement()
                .execute(sql);
    }
}
