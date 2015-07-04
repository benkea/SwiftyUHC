package me.benkea.Listeners;

import me.WingedMLGPro.API.StatsAPI;
import me.benkea.Commands.Revive;
import me.benkea.UHCMain;
import me.benkea.Util.GameState;
import me.benkea.Util.Scoreboard;
import me.benkea.Util.TrackerTask;
import me.benkea.Util.setSpectator;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.SwiftyTeams;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 35047 on 8/04/15.
 */
public class UtilListeners implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        SwiftyPlayer user = new SwiftyPlayer(e.getPlayer());
        if (UHCMain.getState() != GameState.INGAME && UHCMain.getState() != GameState.NOPVP || SwiftyTeams.inTeam("Spectator", user)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        SwiftyPlayer user = new SwiftyPlayer(e.getPlayer());
        if (UHCMain.getState() != GameState.INGAME && UHCMain.getState() != GameState.NOPVP && UHCMain.getState() != GameState.DEATHMATCH || SwiftyTeams.inTeam("Spectator", user)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            SwiftyPlayer user = new SwiftyPlayer(p);
            if (UHCMain.getState() != GameState.INGAME && UHCMain.getState() != GameState.NOPVP && UHCMain.getState() != GameState.DEATHMATCH || SwiftyTeams.inTeam("Spectator", user)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            SwiftyPlayer user = new SwiftyPlayer(p);
            if (UHCMain.getState() != GameState.INGAME && UHCMain.getState() != GameState.NOPVP && UHCMain.getState() != GameState.DEATHMATCH || SwiftyTeams.inTeam("Spectator", user)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPUPItems(PlayerPickupItemEvent e) {
        SwiftyPlayer user = new SwiftyPlayer(e.getPlayer());
        if (UHCMain.getState() != GameState.INGAME && UHCMain.getState() != GameState.NOPVP && UHCMain.getState() != GameState.DEATHMATCH || SwiftyTeams.inTeam("Spectator", user)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItems(PlayerDropItemEvent e) {
        SwiftyPlayer user = new SwiftyPlayer(e.getPlayer());
        if (UHCMain.getState() != GameState.INGAME && UHCMain.getState() != GameState.NOPVP && UHCMain.getState() != GameState.DEATHMATCH || SwiftyTeams.inTeam("Spectator", user)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        SwiftyPlayer user = new SwiftyPlayer(p);
        if (UHCMain.getInstance().getConfig().getBoolean("UHCInfo.Heads")==true) {
            ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwner(p.getName());
            meta.setDisplayName("§6" + p.getName() + "'s head §7§o(Right Click)");
            head.setItemMeta(meta);
            p.getWorld().dropItemNaturally(p.getLocation(), head);
        }
        p.teleport(Bukkit.getWorld("lobby").getSpawnLocation());
        setSpectator.setSpectator(p);
        p.sendMessage(UHCMain.UHCTag + "You are now a spectator!");
        StatsAPI.addUHCDeaths(1, p);
        StatsAPI.addUHCGamesPlayed(1, p);
        if (p.getKiller() != null) {
            StatsAPI.addUHCKills(1, p.getKiller());
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(UHCMain.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (SwiftyTeams.getTeamSize("Alive") == 1) {
                    Bukkit.broadcastMessage(UHCMain.UHCTag + SwiftyTeams.getMembers("Alive") + " has won UHC!");
                    Player player = Bukkit.getPlayer(SwiftyTeams.getMembers("Alive"));
                    StatsAPI.addUHCWins(1, player);
                    UHCMain.setTicks(11);
                    UHCMain.setState(GameState.ENDED);
                }
            }
        }, 20);
        Bukkit.getScheduler().scheduleSyncDelayedTask(UHCMain.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (SwiftyTeams.getTeamSize("Alive") == 0) {
                    Bukkit.broadcastMessage(UHCMain.UHCTag +"No one has won UHC!");
                    UHCMain.setTicks(11);
                    UHCMain.setState(GameState.ENDED);
                }
            }
        }, 20);
    }

    @EventHandler
    public void onEDamgeByE(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player d = (Player) e.getDamager();
                if (UHCMain.getState() == GameState.NOPVP) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onDisconnect(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        SwiftyPlayer user = new SwiftyPlayer(p);
        if (SwiftyTeams.inTeam("Spectator", user)) {
            SwiftyTeams.removePlayer("Spectator", user);
        }
        if (SwiftyTeams.inTeam("Alive", user)) {
            SwiftyTeams.removePlayer("Alive", user);
        }
        if (!(UHCMain.getState() == GameState.LOBBY || UHCMain.getState() == GameState.WARMUP || UHCMain.getState() == GameState.LOADING || UHCMain.getState() == GameState.STARTING)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(UHCMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (SwiftyTeams.getTeamSize("Alive") == 1) {
                        Bukkit.broadcastMessage(UHCMain.UHCTag + SwiftyTeams.getMembers("Alive") + " has won UHC!");
                        Player player = Bukkit.getPlayer(SwiftyTeams.getMembers("Alive"));
                        StatsAPI.addUHCWins(1, player);
                        UHCMain.setTicks(11);
                        UHCMain.setState(GameState.ENDED);
                    }
                }
            }, 20);
            Bukkit.getScheduler().scheduleSyncDelayedTask(UHCMain.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (SwiftyTeams.getTeamSize("Alive") == 0) {
                        Bukkit.broadcastMessage(UHCMain.UHCTag + "No one has won UHC!");
                        UHCMain.setTicks(11);
                        UHCMain.setState(GameState.ENDED);
                    }
                }
            }, 20);
        }
    }
    @EventHandler
    public void onHeadInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack hand = p.getItemInHand();
        if (a==Action.RIGHT_CLICK_BLOCK || a==Action.RIGHT_CLICK_AIR){
            if (hand != null){
                if (hand.getType().equals(Material.SKULL_ITEM)) {
                    e.setCancelled(true);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * 20, 1));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 120 * 20, 0));
                    p.sendMessage(UHCMain.UHCTag + "You will now regen §a3 hearts §band have absorption for §a2 minutes§b!");
                    p.getInventory().remove(p.getItemInHand());
                    StatsAPI.addUHCHeadsEaten(1, p);
                }
            }
        }
    }
    @EventHandler
    public void onEntityDamage(EntityDeathEvent e){
        if (e.getEntity() instanceof Ghast){
            e.getDrops().remove(Material.GHAST_TEAR);
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.GOLD_INGOT, 1));
        }
    }
    @EventHandler
    public void onFallDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                if (Revive.cantBeHeart.contains(p)){
                    e.setCancelled(true);
                    Revive.cantBeHeart.remove(p);
                }
            }
        }
    }
    @EventHandler
    public void onInVoid(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (UHCMain.getState()==GameState.LOBBY){
            if (p.getLocation().getY()<=0){
                p.teleport(Bukkit.getWorld("lobby").getSpawnLocation());
                p.sendMessage(UHCMain.Error+"Don't go too far down!");
            }
        }
    }
    @EventHandler
    public void onCompassTracker(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (p.getItemInHand().getType().equals(Material.COMPASS)){
            new TrackerTask(p).runTaskTimer(UHCMain.getInstance(), 0L, 20L);
        }
    }
}
