package com.boardgame.mp.server.games;

import com.boardgame.mp.server.components.data.Player;
import com.boardgame.mp.server.components.exception.ClassNoArgsConstructor;
import com.boardgame.mp.server.games.game.Game;
import com.boardgame.mp.server.repository.GameRepo;
import lombok.Getter;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;


@Getter
public class Games {
  private static final List<Games> gamesList = new ArrayList<>();


  private final Class<? extends Game> gameclass;

  private final String name;

  private Games(Class<? extends Game> gameclass) {
    this.gameclass = gameclass;
    this.name = gameclass.getSimpleName();
  }

  public static List<Games> getGamesList() {
    return gamesList;
  }

  /** Add to the list a Class that extends Game */
  public static void addGame(Class<? extends Game> gameclass) throws ClassNoArgsConstructor {
    //Check if the Class has an empty Constructor
    if(Stream.of(gameclass.getConstructors())
            .anyMatch((c) -> c.getParameterCount() == 0))
    throw new ClassNoArgsConstructor("The Class "+ gameclass.getName() +" has no a No Args Constructor");


    gamesList.add(new Games(gameclass));
  }

  public static Games getGamesByID(Integer gameid) throws Exception {
    Games game = gamesList.get(gameid);
    if(game == null) throw new Exception("There is no Game with this gameid");
    return gamesList.get(gameid);
  }

  public Game createEmptyInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    return gameclass.getDeclaredConstructor().newInstance();
  }

  /**
   * Givven the param for create a Game class it can create a new SubClass of Game
   *
   * @return The new SubClass
   */
  public Game createInstance(Player player1, Player player2, GameRepo gameRepo)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException,
          IllegalAccessException {
    Class<?>[] object = {Player.class, Player.class, GameRepo.class};
    return gameclass.getDeclaredConstructor(object).newInstance(player1, player2, gameRepo);
  }
}
