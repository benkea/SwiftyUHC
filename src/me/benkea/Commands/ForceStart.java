package me.benkea.Commands;

import me.benkea.UHCMain;
import me.benkea.Util.GameState;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This Document and Code is STRICTLY copyrited to Ben.
 * If anyone takes any part of this code and uses it for
 * Something public, Share with someone, uses it for API's,
 * implementing it to their code and taking the rights for
 * it is NOT allowed unless you get permission from me!
 * This project SwiftyUHC was created by 35047
 * at 10/04/15 at 6:17 PM
 */
public class ForceStart implements CommandExecutor {
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        Player p = (Player)sender;
        SwiftyPlayer user = new SwiftyPlayer(p);

        if (cmd.getName().equalsIgnoreCase("ForceStart")) {
            if (!(user.getRank().equals(RankManager.HEADADMIN) || user.getRank().equals(RankManager.OWNER))) {
                p.sendMessage(UHCMain.PermHEADADMIN);
                return true;
            }
            if (UHCMain.getState()!=GameState.LOBBY){
                p.sendMessage("§c§lNOT NOW!");
                return true;
            }
            UHCMain.setState(GameState.LOADING);
            for (Player world : p.getLocation().getWorld().getPlayers()) {
                world.sendMessage(UHCMain.TAG2 + "§6UHC has been ForceStarted!");
            }
        }
        return true;
    }
}
