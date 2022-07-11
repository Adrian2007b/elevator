package de.adrian.elevator;

import de.adrian.elevator.utils.MainFile;
import de.adrian.elevator.utils.Manage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.security.Permission;
import java.util.List;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    private void onInteract(PlayerInteractEntityEvent e) {
        Location location = e.getRightClicked().getLocation();
        location.set(location.getBlockX(),location.getBlockY(),location.getBlockZ());
        location.setYaw(0);
        location.setPitch(0);
        List<Location> list = (List<Location>) MainFile.getCfg().getList("NPCs");
        if (list.contains(location)) {
            if (MainFile.getCfg().get(e.getPlayer().getUniqueId() + "") != null) for (String s : MainFile.getCfg().getStringList(e.getPlayer().getUniqueId() + "")) if (!e.getPlayer().hasPermission(s)) {
                e.getPlayer().addAttachment(Elevator.INSTANCE).setPermission(s, true);
            }

            Manage.openElevator(e.getPlayer());
        }
    }
    @EventHandler
    private void onInventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked().getOpenInventory().getTitle().equals(MainFile.getCfg().getString("Inventory.Name"))) {
            e.setCancelled(true);
            if(!e.getClickedInventory().getType().equals(InventoryType.PLAYER)) if (e.getWhoClicked().getOpenInventory().getItem(e.getSlot()) != null) {
                List<Location> locationList = (List<Location>) MainFile.getCfg().getList("Locations");
                for (Location l : locationList) {
                    if (MainFile.getCfg().getString(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Name").equals(e.getCurrentItem().getItemMeta().getDisplayName())) {
                        if (MainFile.getCfg().getInt(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Icon.Slot") == e.getSlot()) {
                            if (e.getWhoClicked().hasPermission(MainFile.getCfg().getString(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Permission"))) {
                                e.getWhoClicked().teleport(MainFile.getCfg().getLocation(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Teleport"));
                            } else e.getWhoClicked().sendMessage(Elevator.NOPERMISSION);
                            return;
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    private void onMove(PlayerMoveEvent e) {
        for (Location location : (List<Location>) MainFile.getCfg().getList("Locations")) if (e.getTo().distance(location)<=5) {
           String permission = MainFile.getCfg().getString(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Permission");
           if (e.getPlayer().hasPermission(permission)) return;
           Elevator.addPermission(e.getPlayer(), permission);
           e.getPlayer().sendMessage(Elevator.PREFIX + "Du hast die Region " + MainFile.getCfg().getString(location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ".Name") + "§7 freigeschaltet");
        }
    }
}
