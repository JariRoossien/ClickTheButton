package nl.dizmizzer.ctb.core.listener;

import nl.dizmizzer.ctb.core.GameState;
import nl.dizmizzer.ctb.core.entity.Game;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import nl.dizmizzer.ctb.core.event.ButtonClickEvent;
import org.bukkit.Bukkit;
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
        Bukkit.getLogger().info("Event handled.");
        if (game.getState() != GameState.INGAME) return;
        if (gplayer.getGame() != game) return;
        Bukkit.getLogger().info("Player Clicked!");
        gplayer.sendMessage("Test");
        List<GamePlayer> playerbutton = game.getMap().getButtons().get(button);
        if (playerbutton.contains(gplayer)) return;
        if (playerbutton.size() == 0) {
            gplayer.addScore(10);
            gplayer.sendMessage("&3First person to find the button! &b&l+10 points");
        } else {
            gplayer.addScore(5);
            gplayer.sendMessage("&Found a button! &b&l+5 points");
        }

        playerbutton.add(gplayer);
        gplayer.getClickedButtons().add(button);

    }
}
