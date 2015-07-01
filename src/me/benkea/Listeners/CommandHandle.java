package me.benkea.Listeners;

import me.benkea.UHCMain;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyUHC was created by 35047
 * © at 22/05/15 at 11:53 PM
 */
public class CommandHandle implements Listener {

    @EventHandler
    public void onCmdHandle(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        SwiftyPlayer user = new SwiftyPlayer(p);
        String msg = e.getMessage();
        if (msg.equalsIgnoreCase("/reload")){
            if (user.getRank().equals(RankManager.OWNER)){
                Bukkit.reload();
                user.sendMessage(UHCMain.TAG+"§d§lUHC §6 has been reloaded §a§lSuccessfully!");
            }
            else {
                user.sendMessage(UHCMain.PermOWNER);
            }
        }
        if (msg.equalsIgnoreCase("/restart")){
            if (user.getRank().equals(RankManager.OWNER)){
                Bukkit.getMessenger().registerOutgoingPluginChannel(UHCMain.getInstance(), "BungeeCord");
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                try {
                    out.writeUTF("Connect");
                    out.writeUTF("lobby");
                } catch (IOException ex) {
                }
                p.sendPluginMessage(UHCMain.getInstance(), "BungeeCord", b.toByteArray());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
            }
            else {
                user.sendMessage(UHCMain.PermOWNER);
            }
        }
    }

}
