package me.benkea.Util;

import org.bukkit.entity.Player;

import java.util.Comparator;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyUHC was created by 35047
 * © at 23/06/15 at 4:41 PM
 */
public class TrackerComparer implements Comparator<Player> {

    private Player p;

    public TrackerComparer(Player p) {
        this.p = p;
    }

    @Override
    public int compare(Player target1, Player target2) {
        return compare(target1.getLocation().distance(p.getCompassTarget()), target2.getLocation().distance(p.getLocation()));
    }

    private int compare(double a, double b){
        return a > b ? -1 : a > b ? 1 : 0;
    }
}
