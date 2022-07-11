package de.adrian.elevator.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MainFile {
    private static File folder = new File("plugins/Elevator");
    private static File cfgFile = new File("plugins/Elevator/Main.yml");
    private static YamlConfiguration cfg;
    public static void setup() {
        if (!(folder.exists())) {
            folder.mkdir();
        }
        if (!(cfgFile.exists())) {
            try {

                cfgFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        cfg = YamlConfiguration.loadConfiguration(cfgFile);
    }
    public static YamlConfiguration getCfg() {
        return cfg;
    }

    public static void save() {
        try {
            cfg.save(cfgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
