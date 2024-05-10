package me.hi12167pies.kbffa.Utils;

import me.hi12167pies.kbffa.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomFile {
    protected File file;
    protected FileConfiguration config;

    public CustomFile(String name) {
        this.file = new File(Main.getPlugin(Main.class).getDataFolder(), name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
