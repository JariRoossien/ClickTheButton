package nl.dizmizzer.ctb.core;

import lombok.Getter;
import lombok.Setter;
import nl.dizmizzer.ctb.core.commands.CommandManager;
import nl.dizmizzer.ctb.core.entity.Game;
import nl.dizmizzer.ctb.core.entity.GameMap;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import nl.dizmizzer.ctb.core.listener.ButtonClickListener;
import nl.dizmizzer.ctb.core.listener.PlayerInteractListener;
import nl.dizmizzer.ctb.core.manager.ConfigManager;
import nl.dizmizzer.ctb.core.threads.AreaScanningThread;
import nl.dizmizzer.ctb.core.utils.CTBUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ClickTheButton extends JavaPlugin {

    @Getter
    private static ClickTheButton instance;
    @Getter
    private Map<UUID, GamePlayer> gamePlayer = new HashMap<>();
    @Getter @Setter
    private GameMap gameMap;
    @Getter
    private Game activeGame = new Game(UUID.randomUUID(), gameMap);

    public void onEnable() {
        instance = this;

        // Plugin startup logic
        ConfigManager.get().setup(this);
        initGameMap();

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new ButtonClickListener(), this);
        getCommand("ctb").setExecutor(new CommandManager());
    }

    private synchronized void initGameMap() {
        FileConfiguration config = ConfigManager.get().getConfig();
        gameMap = new GameMap(CTBUtils.deserializeLocation(config.getString("game.map.min")),
                CTBUtils.deserializeLocation(config.getString("game.map.max"))
                , CTBUtils.deserializeLocation(config.getString("game.map.spawnpoint")),
                config.getString("game.map.name"));
        gameMap.setLoaded(false);
        Bukkit.getScheduler().runTask(this, new AreaScanningThread(gameMap));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GamePlayer getGamePlayer(Player player) {
        return gamePlayer.get(player.getUniqueId());
    }
}
