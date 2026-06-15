package br.com.israel.services;

import org.bukkit.configuration.file.FileConfiguration;

import br.com.israel.models.ParOuImparResult;
import br.com.israel.models.PlayerStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class MessageService {
    private final FileConfiguration config;

    public MessageService(FileConfiguration config) {
        this.config = config;
    }

    public Component getWelcomeMessage() {
        String message = config.getString("welcome-message", "Bem vindo ao servidor!");
        return green(message);
    }

    public Component getPingMessage() {
        String message = config.getString("ping-response", "Pong!");
        return green(message);

    }

    public Component getParOuImparMessage(ParOuImparResult result) {
        String message = config.getString(
                "par-ou-impar-message",
                """
                        ══════════════════
                        Sua escolha: {choice}
                        Seu número: {player}
                        Número sorteado: {system}
                        Total: {total}

                        {result}
                        ══════════════════
                        """);

        String resultGame = result.won()
                ? config.getString("par-ou-impar-win", "Você venceu!")
                : config.getString("par-ou-impar-lose", "Você perdeu!");

        message = message
                .replace("{choice}", result.choice().getDisplayName())
                .replace("{player}", String.valueOf(result.playerValue()))
                .replace("{system}", String.valueOf(result.systemValue()))
                .replace("{total}", String.valueOf(result.total()))
                .replace("{result}", resultGame);

        return result.won()
                ? green(message)
                : red(message);
    }

    public Component getPlayerStatsMessage(PlayerStats stats) {
        String message = config.getString("par-ou-impar-stats",
                """
                            ══════════════════
                            Partidas: {games}
                            Vitórias: {wins}
                            Derrotas: {losses}
                            Taxa de vitória: {percentage}

                            ══════════════════
                        """);

        message = message.replace("{games}", stats.getGames().toString())
                .replace("{wins}", stats.getWins().toString())
                .replace("{losses}", stats.getLosses().toString())
                .replace("{percentage}", stats.getPercentage());

        return white(message);
    }

    private Component white(String message) {
        return Component.text(
                message,
                NamedTextColor.WHITE);
    }

    private Component green(String message) {
        return Component.text(
                message,
                NamedTextColor.GREEN);
    }

    private Component red(String message) {
        return Component.text(
                message,
                NamedTextColor.RED);
    }

}
