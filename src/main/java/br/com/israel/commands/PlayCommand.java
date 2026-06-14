package br.com.israel.commands;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import br.com.israel.PluginMinecraftDemo;
import br.com.israel.models.ChoiceType;
import br.com.israel.models.ParOuImparResult;
import net.kyori.adventure.text.Component;

public class PlayCommand implements CommandExecutor, TabCompleter {

    private final PluginMinecraftDemo plugin;

    public static void register(PluginMinecraftDemo plugin) {
        PlayCommand command = new PlayCommand(plugin);

        plugin.getCommand("play").setExecutor(command);
        plugin.getCommand("play").setTabCompleter(command);
    }

    public PlayCommand(PluginMinecraftDemo plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(
            CommandSender sender,
            Command command,
            String alias,
            String[] args) {

        if (args.length == 1) {
            return List.of("par", "impar");
        }

        if (args.length == 2) {
            return List.of("<numero>");
        }

        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {

        try {
            validate(args);

            ChoiceType choice = parseChoice(args[0]);
            int value = Integer.parseInt(args[1]);

            ParOuImparResult result = plugin
                    .getParOuImparService()
                    .play(choice, value);

            sender.sendMessage(
                    plugin
                            .getMessageService()
                            .getParOuImparMessage(result));

        } catch (IllegalArgumentException exception) {
            sender.sendMessage(
                    Component.text(exception.getMessage()));
        }

        return true;
    }

    private void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Uso: /play <par|impar> <1-10>");
        }

        if (!args[0].equalsIgnoreCase("par")
                && !args[0].equalsIgnoreCase("impar")) {

            throw new IllegalArgumentException(
                    "A opção deve ser 'par' ou 'impar'");
        }

        int value;

        try {
            value = Integer.parseInt(args[1]);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "O número deve estar entre 1 e 10");
        }

        if (value < 1 || value > 10) {
            throw new IllegalArgumentException(
                    "O número deve estar entre 1 e 10");
        }
    }

    private ChoiceType parseChoice(String choice) {
        return choice.equalsIgnoreCase("par")
                ? ChoiceType.PAR
                : ChoiceType.IMPAR;
    }
}
