package me.benkea.Commands;

import me.benkea.UHCMain;
import me.benkea.Util.GameState;
import me.benkea.Util.TimeManager;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.SwiftyTeams;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyUHC was created by 35047
 * © at 30/06/15 at 2:35 PM
 */
public class dmatch implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player)sender;

        if (cmd.getName().equalsIgnoreCase("dmatch")){
            for (Player pl : Bukkit.getOnlinePlayers()) {
                SwiftyPlayer user = new SwiftyPlayer(pl);
                if (SwiftyTeams.inTeam("Alive", user)) {
                    Random r = new Random();
                    double x = r.nextInt(175) + 1;
                    double y = 200;
                    double z = r.nextInt(175) + 1;
                    user.teleport(new Location(user.getWorld(), x, y, z));
                    user.sendMessage(UHCMain.UHCTag + "The DeathMatch is starting!");
                    Bukkit.getWorld("world").getWorldBorder().setSize(400);
                    UHCMain.setTicks(TimeManager.DMATCH_WARMUP_SECONS);
                    UHCMain.setState(GameState.DMATCHWARMUP);
                }
            }
        }
        return true;
    }
}
