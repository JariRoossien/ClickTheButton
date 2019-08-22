package nl.dizmizzer.ctb.core.threads;

import nl.dizmizzer.ctb.core.ClickTheButton;
import nl.dizmizzer.ctb.core.utils.CTBUtils;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class AreaScanningThread implements Callable {
    @Override
    public Map<String, List<GamePlayer>> call() throws Exception {
        Location min = ClickTheButton.getGameMap().getMinimum_pos();
        Location max = ClickTheButton.getGameMap().getMaximum_pos();
        World world = min.getWorld();
        Map<String, List<GamePlayer>> returning_list = new HashMap<>();

        for (int x = min.getBlockX(); x < max.getBlockX(); x++) {
            for (int y = min.getBlockY(); y < max.getBlockY(); y++) {
                for (int z = min.getBlockZ(); z < max.getBlockZ(); z++) {
                    Location loc = new Location(world, x, y, z);
                    if (loc.getBlock() == null) continue;
                    if (!loc.getBlock().getType().toString().contains("BUTTON")) continue;
                    returning_list.put(CTBUtils.serializeLocation(loc), new ArrayList<>());
                }

            }

        }
        return returning_list;
    }
}
