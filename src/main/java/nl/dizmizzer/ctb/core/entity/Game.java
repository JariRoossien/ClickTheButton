package nl.dizmizzer.ctb.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public class Game {

    @Getter @Setter
    private UUID gameID;
    @Getter @Setter
    private List<GamePlayer> players;

    public Game() {
    }

}
