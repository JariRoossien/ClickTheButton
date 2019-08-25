package nl.dizmizzer.ctb.core.commands;

import nl.dizmizzer.ctb.core.utils.CTBUtils;
import org.bukkit.command.CommandSender;

public class HelpCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String... commands) {
        sender.sendMessage(CTBUtils.toColor("&6/ctb help: &fHelp Command."));
    }
}
