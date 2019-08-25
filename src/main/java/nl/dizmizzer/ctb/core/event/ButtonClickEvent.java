package nl.dizmizzer.ctb.core.event;

import lombok.Getter;
import lombok.Setter;
import nl.dizmizzer.ctb.core.entity.Game;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ButtonClickEvent extends Event {

    @Getter
    @Setter
    private GamePlayer player;
    @Getter
    @Setter
    private Location button;

    @Getter
    @Setter
    private Game game;

    private static HandlerList handler = new HandlerList();

    public ButtonClickEvent(GamePlayer player, Location button, Game game) {
        this.player = player;
        this.button = button;
        this.game = game;
    }

    @Override
    public HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }

}
