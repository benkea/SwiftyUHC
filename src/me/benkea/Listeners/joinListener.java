package me.benkea.Listeners;

import me.benkea.UHCMain;
import me.benkea.Util.Actionbar;
import me.benkea.Util.GameState;
import me.benkea.Util.Scoreboard;
import me.benkea.Util.Spec;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.SwiftyTeams;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by 35047 on 8/04/15.
 */
public class joinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        SwiftyPlayer user = new SwiftyPlayer(p);
        for (Player all : Bukkit.getOnlinePlayers()) {
            Actionbar.startActionBar(all);
            if (UHCMain.getState() == GameState.LOADING || UHCMain.getState() == GameState.WARMUP || UHCMain.getState() == GameState.LOADINGDMATCH
                    || UHCMain.getState() == GameState.DMATCHWARMUP || UHCMain.getState() == GameState.DEATHMATCH) {
                Bukkit.getMessenger().registerOutgoingPluginChannel(UHCMain.getInstance(), "BungeeCord");
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                try {
                    out.writeUTF("Connect");
                    out.writeUTF("HB1");
                } catch (IOException ex) {
                }
                p.sendPluginMessage(UHCMain.getInstance(), "BungeeCord", b.toByteArray());
            }
            if (UHCMain.getState() != GameState.LOBBY) {
                p.teleport(Bukkit.getWorld("lobby").getSpawnLocation());
                Spec.setSpectator(p);
                p.sendMessage(UHCMain.UHCTag + "You are now a Spectator!");
            }
            if (UHCMain.getState() == GameState.LOBBY || UHCMain.getState() == GameState.STARTING) {
                p.teleport(Bukkit.getWorld("lobby").getSpawnLocation());
                p.setHealth(p.getMaxHealth());
                p.setFoodLevel(20);
                p.setSaturation(8F);
                p.setLevel(0);
                p.setExp(0F);
                p.setFireTicks(0);
                p.getInventory().clear();
                p.getInventory().setArmorContents(null);
                user.removeALLPotionEffects();
                Scoreboard.setScoreboard(all);
                SwiftyTeams.addPlayer("Alive", user);
                user.setPlayerListName("§a" + p.getName());
                p.sendMessage(UHCMain.TAG2 + "§6Welcome to UHC!");
                int players = Bukkit.getOnlinePlayers().size();
                if (players < UHCMain.MIN_PLAYERS) {
                    p.sendMessage(UHCMain.TAG2 + "§6The game will start when we get " + UHCMain.MIN_PLAYERS + " players!");
                } else {
                    p.sendMessage(UHCMain.TAG2 + "§6The game will start soon...");
                }
                p.sendMessage("§c§lWARNING: §c§nFor Right Now, §cWhen you leave and join back, You will become a spectator again!");
            }
        }
    }
}
