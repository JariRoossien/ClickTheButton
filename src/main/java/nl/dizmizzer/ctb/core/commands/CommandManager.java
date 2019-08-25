package nl.dizmizzer.ctb.core.commands;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {

    @Getter
    private Map<String, SubCommand> subcommands = new HashMap<>();

    public CommandManager() {
        subcommands.put("help", new HelpCommand());
        subcommands.put("dev", new DevCommand());
        subcommands.put("gamestate", new GameStateCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (args.length == 0) {
            subcommands.get("help").execute(commandSender, args);
            return true;
        }
        if (!subcommands.containsKey(args[0])) {
            subcommands.get("help").execute(commandSender, args);
            return true;
        }

        subcommands.get(args[0]).execute(commandSender, args);
        return true;
    }
}
