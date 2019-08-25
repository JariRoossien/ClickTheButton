package nl.dizmizzer.ctb.core.commands;

import nl.dizmizzer.ctb.core.ClickTheButton;
import nl.dizmizzer.ctb.core.GameState;
import org.bukkit.command.CommandSender;

public class GameStateCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String... commands) {
        if (!sender.isOp()) return;
        if (commands.length == 1) return;
        GameState change = GameState.valueOf(commands[1].toUpperCase());
        if (change == null) return;
        ClickTheButton.getInstance().getActiveGame().setState(change);
    }
}
