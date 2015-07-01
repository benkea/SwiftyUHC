package me.benkea.Listeners;

import me.benkea.UHCMain;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyUHC was created by 35047
 * © at 28/06/15 at 1:21 PM
 */
public class TogglesEvents implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void cancelStrength1(PlayerItemConsumeEvent e){
        Player p =e.getPlayer();
        if (UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Strength1")==false) {
            if (e.getItem().getType().equals(Material.POTION)) {
                Potion potion = Potion.fromItemStack(p.getItemInHand());
                if (potion.equals(PotionType.STRENGTH) && potion.getLevel() == 1) {
                    e.setCancelled(true);
                    p.sendMessage(UHCMain.Error + "You are not allowed to use Strength 1 Potions!");
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void cancelStrength2(PlayerItemConsumeEvent e){
        Player p =e.getPlayer();
        if (UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Strength2")==false) {
            if (e.getItem().getType().equals(Material.POTION)) {
                Potion potion = Potion.fromItemStack(p.getItemInHand());
                if (potion.equals(PotionType.STRENGTH) && potion.getLevel() == 2) {
                    e.setCancelled(true);
                    p.sendMessage(UHCMain.Error + "You are not allowed to use Strength 2 Potions!");
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void cancelRegen(PlayerItemConsumeEvent e){
        Player p =e.getPlayer();
        if (UHCMain.getInstance().getConfig().getBoolean("UHCInfo.RegenPot")==false) {
            if (e.getItem().getType().equals(Material.POTION)) {
                Potion potion = Potion.fromItemStack(p.getItemInHand());
                if (potion.equals(PotionType.REGEN)) {
                    e.setCancelled(true);
                    p.sendMessage(UHCMain.Error + "You are not allowed to use Regen Potions!");
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void cancelNether(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (e.getTo().getBlock().getType().equals(Material.PORTAL) && UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Nether")==false){
            e.setTo(e.getFrom().subtract(1.5, 0, 0));
            p.sendMessage(UHCMain.Error+"The Nether is disabled in this UHC!");
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void cancelEnd(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (e.getTo().getBlock().getType().equals(Material.ENDER_PORTAL) && UHCMain.getInstance().getConfig().getBoolean("UHCInfo.End")==false){
            e.setTo(e.getFrom().subtract(1.5, 0, 0));
            p.sendMessage(UHCMain.Error+"The End is disabled in this UHC!");
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCraft(CraftItemEvent e){
        if (e.getRecipe().getResult().isSimilar(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1))){
            e.setResult(Event.Result.DENY);
            e.getWhoClicked().closeInventory();
            e.getWhoClicked().sendMessage(UHCMain.Error+"Golden Apples are disabled in this game!");
        }
    }
}
