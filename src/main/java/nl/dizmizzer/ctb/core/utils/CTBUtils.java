package nl.dizmizzer.ctb.core.utils;

import nl.dizmizzer.ctb.core.ButtonDeserializeException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class CTBUtils {

    public static String serializeLocation(Location location) {
        return location.getWorld().getName() + ";" +
                location.getBlockX() + ";" +
                location.getBlockY() + ";" +
                location.getBlockZ();
    }

    public static Location deserializeLocation(String string)  {
        String[] objects = string.split(";");
        if (objects.length != 4) try {
            throw new ButtonDeserializeException();
        } catch (ButtonDeserializeException e) {
            e.printStackTrace();
        }

        if (Bukkit.getWorld(objects[0]) == null) try {
            throw new ButtonDeserializeException();
        } catch (ButtonDeserializeException e) {
            e.printStackTrace();
        }
        World world = Bukkit.getWorld(objects[0]);
        int x = Integer.parseInt(objects[1]);
        int y = Integer.parseInt(objects[2]);
        int z = Integer.parseInt(objects[3]);

        Location location = new Location(world, x, y, z);

        if (!location.getBlock().getType().toString().contains("BUTTON")) try {
            throw new ButtonDeserializeException();
        } catch (ButtonDeserializeException e) {
            e.printStackTrace();
        }

        return location;
    }
}
