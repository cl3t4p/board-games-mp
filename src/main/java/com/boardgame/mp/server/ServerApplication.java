package com.boardgame.mp.server;

import com.boardgame.mp.server.games.Games;
import com.boardgame.mp.server.games.game.Game;
import org.reflections.Reflections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ServerApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    loadGameClass();
    SpringApplication.run(ServerApplication.class, args);
  }

  /** Load every Class that extends Game into Games */
  private static void loadGameClass() {
    Reflections reflections = new Reflections("com.boardgame.mp.server");

    // Get Every Class with that is a SubClass of Game
    reflections
        .getSubTypesOf(Game.class)
        // Add Every class to the Games
        .forEach(Games::addGame);
  }
}
