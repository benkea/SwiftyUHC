package me.benkea.Commands;

import me.benkea.UHCMain;
import me.benkea.Util.Modes;
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
 * © at 30/06/15 at 10:55 AM
 */
public class Mode implements CommandExecutor {

    // /setmode <Mode>

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player)sender;

        if (cmd.getName().equalsIgnoreCase("setmode")){
            if (args.length==0){
                p.sendMessage(UHCMain.Error+"Usage: /setmode <Mode>");
                p.sendMessage(UHCMain.Arrows+"§bModes§7: §aFFA, SPEED, CUTCLEAN");
                return true;
            }
            String mode = args[0];
            if (mode.equalsIgnoreCase("FFA")){
                UHCMain.setMode(Modes.FFA);
                p.sendMessage(UHCMain.UHCTag+"You set the Mode to FFA");
                return true;
            }
            if (mode.equalsIgnoreCase("SPEED")){
                UHCMain.setMode(Modes.SPEED);
                p.sendMessage(UHCMain.UHCTag+"You set the Mode to SPEED");
                return true;
            }
            if (mode.equalsIgnoreCase("CUTCLEAN")){
                UHCMain.setMode(Modes.CUTCLEAN);
                p.sendMessage(UHCMain.UHCTag+"You set the Mode to CUTCLEAN");
                return true;
            }
            else if (!mode.equalsIgnoreCase("CUTCLEAN") && !mode.equalsIgnoreCase("SPEED") && !mode.equalsIgnoreCase("FFA")) {
                p.sendMessage(UHCMain.Error+"Usage: /setmode <Mode>");
                p.sendMessage(UHCMain.Arrows+"§bModes§7: §aFFA, SPEED, CUTCLEAN");
                return true;
            }
        }
        return true;
    }
}
