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
 * © at 24/04/15 at 6:09 PM
 */
public class information implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player)sender;
        SwiftyPlayer user = new SwiftyPlayer(p);

        if (cmd.getName().equalsIgnoreCase("UHCTest")){
            if (!(user.getRank().equals(RankManager.OWNER))){
                p.sendMessage(UHCMain.PermOWNER);
                return true;
            }
            p.sendMessage("§6Current Ticks: "+ String.valueOf(UHCMain.getTicks()));
            p.sendMessage("§6Current State: " + String.valueOf(UHCMain.getState()));
            p.sendMessage("§6Current Time Format: "+UHCMain.formatTime(UHCMain.getTicks()));
            p.sendMessage("§6Current World: "+p.getWorld().getName());
        }
        return true;
    }
}
