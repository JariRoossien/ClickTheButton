package nl.dizmizzer.ctb.core.entity;

import lombok.Getter;
import lombok.Setter;
import nl.dizmizzer.ctb.core.utils.CTBUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GamePlayer {

    @Getter
    private UUID uuid;
    @Setter
    private Player player;

    @Getter @Setter
    private Game game;

    @Getter
    @Setter
    private int Score;

    @Getter
    private List<Location> clickedButtons = new ArrayList<>();

    public GamePlayer(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
    }

    public Player getPlayer() {
        if (player == null) {
            return null;
        }
        return player;
    }

    public void addScore(int i) {
        setScore(getScore() + i);
    }

    public void sendMessage(String msg) {
        if (getPlayer() != null) {
            getPlayer().sendMessage(CTBUtils.toColor(msg));
        }
    }
}
