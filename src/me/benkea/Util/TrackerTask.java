package me.benkea.Util;

import me.dylzqn.Utils.MiscellaneousManager;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyUHC was created by 35047
 * © at 23/06/15 at 4:50 PM
 */
public class TrackerTask extends BukkitRunnable {

    private Player p;

    public TrackerTask(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        String msg = "§4§lNO TARGET FOUND";

        List<Player>  players = new ArrayList<Player>();
        for (Player all : p.getWorld().getPlayers()){

            if (all.getName().equals(p.getName())){
                players.remove(all);
            }
        }

        Collections.sort(players, new TrackerComparer(p));
        Player nearest = null;

        if (players.size() > 0){
            nearest = players.get(0);
            msg = "§6§l>§e§l> §bTarget§7: §a"+nearest.getName()+" §7| §bDistance§7: §a"+(int)nearest.getLocation().distance(p.getLocation());
        }

        MiscellaneousManager.sendActionBar(p, msg);
        try {
            p.setCompassTarget(nearest != null ? nearest.getLocation() : null);
        } catch (NullPointerException ignore){

        }
    }
}
