package me.benkea;

import me.benkea.Commands.*;
import me.benkea.Listeners.*;
import me.benkea.Util.GameState;
import me.benkea.Util.Modes;
import me.benkea.Util.TimeManager;
import me.benkea.Util.Spec;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.SwiftyTeams;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Random;

/**
 * Created by 35047 on 8/04/15.
 */
public class UHCMain extends JavaPlugin {

    public static String UHCTag = "§6§lSwi§e§lfty §6§lU§e§lH§6§lC §e§l>§6§l> §b";
    public static String Error = "§c§l/§4§l/ §6";
    public static String CantFindPlayer = Error+"Sorry, but we could not find the player ";
    public static String NoCONSOLE = Error+"Sorry, But the Console can't run this command!";
    public static String TAG = "§6§lSwi§e§lfty§6§l>§e§l>§r ";
    public static String TAG2 = "§6§l/§e§l/ ";
    public static String Arrows = "§e§l>§6§l> ";
    public static String SwiftyAnnounment = "§6§lSwi§e§lfty§6§l>§e§l>§r ";
    public static String PermCONSOLE = "§c§l/§4§l/ §cYou are not as cool as a Console, so stop trying to use this command!";
    public static String PermOWNER = "§c§l/§4§l/ §cYou are not as cool as a Owner, so stop trying to use this command!";
    public static String PermHEADADMIN = "§c§l/§4§l/ §cYou are not as cool as a HeadAdmin, so stop trying to use this command!";
    public static String PermAdmin = "§c§l/§4§l/ §cYou are not as cool as a Admin, so stop trying to use this command!";
    public static String PermMOD = "§c§l/§4§l/ §cYou are not as cool as a Mod, so stop trying to use this command!";
    public static String PermVIP = "§c§l/§4§l/ §cYou are not as cool as a VIP, so stop trying to use this command!";
    public static String PermHELPER = "§c§l/§4§l/ §cYou are not as cool as a Helper, so stop trying to use this command!";
    public static String PermIMMORTAL = "§c§l/§4§l/ §cYou are not as cool as a Immortal, so stop trying to use this command!";
    public static String PermLEGEND = "§c§l/§4§l/ §cYou are not as cool as a Legend, so stop trying to use this command!";
    public static String PermELITE = "§c§l/§4§l/ §cYou are not as cool as a Elite, so stop trying to use this command!";
    public static String PermHERO = "§c§l/§4§l/ §cYou are not as cool as a Hero, so stop trying to use this command!";
    public static String PermSergeant = "§c§l/§4§l/ §cYou are not as cool as a Sergeant, so stop trying to use this command!";

    private static int ticks = 0;
    public static int MIN_PLAYERS = 12;
    private static GameState state;
    private static Modes mode;

    public static Modes getMode() {
        return mode;
    }

    public static void setMode(Modes mode) {
        UHCMain.mode = mode;
    }

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState state) {
        UHCMain.state = state;
    }

    public static int getTicks() {
        return ticks;
    }

    public static void setTicks(int ticks) {
        UHCMain.ticks = ticks;
    }

    public static UHCMain instance;

    public static UHCMain getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        delDir(new File("world"));
    }

    public void regListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new joinListener(), this);
        pm.registerEvents(new UtilListeners(), this);
        pm.registerEvents(new RegenListener(), this);
        pm.registerEvents(new ChatEvent(), this);
        pm.registerEvents(new CommandHandle(), this);
        pm.registerEvents(new TogglesEvents(), this);
        pm.registerEvents(new Spec(), this);
    }

    public void regCmd(){
        this.getCommand("ForceStart").setExecutor(new ForceStart());
        this.getCommand("UHCTest").setExecutor(new information());
        this.getCommand("revive").setExecutor(new Revive());
        this.getCommand("UHCInfo").setExecutor(new UHCInfo());
        this.getCommand("freezetimer").setExecutor(new FreezeTimer());
        this.getCommand("spectest").setExecutor(new SpecTest());
        this.getCommand("setmode").setExecutor(new Mode());
        this.getCommand("dmatch").setExecutor(new dmatch());
    }

    @Override
    public void onEnable() {

        SwiftyTeams.addTeam("Alive");
        SwiftyTeams.addTeam("Spectator");

        instance = this;

        regListeners();

        regCmd();

        state = GameState.STARTING;
        mode = Modes.FFA;

        Bukkit.getServer().getScheduler().runTaskTimer(this, new TimeManager(), 20L, 20L);

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        getConfig().options().copyDefaults(true);
        getConfig().set("UHCInfo.Strength1", true);
        getConfig().set("UHCInfo.Strength2", true);
        getConfig().set("UHCInfo.RegenPot", true);
        getConfig().set("UHCInfo.GodApple", true);
        getConfig().set("UHCInfo.End", false);
        getConfig().set("UHCInfo.Nether", true);
        getConfig().set("UHCInfo.Heads", true);
        saveConfig();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (getState()==GameState.INGAME && getTicks() >=1200){
                    if (SwiftyTeams.getTeamSize("Alive")<=10){
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            SwiftyPlayer user = new SwiftyPlayer(pl);
                            if (SwiftyTeams.inTeam("Alive", user)) {
                                Random r = new Random();
                                double x = r.nextInt(175) + 1;
                                double y = 200;
                                double z = r.nextInt(175) + 1;
                                user.teleport(new Location(user.getWorld(), x, y, z));
                                user.sendMessage(UHCTag + "The DeathMatch is starting!");
                                Bukkit.getWorld("world").getWorldBorder().setSize(400);
                                setTicks(TimeManager.DMATCH_WARMUP_SECONS);
                                setState(GameState.DMATCHWARMUP);
                            }
                        }
                    }
                }
            }
        }, 0, 20);
    }

    @Override
    public void onDisable() {

        SwiftyTeams.removeTeam("Alive");
        SwiftyTeams.removeTeam("Spectator");

        instance = null;

    }

    public static String formatTime(int seconds){

        int hours = seconds/ 3600;
        int remainder = seconds % 3600;
        int mins = remainder / 60;
        int secs = remainder % 60;

        if (hours > 0){
            return hours+" hrs and "+mins+" mins and "+secs+" secs";
        }
        else  if (mins > 0){
            return mins+" mins and "+secs+" secs";
        }
        else{
            return String.valueOf(secs+" secs");
        }
    }

    public static void delDir(File dir) {

        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                delDir(new File(dir, children[i]));
            }
        }
        dir.delete();
    }
}
