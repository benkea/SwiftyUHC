package me.benkea.Commands;

import me.benkea.Util.Spec;
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
 * © at 30/06/15 at 9:41 AM
 */
public class SpecTest implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        Player p = (Player)sender;

        if (cmd.getName().equalsIgnoreCase("spectest")){
            Spec.setSpectator(p);
            p.sendMessage("You are now testing being a spectator!");
        }
        return true;
    }
}
