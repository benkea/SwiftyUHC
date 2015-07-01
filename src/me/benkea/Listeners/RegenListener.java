package me.benkea.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

/**
 * Created by 35047 on 9/04/15.
 */
public class RegenListener implements Listener {

    @EventHandler
    public void onRegen(EntityRegainHealthEvent e){
        EntityRegainHealthEvent.RegainReason reason = e.getRegainReason();
        if (reason==EntityRegainHealthEvent.RegainReason.SATIATED || reason== EntityRegainHealthEvent.RegainReason.REGEN){
            e.setCancelled(true);
        }
    }

}
