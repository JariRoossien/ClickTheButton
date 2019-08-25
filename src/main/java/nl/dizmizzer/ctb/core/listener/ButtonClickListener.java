package nl.dizmizzer.ctb.core.listener;

import nl.dizmizzer.ctb.core.GameState;
import nl.dizmizzer.ctb.core.entity.Game;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import nl.dizmizzer.ctb.core.event.ButtonClickEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class ButtonClickListener implements Listener {

    @EventHandler
    public void onClick(ButtonClickEvent event) {
        GamePlayer gplayer = event.getPlayer();
        Location button = event.getButton();
        Game game = event.getGame();

        if (game.getState() != GameState.INGAME) return;
        if (gplayer.getGame() != game) return;

        List<GamePlayer> playerbutton = game.getMap().getButtons().get(button);
        if (playerbutton.contains(gplayer)) return;

        playerbutton.add(gplayer);
        gplayer.getClickedButtons().add(button);

    }
}
