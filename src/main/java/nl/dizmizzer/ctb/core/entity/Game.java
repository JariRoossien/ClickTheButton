package nl.dizmizzer.ctb.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    @Getter @Setter
    private UUID gameID;
    @Getter @Setter
    private List<GamePlayer> players = new ArrayList<>();

    public Game() {
    }

}
