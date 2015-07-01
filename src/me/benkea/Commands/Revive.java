package me.benkea.Commands;

import me.benkea.UHCMain;
import me.benkea.Util.GameState;
import me.benkea.Util.Scoreboard;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.RankManager;
import me.dylzqn.Utils.SwiftyTeams;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyUHC was created by 35047
 * © at 27/06/15 at 3:37 PM
 */
public class Revive implements CommandExecutor {
    public static List<Player> cantBeHeart = new ArrayList<Player>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player)sender;
        SwiftyPlayer user = new SwiftyPlayer(p);

        if (cmd.getName().equalsIgnoreCase("revive")) {
            if (UHCMain.getState().equals(GameState.NOPVP) || UHCMain.getState().equals(GameState.INGAME)) {
                if (!(user.getRank().equals(RankManager.OWNER) || user.getRank().equals(RankManager.HEADADMIN))) {
                    p.sendMessage(UHCMain.PermHEADADMIN);
                    return true;
                }
                if (args.length == 0) {
                    p.sendMessage(UHCMain.Error + "Usage: /revive <Player>");
                    return true;
                }
                Player t = Bukkit.getPlayer(args[0]);
                SwiftyPlayer tuser = new SwiftyPlayer(t);
                if (t == null) {
                    p.sendMessage(UHCMain.CantFindPlayer + t.getName());
                    return true;
                }
                if (SwiftyTeams.inTeam("Alive", tuser)) {
                    p.sendMessage(UHCMain.Error + "That player doesn't need reviving!");
                    return true;
                }
                else if (SwiftyTeams.inTeam("Spectator", tuser)) {
                    SwiftyTeams.removePlayer("Spectator", tuser);
                    SwiftyTeams.addPlayer("Alive", tuser);

                    double bordersize = Bukkit.getWorld("world").getWorldBorder().getSize();

                    double x = bordersize - 30;
                    double y = 200;
                    double z = bordersize - 30;

                    tuser.teleport(new Location(Bukkit.getWorld("world"), x, y, z));
                    tuser.setGameMode(GameMode.SURVIVAL);
                    tuser.setPlayerListName("§a" + p.getName());
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        Scoreboard.setScoreboard(all);
                    }
                    cantBeHeart.add(t);
                }
            }
            else {
                p.sendMessage(UHCMain.Error+"You can't do that now!");
            }
        }
        return true;
    }
}
