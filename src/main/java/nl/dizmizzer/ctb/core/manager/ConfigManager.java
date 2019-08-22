package nl.dizmizzer.ctb.core.manager;

import nl.dizmizzer.ctb.core.ClickTheButton;
import nl.dizmizzer.ctb.core.utils.CTBUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    File file;
    FileConfiguration config;

    private ConfigManager() { }
    private static ConfigManager instance = new ConfigManager();

    public static ConfigManager get() {
        return instance;
    }

    public void setup(ClickTheButton ctb)  {
        if (!ctb.getDataFolder().exists()) ctb.getDataFolder().mkdir();
        file = new File(ctb.getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                config.set("game.map.min", CTBUtils.serializeLocation(new Location(Bukkit.getWorlds().get(0), 100, 20, 100)));
                config.set("game.map.max", CTBUtils.serializeLocation(new Location(Bukkit.getWorlds().get(0),200, 60, 200)));
                config.set("game.map.name", "TestMap");
                config.set("game.map.spawnpoint", CTBUtils.serializeLocation(new Location(Bukkit.getWorlds().get(0), 0, 50, 0)));
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
