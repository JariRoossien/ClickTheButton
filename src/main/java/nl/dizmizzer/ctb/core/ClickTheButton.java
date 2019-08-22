package nl.dizmizzer.ctb.core;

import lombok.Getter;
import lombok.Setter;
import nl.dizmizzer.ctb.core.listener.PlayerInteractListener;
import nl.dizmizzer.ctb.core.utils.CTBUtils;
import nl.dizmizzer.ctb.core.entity.GameMap;
import nl.dizmizzer.ctb.core.entity.GamePlayer;
import nl.dizmizzer.ctb.core.manager.ConfigManager;
import nl.dizmizzer.ctb.core.threads.AreaScanningThread;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@SuppressWarnings("unchecked")
public final class ClickTheButton extends JavaPlugin {

    @Getter @Setter
    private static GameMap gameMap;
    private Future<Map<String, List<GamePlayer>>> thread_response;
    private Callable<Map<String, List<GamePlayer>>> thread;
    private ExecutorService service = Executors.newFixedThreadPool(1);
    @Override

    public void onEnable() {
        // Plugin startup logic
        ConfigManager.get().setup(this);

        try {
            initGameMap();
        } catch (ButtonDeserializeException e) {
            getServer().getPluginManager().disablePlugin(this);
            e.printStackTrace();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }

    private synchronized void initGameMap() throws ButtonDeserializeException, ExecutionException, InterruptedException {
        FileConfiguration config = ConfigManager.get().getConfig();
        gameMap = new GameMap(CTBUtils.deserializeLocation(config.getString("game.map.min")),
                CTBUtils.deserializeLocation(config.getString("game.map.max"))
                , CTBUtils.deserializeLocation(config.getString("game.map.spawnpoint")),
                config.getString("game.map.name"));
        thread = new AreaScanningThread();
        thread_response = service.submit(thread);
        gameMap.setButtons(thread_response.get());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
