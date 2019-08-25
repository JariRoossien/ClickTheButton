package nl.dizmizzer.ctb.core.threads;

import nl.dizmizzer.ctb.core.ClickTheButton;
import nl.dizmizzer.ctb.core.entity.GameMap;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaScanningThread implements Runnable {

    private GameMap gameMap;
    private Map<Location, List<GamePlayer>> buttonList;

    public AreaScanningThread(GameMap gameMap) {
        this.gameMap = gameMap;
        buttonList = new HashMap<>();
    }

    @Override
    public void run() {
        Bukkit.getLogger().info("Started scanning the game area!");
        for (int x = gameMap.getMinimum_pos().getBlockX(); x < gameMap.getMaximum_pos().getBlockX(); x++) {
            for (int y = gameMap.getMinimum_pos().getBlockY(); y < gameMap.getMaximum_pos().getBlockY(); y++) {
                for (int z = gameMap.getMinimum_pos().getBlockZ(); z < gameMap.getMaximum_pos().getBlockZ(); z++) {
                    Location loc = new Location(gameMap.getMinimum_pos().getWorld(), x, y, z);
                    if (loc.getBlock() == null) continue;
                    if (loc.getBlock().getType().toString().contains("BUTTON")) {
                        buttonList.put(loc, new ArrayList<>());
                    }
                }
            }
        }
        onFinish();
    }

    private void onFinish() {
        Bukkit.getLogger().info("Map has been loaded!");
        Bukkit.getLogger().info("{count} buttons have been added!".replace("{count}", "" + buttonList.size()));
        gameMap.setButtons(buttonList);
        gameMap.setLoaded(true);
        ClickTheButton.getInstance().getActiveGame().setMap(gameMap);
    }
}
