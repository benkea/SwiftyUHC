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
 * © at 27/06/15 at 3:25 PM
 */
public class UHCInfo implements CommandExecutor {

    /*
    To put in Config:
    Strength 1?
    Strength 2?
    GOD Apple?
    Regeneration Potions?
    End?
    Nether?
    Heads?
     */

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player)sender;
        SwiftyPlayer user = new SwiftyPlayer(p);

        if (cmd.getName().equalsIgnoreCase("uhcinfo")){
            if (args.length==0) {
                if (!(user.getRank().equals(RankManager.OWNER))) {
                    p.sendMessage(UHCMain.UHCTag);
                    p.sendMessage(UHCMain.Arrows + "§bStrength 1 Potion? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Strength1"));
                    p.sendMessage(UHCMain.Arrows + "§bStrength 2 Potion? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Strength2"));
                    p.sendMessage(UHCMain.Arrows + "§bRegen Potions? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.RegenPot"));
                    p.sendMessage(UHCMain.Arrows + "§bGod Apple? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.GodApple"));
                    p.sendMessage(UHCMain.Arrows + "§bEnd? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.End"));
                    p.sendMessage(UHCMain.Arrows + "§bNether? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Nether"));
                    p.sendMessage(UHCMain.Arrows + "§bGolden Heads? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Heads"));
                    return true;
                } else if (user.getRank().equals(RankManager.OWNER)) {
                    p.sendMessage(UHCMain.UHCTag);
                    p.sendMessage(UHCMain.Arrows + "§bStrength 1 Potion? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Strength1"));
                    p.sendMessage(UHCMain.Arrows + "§bStrength 2 Potion? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Strength2"));
                    p.sendMessage(UHCMain.Arrows + "§bRegen Potions? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.RegenPot"));
                    p.sendMessage(UHCMain.Arrows + "§bGod Apple? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.GodApple"));
                    p.sendMessage(UHCMain.Arrows + "§bEnd? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.End"));
                    p.sendMessage(UHCMain.Arrows + "§bNether? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Nether"));
                    p.sendMessage(UHCMain.Arrows + "§bGolden Heads? §a" + UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Heads"));
                    return true;
                }
            }
            String subcmd = args[0];
            //uhcinfo set <Toggle> <True|False>
            if (subcmd.equalsIgnoreCase("set")){
                if (args.length<= 2){
                    p.sendMessage(UHCMain.Error+"Usage: /uhcinfo set <Toggle> <True|False>");
                    return true;
                }
                if (!(user.getRank().equals(RankManager.OWNER))){
                    p.sendMessage(UHCMain.PermOWNER);
                    return true;
                }
                String toggle = args[1];
                String statement = args[2];
                if (UHCMain.getInstance().getConfig().get("UHCInfo."+toggle)==null){
                    p.sendMessage(UHCMain.Error+"There is no toggle called "+toggle);
                    p.sendMessage(UHCMain.Arrows+"§bToggles§7:");
                    p.sendMessage(UHCMain.Arrows+"§bStrength1");
                    p.sendMessage(UHCMain.Arrows+"§bStrength2");
                    p.sendMessage(UHCMain.Arrows+"§bRegenPot");
                    p.sendMessage(UHCMain.Arrows+"§bGodApple");
                    p.sendMessage(UHCMain.Arrows+"§bEnd");
                    p.sendMessage(UHCMain.Arrows+"§bNether");
                    p.sendMessage(UHCMain.Arrows+"§bHeads");
                    return true;
                }
                else if (UHCMain.getInstance().getConfig().get("UHCInfo."+toggle)!=null){
                    if (statement.equalsIgnoreCase("true")){
                        UHCMain.getInstance().getConfig().set("UHCInfo."+toggle, true);
                        p.sendMessage(UHCMain.UHCTag+"You changed "+toggle+" to true!");
                        return true;
                    }
                    if (statement.equalsIgnoreCase("false")) {
                        UHCMain.getInstance().getConfig().set("UHCInfo." + toggle, false);
                        p.sendMessage(UHCMain.UHCTag + "You changed " + toggle + " to false!");
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
