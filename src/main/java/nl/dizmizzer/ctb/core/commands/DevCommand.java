package nl.dizmizzer.ctb.core.commands;

import nl.dizmizzer.ctb.core.ClickTheButton;
import nl.dizmizzer.ctb.core.entity.Game;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class DevCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String... commands) {
        if (!sender.isOp()) return;
        Logger l = Bukkit.getLogger();
        Game active = ClickTheButton.getInstance().getActiveGame();

        sender.sendMessage("-=[Game stats]=-");
        sender.sendMessage("GameID: " + active.getGameID());
        sender.sendMessage("Playersize: " + active.getPlayers().size());
        sender.sendMessage("Buttonsize: " + active.getMap().getButtons().size());
        sender.sendMessage("");

        if (sender instanceof Player) {
            Player player = (Player) sender;
            GamePlayer gplayer = ClickTheButton.getInstance().getGamePlayer(player);
            player.sendMessage("PlayerID: " + gplayer.getUuid());
            player.sendMessage("Clicked Buttons: " + gplayer.getClickedButtons().size());
            player.sendMessage("Score: " + gplayer.getScore());
        }
    }
}
