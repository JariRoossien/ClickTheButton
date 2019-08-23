package nl.dizmizzer.ctb.core;

import lombok.Getter;
import lombok.Setter;
import nl.dizmizzer.ctb.core.entity.Game;
import nl.dizmizzer.ctb.core.listener.PlayerInteractListener;
import nl.dizmizzer.ctb.core.utils.CTBUtils;
import nl.dizmizzer.ctb.core.entity.GameMap;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import nl.dizmizzer.ctb.core.manager.ConfigManager;
import nl.dizmizzer.ctb.core.threads.AreaScanningThread;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.*;

@SuppressWarnings("unchecked")
public final class ClickTheButton extends JavaPlugin {

    @Getter @Setter
    private static GameMap gameMap;
    private ExecutorService service = Executors.newFixedThreadPool(1);
    @Getter private Map<UUID, GamePlayer> gamePlayer = new HashMap<>();

    @Getter private Game activeGame = new Game();

    public void onEnable() {
        // Plugin startup logic
        ConfigManager.get().setup(this);

        try {
            initGameMap();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }

    private synchronized void initGameMap() throws ExecutionException, InterruptedException {
        FileConfiguration config = ConfigManager.get().getConfig();
        gameMap = new GameMap(CTBUtils.deserializeLocation(config.getString("game.map.min")),
                CTBUtils.deserializeLocation(config.getString("game.map.max"))
                , CTBUtils.deserializeLocation(config.getString("game.map.spawnpoint")),
                config.getString("game.map.name"));
        Callable<Map<String, List<GamePlayer>>> thread = new AreaScanningThread();
        Future<Map<String, List<GamePlayer>>> thread_response = service.submit(thread);
        gameMap.setButtons(thread_response.get());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
