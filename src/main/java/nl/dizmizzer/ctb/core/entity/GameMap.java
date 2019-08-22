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
    private Map<String,List<GamePlayer>> buttons;

    @Getter @Setter
    private String map_name;

    public GameMap(Location minPos, Location maxPos, Location spawn, String name) {
        this.minimum_pos = minPos;
        this.maximum_pos = maxPos;
        this.spawn_pos = spawn;
        this.map_name = name;
    }


}
