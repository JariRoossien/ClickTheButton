package nl.dizmizzer.ctb.core.commands;

import org.bukkit.command.CommandSender;

public interface SubCommand {

    void execute(CommandSender sender, String... commands);
}
