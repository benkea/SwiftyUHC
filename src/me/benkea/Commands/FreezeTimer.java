package me.benkea.Commands;

import me.benkea.UHCMain;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.RankManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyUHC was created by 35047
 * © at 28/06/15 at 10:30 PM
 */
public class FreezeTimer implements CommandExecutor {

    public static boolean freezetimer = false;

    public static boolean isFreezetimer() { //Returns if True
        return freezetimer;
    }

    public static void setFreezetimer(boolean freezetimer) {
        FreezeTimer.freezetimer = freezetimer;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player)sender;
        SwiftyPlayer user = new SwiftyPlayer(p);

        if (cmd.getName().equalsIgnoreCase("freezetimer")){
            if (!(user.getRank().equals(RankManager.OWNER))){
                user.sendMessage(UHCMain.PermOWNER);
                return true;
            }
            if (!isFreezetimer()){
                setFreezetimer(true);
                p.sendMessage(UHCMain.UHCTag+"You have frozen the timer!");
                return true;
            }
            else if (isFreezetimer()){
                setFreezetimer(false);
                p.sendMessage(UHCMain.UHCTag+"You have unfrozen the timer!");
                return true;
            }
        }
        return true;
    }
}
