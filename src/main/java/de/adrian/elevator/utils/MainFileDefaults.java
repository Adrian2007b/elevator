package de.adrian.elevator.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MainFileDefaults {
    public static void addDefaults() {
        if (MainFile.getCfg().get("Locations") == null) MainFile.getCfg().set("Locations", new ArrayList<Location>());
        if (MainFile.getCfg().get("NPCs") == null) MainFile.getCfg().set("NPCs", new ArrayList<Location>());
        if (MainFile.getCfg().get("Inventory.Name") == null) MainFile.getCfg().set("Inventory.Name", "§bAufzug");
        if (MainFile.getCfg().get("Inventory.Size") == null) MainFile.getCfg().set("Inventory.Size", 27);
            List<Location> list = (List<Location>) MainFile.getCfg().getList("Locations");


            for (Location location : list) {
                if (MainFile.getCfg().get(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Name") == null) MainFile.getCfg().set(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Name", "&c-");
                ItemStack Locked = new ItemBuilder(Material.BARRIER).setDisplayname(MainFile.getCfg().getString(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Name")).build();
                ItemStack Unlocked = new ItemBuilder(Material.LIME_DYE).setDisplayname(MainFile.getCfg().getString(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Name")).build();
                if (MainFile.getCfg().get(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Icon.Locked") == null) MainFile.getCfg().set(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Icon.Locked", Locked);
                if (MainFile.getCfg().get(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Icon.Unlocked") == null) MainFile.getCfg().set(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Icon.Unlocked", Unlocked);
                if  (MainFile.getCfg().get(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Icon.Slot") == null) MainFile.getCfg().set(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Icon.Slot", 0);
                if (MainFile.getCfg().get(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Permission") == null) MainFile.getCfg().set(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Permission", "elevatormain");
        }
        MainFile.save();
    }
}
