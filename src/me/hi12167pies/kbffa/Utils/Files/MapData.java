package me.hi12167pies.kbffa.Utils.Files;

import me.hi12167pies.kbffa.Utils.CustomFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class MapData extends CustomFile {
    public MapData() {
        super("arenas.yml");
    }
    public void setSpawn(String map, Location location) {
        config.set("map."+map+".spawn.x", location.getX());
        config.set("map."+map+".spawn.y", location.getY());
        config.set("map."+map+".spawn.z", location.getZ());
        config.set("map."+map+".spawn.pitch", (double) location.getPitch());
        config.set("map."+map+".spawn.yaw", (double) location.getYaw());
        config.set("map."+map+".spawn.world", location.getWorld().getName());
        save();
    }

    public Location getSpawn(String map) {
        double x = config.getDouble("map."+map+".spawn.x");
        double y = config.getDouble("map."+map+".spawn.y");
        double z = config.getDouble("map."+map+".spawn.z");
        float pitch = (float) config.getDouble("map."+map+".spawn.pitch");
        float yaw = (float) config.getDouble("map."+map+".spawn.yaw");
        World world = Bukkit.getWorld(config.getString("map."+map+".spawn.world"));

        return new Location(world, x, y, z, yaw, pitch);
    }

    public void setVoid(String map, double a) {
        config.set("map."+map+".void", a);
        save();
    }

    public double getVoid(String map) {
        return config.getDouble("map."+map+".void");
    }

    public void setTop(String map, double a) {
        config.set("map."+map+".top", a);
        save();
    }

    public double getTop(String map) {
        return config.getDouble("map."+map+".top");
    }



    public void deleteMap(String map) {
        config.set("map."+map, null);
        save();
    }

    public boolean mapExist(String map) {
        return config.getConfigurationSection("map").getKeys(false).contains(map);
    }
}
