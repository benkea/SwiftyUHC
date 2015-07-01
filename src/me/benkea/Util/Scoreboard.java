package me.benkea.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyUHC was created by 35047
 * © at 23/06/15 at 10:18 AM
 */
public class Scoreboard {

    public static void setScoreboard(Player p){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();

        Objective obj = board.registerNewObjective("health", "health");
        obj.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        int health = (int) p.getHealth();
        obj.getScore(p.getName()).setScore(health);
        p.setScoreboard(board);
    }

}
