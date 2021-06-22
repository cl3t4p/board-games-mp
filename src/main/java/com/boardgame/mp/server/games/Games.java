package  com.boardgame.mp.server.games;



import com.boardgame.mp.server.Repository.GameRepo;
import com.boardgame.mp.server.components.data.Player;
import com.boardgame.mp.server.games.game.Game;

import java.lang.reflect.InvocationTargetException;

public enum Games {

    TIC_TAC_TOE(TicTacToe.class);


    private final Class<? extends Game> gameclass;

    Games(Class<? extends Game> gameclass) {
        this.gameclass = gameclass;
    }

    public static Games getGamesByID(Integer gameid){
        return Games.class.getEnumConstants()[gameid];
    }


    public Game createInstance(Player player1, Player player2, GameRepo gameRepo) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class[] object = {Player.class,Player.class,GameRepo.class};
        return  gameclass.getDeclaredConstructor(object).newInstance(player1,player2,gameRepo);
    }
}
