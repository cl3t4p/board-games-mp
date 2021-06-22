package  com.boardgame.mp.server.games;



import com.boardgame.mp.server.repository.GameRepo;
import com.boardgame.mp.server.components.data.Player;
import com.boardgame.mp.server.games.game.Game;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Games {
    private static final List<Games> games = new ArrayList<>();

    public static List<Games> getGames() {
        return games;
    }



    private final Class<? extends Game> gameclass;
    private final String name;

    private Games(Class<? extends Game> gameclass)
    {
        this.gameclass = gameclass;
        this.name = gameclass.getSimpleName();
    }

    public static void addGame(Class<? extends Game> gameclass){
        games.add(new Games(gameclass));
    }

    public static Games getGamesByID(Integer gameid){
        return games.get(gameid);
    }


    public Game createInstance(Player player1, Player player2, GameRepo gameRepo) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class[] object = {Player.class,Player.class,GameRepo.class};
        return gameclass.getDeclaredConstructor(object).newInstance(player1,player2,gameRepo);
    }


}
