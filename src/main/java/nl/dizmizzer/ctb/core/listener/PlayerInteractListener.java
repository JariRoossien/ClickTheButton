package nl.dizmizzer.ctb.core.listener;

import nl.dizmizzer.ctb.core.event.ButtonClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = event.getClickedBlock();
        if (!block.getType().toString().contains("BUTTON")) return;

        //TODO Get Player, check if player is in game, check if button has already been clicked, manage click from BaseGame

        Bukkit.getServer().getPluginManager().callEvent(new ButtonClickEvent(null, "", null));
    }
}
