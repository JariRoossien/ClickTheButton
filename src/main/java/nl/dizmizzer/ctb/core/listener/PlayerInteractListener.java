package nl.dizmizzer.ctb.core.listener;

import nl.dizmizzer.ctb.core.ClickTheButton;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import nl.dizmizzer.ctb.core.event.ButtonClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Logger;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        GamePlayer gamePlayer = ClickTheButton.getInstance().getGamePlayer(event.getPlayer());

        Logger l = Bukkit.getLogger();
        l.info((ClickTheButton.getInstance() == null) + "");
        l.info((gamePlayer == null) + "");
        l.info((gamePlayer.getGame() == null) + "");

        Block block = event.getClickedBlock();
        if (!block.getType().toString().contains("BUTTON")) return;
        l.info("Called event.");
        Bukkit.getServer().getPluginManager().callEvent(new ButtonClickEvent(gamePlayer, block.getLocation(), gamePlayer.getGame()));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        GamePlayer player = new GamePlayer(event.getPlayer());
        ClickTheButton.getInstance().getGamePlayer().put(
                event.getPlayer().getUniqueId(), player);
        player.setGame(ClickTheButton.getInstance().getActiveGame());

    }

}
