package me.benkea.Util;

import me.WingedMLGPro.API.StatsAPI;
import me.benkea.UHCMain;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.SqlStatements.CreditSql;
import me.dylzqn.Utils.SwiftyTeams;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyUHC was created by 35047
 * © at 29/06/15 at 1:43 PM
 */
public class setSpectator implements Listener {

    public static void setSpectator(Player p){
        for (Player all : Bukkit.getOnlinePlayers()){
            SwiftyPlayer user = new SwiftyPlayer(p);
            SwiftyTeams.removePlayer("Alive", user);
            SwiftyTeams.addPlayer("Spectator", user);
            all.hidePlayer(p);
            user.setPlayerListName("§8§o" + p.getName());
            Scoreboard.setScoreboard(all);
            p.setHealth(p.getMaxHealth());
            p.setFoodLevel(20);
            p.setSaturation(8F);
            p.setLevel(0);
            p.setExp(0F);
            p.setFireTicks(0);
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            PlayerInventory pi = p.getInventory();

            {
                ItemStack Alive = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta meta = Alive.getItemMeta();
                meta.setDisplayName("§6Alive Players §7§o(Right Click)");
                Alive.setItemMeta(meta);
                pi.setItem(0, Alive);
            }
            {
                ItemStack Spectator = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta meta = Spectator.getItemMeta();
                meta.setDisplayName("§6Spectators §7§o(Right Click)");
                Spectator.setItemMeta(meta);
                pi.setItem(1, Spectator);
            }
            {
                ItemStack pinfo = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta meta = (SkullMeta) pinfo.getItemMeta();
                meta.setOwner(p.getName());
                meta.setDisplayName("§6Player Information §7§o(Right Click)");
                pinfo.setItemMeta(meta);
                pi.setItem(7, pinfo);
            }
            {
                ItemStack toHub = new ItemStack(Material.WOOD_DOOR);
                ItemMeta meta = toHub.getItemMeta();
                meta.setDisplayName("§6Go to hub §7§o(Right Click)");
                toHub.setItemMeta(meta);
                pi.setItem(8, toHub);
            }
        }
    }
    public Inventory getAlivePlayersInv(){
        Inventory inv = Bukkit.createInventory(null, 54, "§6Alive Players");
        return inv;
    }
    public Inventory getSpectatorsInv(){
        Inventory inv = Bukkit.createInventory(null, 54, "§6Spectators");
        return inv;
    }
    public Inventory getPlayerInfoInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "§6PlayerInfo`");
        SwiftyPlayer user = new SwiftyPlayer(p);

        {
            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) DyeColor.YELLOW.getData());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e§lSWIFTY §6§lPVP");
            List lore = new ArrayList();
            lore.add("§e§oPublic §6§nAlpha");
            lore.add("§6Version §e1.8 §6§l - §e1.8.7");
            lore.add("§eSite§6: §3§nwww.swiftypvp.com");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(1, item);
            inv.setItem(3, item);
            inv.setItem(5, item);
            inv.setItem(7, item);
            inv.setItem(9, item);
            inv.setItem(17, item);
            inv.setItem(27, item);
            inv.setItem(29, item);
            inv.setItem(31, item);
            inv.setItem(33, item);
            inv.setItem(35, item);
        }
        {
            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) DyeColor.ORANGE.getData());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e§lSWIFTY §6§lPVP");
            List lore = new ArrayList();
            lore.add("§e§oPublic §6§nAlpha");
            lore.add("§6Version §e1.8 §6§l - §e1.8.7");
            lore.add("§eSite§6: §3§nwww.swiftypvp.com");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(0, item);
            inv.setItem(2, item);
            inv.setItem(4, item);
            inv.setItem(6, item);
            inv.setItem(8, item);
            inv.setItem(18, item);
            inv.setItem(26, item);
            inv.setItem(28, item);
            inv.setItem(30, item);
            inv.setItem(32, item);
            inv.setItem(34, item);
        }
        {
            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) DyeColor.BLACK.getData());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e§lSWIFTY §6§lPVP");
            List lore = new ArrayList();
            lore.add("§e§oPublic §6§nAlpha");
            lore.add("§6Version §e1.8 §6§l - §e1.8.7");
            lore.add("§eSite§6: §3§nwww.swiftypvp.com");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(11, item);
            inv.setItem(13, item);
            inv.setItem(15, item);
            inv.setItem(19, item);
            inv.setItem(21, item);
            inv.setItem(23, item);
            inv.setItem(25, item);
        }
        {
            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) DyeColor.GRAY.getData());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e§lSWIFTY §6§lPVP");
            List lore = new ArrayList();
            lore.add("§e§oPublic §6§nAlpha");
            lore.add("§6Version §e1.8 §6§l - §e1.8.7");
            lore.add("§eSite§6: §3§nwww.swiftypvp.com");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(24, item);
        }
        {
            ItemStack item = new ItemStack(Material.GOLD_INGOT);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e§lCredits Balance");
            List lore = new ArrayList();
            lore.add("§e§lCredits§6§l: §a" + CreditSql.getCredits(p));
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(20, item);
        }
        {
            ItemStack item = new ItemStack(Material.WORKBENCH);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§4§lRank§c§l:");
            List lore = new ArrayList();
            lore.add(user.getRank());
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(22, item);
        }
        {
            ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§4§lSurvival Games:");
            List lore = new ArrayList();
            lore.add("§7§o(Coming Soon)");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(16, item);
        }
        {
            ItemStack item = new ItemStack(Material.IRON_SWORD);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§3§lBattle Arena:");
            List lore = new ArrayList();
            lore.add("§6Kills: §a" + StatsAPI.getBAKills(p));
            lore.add("§6Deaths: §a" + StatsAPI.getBADeaths(p));
            lore.add("§6KillStreak: §a" + StatsAPI.getBAKillStreak(p));
            lore.add("§6Highest KillStreak: §a" + StatsAPI.getBAHKillStreak(p));
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(10, item);
        }
        {
            ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§d§lFree For All:");
            List lore = new ArrayList();
            lore.add("§7§o(Coming Soon)");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(14, item);
        }
        {
            ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e§lUHC:");
            List lore = new ArrayList();
            lore.add("§6Kills: §a" + StatsAPI.getUHCKills(p));
            lore.add("§6Deaths: §a" + StatsAPI.getUHCDeaths(p));
            lore.add("§6Games Played: §a" + StatsAPI.getUHCGamesPlayed(p));
            lore.add("§6Heads Eaten: §a" + StatsAPI.getUHCHeadsEaten(p));
            lore.add("§6Wins: §a" + StatsAPI.getUHCWins(p));
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(12, item);
        }
        return inv;
    }
    @EventHandler
    public void onINteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        SwiftyPlayer user = new SwiftyPlayer(p);
        Action a = e.getAction();
        if (a==Action.RIGHT_CLICK_AIR || a==Action.RIGHT_CLICK_BLOCK){
            if (SwiftyTeams.inTeam("Spectator", user)){
                ItemStack hand = p.getItemInHand();
                if (hand.getType().equals(Material.ENCHANTED_BOOK)){
                    if (hand.getItemMeta().getDisplayName().equalsIgnoreCase("§6Alive Players §7§o(Right Click)")){
                        p.openInventory(getAlivePlayersInv());
                    }
                    if (hand.getItemMeta().getDisplayName().equalsIgnoreCase("§6Spectators §7§o(Right Click)")){
                        p.openInventory(getSpectatorsInv());
                    }
                }
                if (hand.getType().equals(Material.SKULL_ITEM)){
                    p.openInventory(getPlayerInfoInv(p));
                }
                if (hand.getType().equals(Material.WOOD_DOOR)){
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
            }
        }
    }
    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if (e.getWhoClicked() instanceof Player){
            Player p = (Player)e.getWhoClicked();
            Inventory inv = e.getInventory();
            if (inv.equals(getPlayerInfoInv(p))){
                e.setCancelled(true);
            }
            if (inv.equals(getAlivePlayersInv())){
                e.setCancelled(true);
            }
            if (inv.equals(getSpectatorsInv())){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        SwiftyPlayer user = new SwiftyPlayer(p);
        for (Player all : Bukkit.getOnlinePlayers()){
            SwiftyPlayer auser = new SwiftyPlayer(all);
            if (SwiftyTeams.inTeam("Spectator", auser)){
                user.hideUser(all);
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        for (Player all : Bukkit.getOnlinePlayers()){
            if (!(UHCMain.getState()==GameState.LOBBY)) {
                all.showPlayer(p);
            }
        }
    }
}
