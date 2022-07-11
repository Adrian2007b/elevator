package de.adrian.elevator.utils;

import de.adrian.elevator.Elevator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manage {
    private static Map<String, Boolean> map = new HashMap<>();

    public static void openElevator(Player player) {
        Inventory inventory = Bukkit.createInventory(null, MainFile.getCfg().getInt("Inventory.Size"), MainFile.getCfg().getString("Inventory.Name"));
        List<Location> locationList = (List<Location>) MainFile.getCfg().getList("Locations");
        if (locationList.size()>0) for (Location location : locationList) {
            ItemStack iconUnlock = MainFile.getCfg().getItemStack(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Icon.Unlocked");
            ItemStack iconLock = MainFile.getCfg().getItemStack(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Icon.Locked");
            if (player.hasPermission(MainFile.getCfg().getString(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Permission"))) inventory.setItem(MainFile.getCfg().getInt(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Icon.Slot"), iconUnlock);
            else inventory.setItem(MainFile.getCfg().getInt(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Icon.Slot"), iconLock);
        }
        player.openInventory(inventory);
    }
}
