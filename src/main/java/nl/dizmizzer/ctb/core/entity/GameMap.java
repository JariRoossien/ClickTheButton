package nl.dizmizzer.ctb.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.List;
import java.util.Map;

public class GameMap {

    @Getter @Setter
    private Game game;

    @Getter @Setter
    private Location minimum_pos;

    @Getter @Setter
    private Location maximum_pos;

    @Getter @Setter
    private Location spawn_pos;

    @Getter @Setter
    private Map<Location, List<GamePlayer>> buttons;

    @Getter @Setter
    private String map_name;

    @Getter
    @Setter
    private boolean loaded;

    public GameMap(Location minPos, Location maxPos, Location spawn, String name) {
        this.minimum_pos = minPos;
        this.maximum_pos = maxPos;
        this.spawn_pos = spawn;
        this.map_name = name;

        if (minPos.getBlockX() > maxPos.getBlockX()) {
            minimum_pos.setX(maxPos.getBlockX());
            maximum_pos.setX(minPos.getBlockX());
        }
        if (minPos.getBlockY() > maxPos.getBlockY()) {
            minimum_pos.setY(maxPos.getBlockY());
            maximum_pos.setY(minPos.getBlockY());
        }
        if (minPos.getBlockZ() > maxPos.getBlockZ()) {
            minimum_pos.setZ(maxPos.getBlockZ());
            maximum_pos.setZ(minPos.getBlockZ());
        }
    }
}
