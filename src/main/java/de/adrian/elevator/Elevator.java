package de.adrian.elevator;

import de.adrian.elevator.utils.MainFile;
import de.adrian.elevator.utils.MainFileDefaults;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class Elevator extends JavaPlugin {
    public static String PREFIX = "§8[§bElevator§8] §7";
    public static String NOPERMISSION = PREFIX + "§cDu hast dafür keine Rechte";
    public static Elevator INSTANCE;
    public Elevator() {
        INSTANCE = this;
    }

    private static Elevator plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.register();
        plugin = this;
        MainFile.setup();
        MainFileDefaults.addDefaults();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void register() {
        //ListenerRegister
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Listener(),this);

        //CommandRegister
        Bukkit.getPluginCommand("elevator").setExecutor(new Command());
    }
    public static void addPermission(Player player, String permission) {
        player.addAttachment(Elevator.INSTANCE).setPermission(permission, true);

        if (!MainFile.getCfg().getStringList(player.getUniqueId() + "").contains(permission)) {
            List<String> list = new ArrayList<>();
            if (MainFile.getCfg().get(player.getUniqueId() + "") != null) list = MainFile.getCfg().getStringList(player.getUniqueId() + "");
            list.add(permission);
            MainFile.getCfg().set(player.getUniqueId() + "", list);
            MainFile.save();
        }
    }
}
