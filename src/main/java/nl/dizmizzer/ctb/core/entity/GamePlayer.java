package nl.dizmizzer.ctb.core.entity;

        import lombok.Getter;
        import lombok.Setter;
        import org.bukkit.entity.Player;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.UUID;

public class GamePlayer {

    @Getter
    private UUID uuid;
    @Getter @Setter
    private Player player;

    @Getter @Setter
    private Game game;

    @Getter
    private List<String> clickedButtons = new ArrayList<>();

}
