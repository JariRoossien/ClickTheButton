package nl.dizmizzer.ctb.core.entity;

import lombok.Getter;
import lombok.Setter;
import nl.dizmizzer.ctb.core.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    @Getter @Setter
    private UUID gameID;
    @Getter @Setter
    private List<GamePlayer> players = new ArrayList<>();
    @Getter
    @Setter
    private GameMap map;

    @Getter
    @Setter
    private GameState state;

    public Game(UUID id, GameMap gmap) {
        this.gameID = id;
        this.map = gmap;
        this.state = GameState.LOBBY;
    }

}
