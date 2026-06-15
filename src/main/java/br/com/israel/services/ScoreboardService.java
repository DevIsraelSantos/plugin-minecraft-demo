package br.com.israel.services;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class ScoreboardService {

    public void update(Player player, Integer blocksBroken) {

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("blocks", Criteria.DUMMY,
                Component.text("BLOCOS", NamedTextColor.GOLD));

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // objective.getScore(" ").setScore(2);

        objective.getScore("§fQuebrados: §a" + blocksBroken).setScore(1);

        player.setScoreboard(scoreboard);
    }

    public void hide(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }
}
