package de.adrian.elevator;

import de.adrian.elevator.utils.ItemBuilder;
import de.adrian.elevator.utils.MainFile;
import de.adrian.elevator.utils.MainFileDefaults;
import de.adrian.elevator.utils.Manage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Command implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Elevator.PREFIX + "§cUngültige Argumente (/elevator [npc/destination])");
            return true;
        }
        if (args.length < 2 && args[0].equalsIgnoreCase("NPC")) {
            sender.sendMessage(Elevator.PREFIX + "§cUngültige Argumente (/elevator npc [create/delete])");
            return true;
        }
        if (args.length <2 && args[0].equalsIgnoreCase("destination")) {
            sender.sendMessage(Elevator.PREFIX + "§cUngültige Argumente (/elevator destination [create/delete])");
            return true;
        }
        if (args.length <7 && args[0].equalsIgnoreCase("destination") && args[1].equalsIgnoreCase("create")) {
            sender.sendMessage(Elevator.PREFIX + "§cUngültige Argumente (/elevator destination [Name] [Permission] [Icon Locked] [Icon Unlocked] [Inventory Slot])");
            return true;
        }


        if (!(sender instanceof Player)) {
            sender.sendMessage(Elevator.PREFIX + "Nur Spieler dürfen diesen Befehl benutzen");
            return true;
        }
        if (!sender.hasPermission("elevatormain")) {
            sender.sendMessage(Elevator.NOPERMISSION);
            return true;
        }





        if (args.length >= 2 && args[0].equalsIgnoreCase("NPC") && args[1].equalsIgnoreCase("create")) {
            List<Location> list = (List<Location>) MainFile.getCfg().getList("NPCs");
            Location playerlocation = ((Player) sender).getLocation();
            playerlocation.set(playerlocation.getBlockX(), playerlocation.getBlockY(), playerlocation.getBlockZ());
            playerlocation.setPitch(0);
            playerlocation.setYaw(0);
            if (!list.contains(playerlocation)) {
                list.add(playerlocation);
                MainFile.save();
                sender.sendMessage(Elevator.PREFIX + "§aDu hast erfolgreich eine NPC Location erstellt");
            }else sender.sendMessage(Elevator.PREFIX + "§cAn diesem Ort befindet sich schon ein NPC");
        }

        if (args.length >= 2 && args[0].equalsIgnoreCase("NPC") && args[1].equalsIgnoreCase("delete")) {
            List<Location> list = (List<Location>) MainFile.getCfg().getList("NPCs");
            Location playerlocation = ((Player) sender).getLocation();
            playerlocation.set(playerlocation.getBlockX(), playerlocation.getBlockY(), playerlocation.getBlockZ());
            playerlocation.setPitch(0);
            playerlocation.setYaw(0);
            if (list.contains(playerlocation)) {
                list.remove(playerlocation);
                MainFile.save();
                sender.sendMessage(Elevator.PREFIX + "§aDu hast erfolgreich die NPC Location gelöscht");
            }else sender.sendMessage(Elevator.PREFIX + "§cAn diesem Ort befindet sich kein NPC");
        }

        if (args.length >= 7 && args[0].equalsIgnoreCase("destination") && args[1].equalsIgnoreCase("create")) {
            List<Location> list = (List<Location>) MainFile.getCfg().getList("Locations");
            Location l = ((Player) sender).getLocation();
            l.set(l.getBlockX(), l.getBlockY(),l.getBlockZ());
            l.setYaw(0);
            l.setPitch(0);
            if (!list.contains(l)) {
                list.add(l);
                MainFile.getCfg().set("Locations", list);
                MainFile.getCfg().set(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Teleport", ((Player) sender).getLocation());
                MainFile.getCfg().set(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Name", "§f" + args[2].replace("&","§").replace("-"," "));
                MainFile.getCfg().set(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Permission", args[3]);
                ItemStack itemStack = new ItemBuilder(Material.getMaterial(args[4].toUpperCase())).setDisplayname(MainFile.getCfg().getString(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Name")).build();
                ItemStack itemStack2 = new ItemBuilder(Material.getMaterial(args[5].toUpperCase())).setDisplayname(MainFile.getCfg().getString(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Name")).build();
                MainFile.getCfg().set(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Icon.Locked", itemStack);
                MainFile.getCfg().set(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Icon.Unlocked", itemStack2);
                MainFile.getCfg().set(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ".Icon.Slot", Integer.parseInt(args[6]));
                MainFileDefaults.addDefaults();
                MainFile.save();
                sender.sendMessage(Elevator.PREFIX + "§aDu hast erfolgreich ein Ziel erstellt");
            }else sender.sendMessage(Elevator.PREFIX + "§cAn diesem Ort wurde bereits ein Ziel erstellt");
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("destination") && args[1].equalsIgnoreCase("delete")) {
            List<Location> list = (List<Location>) MainFile.getCfg().getList("Locations");
            Location l = ((Player) sender).getLocation();
            l.set(l.getBlockX(), l.getBlockY(),l.getBlockZ());
            l.setYaw(0);
            l.setPitch(0);
            if (list.contains(l)) {
                list.remove(l);
                MainFile.getCfg().set("Locations", list);
                MainFile.getCfg().set(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ(), null);
                MainFile.save();
                sender.sendMessage(Elevator.PREFIX + "§aDu hast erfolgreich ein Ziel gelöscht");
            }else sender.sendMessage(Elevator.PREFIX + "§cAn diesem Ort befindet sich momentan kein Ziel");
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("npc");
            list.add("destination");
        }
        if (args.length == 2) {
            list.add("create");
            list.add("delete");
        }
            if (args.length == 3 && args[0].equalsIgnoreCase("destination") && args[1].equalsIgnoreCase("create")) list.add("(Name)");
            if (args.length == 4 && args[0].equalsIgnoreCase("destination") && args[1].equalsIgnoreCase("create")) list.add("(Permission)");
            if (args.length == 5 && args[0].equalsIgnoreCase("destination") && args[1].equalsIgnoreCase("create")) {
                list.add("(Icon [Locked])");
                for (Material name : Material.values()) list.add(name.name().toLowerCase());
            if (args.length == 6) {
                list.add("(Icon [Unlocked]");
                for (Material name : Material.values()) list.add(name.name().toLowerCase());
            }
            if (args.length == 7) list.add("position");
        }
        return list;
    }
}
