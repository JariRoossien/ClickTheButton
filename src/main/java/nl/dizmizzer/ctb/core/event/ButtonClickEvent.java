package nl.dizmizzer.ctb.core.event;

import nl.dizmizzer.ctb.core.entity.Game;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import nl.dizmizzer.ctb.core.utils.CTBUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ButtonClickEvent extends Event {

    private GamePlayer player;
    private Location button;
    private Game game;

    private static HandlerList handler = new HandlerList();
    public ButtonClickEvent(GamePlayer player, String ser_button, Game game) {
        this.player = player;
        this.button = CTBUtils.deserializeLocation(ser_button);
    }

    @Override
    public HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }


}
