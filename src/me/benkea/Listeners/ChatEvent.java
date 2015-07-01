package me.benkea.Listeners;

import me.benkea.UHCMain;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.SwiftyTeams;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by 35047 on 9/04/15.
 */
public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        SwiftyPlayer user = new SwiftyPlayer(p);
        String msg = e.getMessage();
        if (SwiftyTeams.inTeam("Alive", user)){
            e.setFormat("§a"+p.getName()+"§7: §e"+msg);
        }
        else if (SwiftyTeams.inTeam("Spectator", user)){
            e.setCancelled(true);
            SwiftyTeams.sendMessage("Spectator", user.getRankColour()+"§7: §6"+msg);
        }
        else  if (!(SwiftyTeams.inTeam("Alive", user) && SwiftyTeams.inTeam("Spectator", user))){
            e.setCancelled(true);
            p.sendMessage(UHCMain.Error+"It appears you are not in a team! Plz contact one of the Staff!");
        }
    }
}
