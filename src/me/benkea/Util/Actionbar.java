package me.benkea.Util;

import me.benkea.UHCMain;
import me.dylzqn.Utils.MiscellaneousManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyUHC was created by 35047
 * © at 29/06/15 at 2:03 PM
 */
public class Actionbar {

    public static void startActionBar(final Player p){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(UHCMain.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (!(UHCMain.getState() == GameState.INGAME)) {
                    MiscellaneousManager.sendActionBar(p, UHCMain.UHCTag + "§fTime left in " + UHCMain.getState() + "§7: §a" + UHCMain.formatTime(UHCMain.getTicks()));
                }
                else if (UHCMain.getState()==GameState.INGAME){
                    MiscellaneousManager.sendActionBar(p, UHCMain.UHCTag + "§fYou have been in UHC for §7: §a" + UHCMain.formatTime(UHCMain.getTicks()));
                }
            }
        }, 0, 20);
    }

}
