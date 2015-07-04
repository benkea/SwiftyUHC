package me.benkea.Util;

import me.benkea.Commands.FreezeTimer;
import me.benkea.UHCMain;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by 35047 on 8/04/15.
 */
public class TimeManager implements Runnable {

    public int LOBBY_SECONDS= 120;
    public int MIN_PLAYERS = 12  ;
    public int WARMUP_SECONDS = 16;
    public int NOPVP_SECONDS = 600;
    public int INGAME_SECONDS = 7200;
    public static int DMATCH_WARMUP_SECONS = 11;
    public int DEATHMATCH_SECONDS = 1200;
    public int ENDED_SECONDS = 11;

    @Override
    public void run() {

        if (!(UHCMain.getState() == GameState.INGAME)) {
            if (!FreezeTimer.isFreezetimer()) {
                if (UHCMain.getTicks() > 0) {
                    UHCMain.setTicks(UHCMain.getTicks() - 1);
                }
            }
        }
        if (UHCMain.getMode() == Modes.FFA) {
            if (UHCMain.getState() == GameState.INGAME) {
                if (!FreezeTimer.isFreezetimer()) {
                    if (UHCMain.getTicks() >= 0) {
                        UHCMain.setTicks(UHCMain.getTicks() + 1);
                    }
                }
            }
            if (UHCMain.getState() == GameState.STARTING) {
                UHCMain.setTicks(LOBBY_SECONDS);
                UHCMain.setState(GameState.LOBBY);
            }

            if (UHCMain.getState() == GameState.LOBBY) {

                int online = Bukkit.getServer().getOnlinePlayers().size();

                for (Player pl : Bukkit.getOnlinePlayers()) {
                    if (UHCMain.getTicks() == 20 || UHCMain.getTicks() == 40 || UHCMain.getTicks() == 60 || UHCMain.getTicks() == 80 || UHCMain.getTicks() == 100 || UHCMain.getTicks() == 120) {
                        pl.sendMessage(UHCMain.UHCTag + "Lobby ends in §a" + UHCMain.formatTime(UHCMain.getTicks()) + "...");
                        pl.sendMessage(UHCMain.UHCTag + "There are §a" + Bukkit.getOnlinePlayers().size() + " §bplayers waiting to play UHC!  ");
                    }
                }
                if (UHCMain.getTicks() == 0) {
                    if (online >= MIN_PLAYERS) {
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            Bukkit.getScheduler().scheduleSyncDelayedTask(UHCMain.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    UHCMain.setState(GameState.LOADING);
                                }
                            }, 100);
                        }
                    } else {
                        UHCMain.setTicks(LOBBY_SECONDS);
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            pl.sendMessage(UHCMain.UHCTag + "UHC requires §a" + MIN_PLAYERS + "§b players to begin!");
                            pl.playSound(pl.getLocation(), Sound.CLICK, 1, 1);
                        }
                    }
                }
            } else if (UHCMain.getState() == GameState.LOADING) {
                for (Player pl : Bukkit.getOnlinePlayers()) {

                    if (Bukkit.getWorld("world") == null) {
                        UHCMain.setTicks(LOBBY_SECONDS);
                        UHCMain.setState(GameState.LOBBY);
                        pl.sendMessage(UHCMain.Error + "Something went wrong with the worlds! Plz contact a Staff Member!");
                    } else if (Bukkit.getWorld("world") != null) {
                        pl.teleport(Bukkit.getWorld("world").getSpawnLocation());
                        Bukkit.getServer().getWorld("world").getWorldBorder().setCenter(0, 0); //SET THE CENTER OF WORLDBORDER
                        Bukkit.getServer().getWorld("world").getWorldBorder().setDamageBuffer(5);
                        Bukkit.getServer().getWorld("world").getWorldBorder().setDamageAmount(0.2);
                        Bukkit.getServer().getWorld("world").getWorldBorder().setSize(4000); //SET THE ORIGINAL SIZE OF WORLDBORDER
                        Bukkit.getServer().getWorld("world").getWorldBorder().setSize(400, 7800); //SET THE SHRINKING BORDER!
                        Bukkit.getServer().getWorld("world").setTime(0);
                    }
                    Random r = new Random();
                    double x = r.nextInt(1750) + 1;
                    double y = 200;
                    double z = r.nextInt(1750) + 1;
                    pl.teleport(new Location(pl.getWorld(), x, y, z));
                    pl.sendMessage(UHCMain.UHCTag + "Teleporting Players...");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        PlayerInventory pi = all.getInventory();
                        pi.clear();
                        pi.setArmorContents(null);
                        all.setHealth(all.getMaxHealth());
                        all.setFireTicks(0);
                        all.setFoodLevel(20);
                        all.setSaturation(8F);
                        UHCMain.setTicks(WARMUP_SECONDS);
                        UHCMain.setState(GameState.WARMUP);
                    }
                }
            } else if (UHCMain.getState() == GameState.WARMUP) {

                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 1));
                    all.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 1));
                    all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 3));
                    all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 300, 3));
                    all.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 600, 3));
                    if (UHCMain.getTicks() == 15) {
                        all.sendMessage(UHCMain.UHCTag + "Starting in §a" + UHCMain.formatTime(UHCMain.getTicks()) + "§b...");
                    }
                    if (UHCMain.getTicks() >= 1 && UHCMain.getTicks() <= 10) {
                        all.sendMessage(UHCMain.UHCTag + "Starting in §a" + UHCMain.formatTime(UHCMain.getTicks()) + "§b...");
                    }
                    if (UHCMain.getTicks() == 0) {
                        all.sendMessage(UHCMain.UHCTag + "UHC has Started! Good Luck!");
                        all.sendMessage(UHCMain.UHCTag + "There is a §a10 minute §bNoPvP Period starting §l§nNOW!");
                        all.sendMessage(UHCMain.UHCTag + "Do /uhcinfo to see information about this match!");
                        UHCMain.setTicks(NOPVP_SECONDS);
                        UHCMain.setState(GameState.NOPVP);
                        all.removePotionEffect(PotionEffectType.BLINDNESS);
                        all.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        all.removePotionEffect(PotionEffectType.SLOW);
                        all.removePotionEffect(PotionEffectType.SLOW_DIGGING);
                    }
                }
            } else if (UHCMain.getState() == GameState.NOPVP) {

                for (Player all : Bukkit.getOnlinePlayers()) {

                    if (UHCMain.getTicks() == 450 || UHCMain.getTicks() == 150) {
                        all.sendMessage(UHCMain.UHCTag + "There are §a" + UHCMain.formatTime(UHCMain.getTicks()) + "§b until NoPvP ends!");
                    }
                    if (UHCMain.getTicks() == 300) {
                        all.sendMessage(UHCMain.UHCTag + "§a5 mins §bhave passed! There are §a" + UHCMain.formatTime(UHCMain.getTicks()) + "§b left until NoPvP finishes!");
                    }
                    if (UHCMain.getTicks() == 0) {
                        all.sendMessage(UHCMain.UHCTag + "NoPvP has officially ended! Good luck all! In §atwo hours §bthe DeathMatch will start");
                        UHCMain.setTicks(INGAME_SECONDS);
                        UHCMain.setState(GameState.INGAME);
                    }
                }
            } else if (UHCMain.getState() == GameState.INGAME) {

                for (Player all : Bukkit.getOnlinePlayers()) {

                    int ticks = UHCMain.getTicks();

                    if (ticks > 0 && (ticks % 5 == 0 || (ticks <= 10 && ticks > 0))) {
                        all.sendMessage(UHCMain.UHCTag + "You have been in UHC for§a" + UHCMain.formatTime(UHCMain.getTicks()));
                    }
                }
            } else if (UHCMain.getState() == GameState.DMATCHWARMUP) {

                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 220, 1));
                    all.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 220, 1));
                    all.teleport(new Location(Bukkit.getWorld("world"), all.getLocation().getX(), 200, all.getLocation().getZ()));
                    if (UHCMain.getTicks() >= 1 && UHCMain.getTicks() <= 10) {
                        all.sendMessage(UHCMain.UHCTag + "The DeathMatch will start in §a" + UHCMain.formatTime(UHCMain.getTicks()) + "§b...");
                    }
                    if (UHCMain.getTicks() == 0) {
                        all.removePotionEffect(PotionEffectType.BLINDNESS);
                        all.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        all.sendMessage(UHCMain.UHCTag + "The DeathMatch has Started! May the best player win!");
                        all.sendMessage(UHCMain.UHCTag + "You are now in DeathMatch for §a20 minutes§b!");
                        all.sendMessage(UHCMain.UHCTag + "The border is now §a200 x 200§b!");
                        UHCMain.setTicks(DEATHMATCH_SECONDS);
                        UHCMain.setState(GameState.DEATHMATCH);
                    }
                }
            } else if (UHCMain.getState() == GameState.DEATHMATCH) {

                for (Player all : Bukkit.getOnlinePlayers()) {

                    int ticks = UHCMain.getTicks();

                    if (ticks == 900 || ticks == 600 || ticks == 300 || ticks == 150 || ticks == 60 || ticks == 45 || ticks == 30 || ticks == 15) {
                        all.sendMessage(UHCMain.UHCTag + "There is §a" + UHCMain.formatTime(UHCMain.getTicks()) + "§b until the deathMatch ends!");
                    }
                    if (UHCMain.getTicks() >= 1 && UHCMain.getTicks() <= 10) {
                        all.sendMessage(UHCMain.UHCTag + "There is §a" + UHCMain.formatTime(UHCMain.getTicks()) + "§b until the DeathMatch ends!");
                    }
                    if (UHCMain.getTicks() == 0) {
                        Bukkit.getServer().getWorld("world").getWorldBorder().setDamageBuffer(0);
                        Bukkit.getServer().getWorld("world").getWorldBorder().setDamageAmount(3);
                        Bukkit.getServer().getWorld("world").getWorldBorder().setSize(1, 1); //SET THE SHRINKING BORDER!
                        all.sendMessage(UHCMain.UHCTag + "We have no winner :'( Better luck next time!");
                        UHCMain.setTicks(ENDED_SECONDS);
                        UHCMain.setState(GameState.ENDED);
                    }
                }
            } else if (UHCMain.getState() == GameState.ENDED) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (UHCMain.getTicks() >= 2 && UHCMain.getTicks() <= 10) {
                        all.sendMessage(UHCMain.UHCTag + "You will be teleported to hub in §a" + UHCMain.formatTime(UHCMain.getTicks()) + "§b...");
                    }
                    if (UHCMain.getTicks() == 2) {
                        Bukkit.getMessenger().registerOutgoingPluginChannel(UHCMain.getInstance(), "BungeeCord");
                        ByteArrayOutputStream b = new ByteArrayOutputStream();
                        DataOutputStream out = new DataOutputStream(b);
                        try {
                            out.writeUTF("Connect");
                            out.writeUTF("HB1");
                        } catch (IOException ex) {
                        }
                        all.sendPluginMessage(UHCMain.getInstance(), "BungeeCord", b.toByteArray());
                    }
                    if (UHCMain.getTicks() == 0) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
                    }
                }
            }
        }
    }
}