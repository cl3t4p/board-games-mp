package server.Controller;

import org.springframework.web.bind.annotation.*;
import server.Components.Exception.WrongDataException;
import server.Components.Exception.WrongTurnException;
import server.Games.Game.Game;
import server.Games.TicTacToe;
import server.Repository.PlayerRepo;

import java.util.UUID;


@RestController
@RequestMapping("/moves")
public class GameController {


    private final PlayerRepo playerRepo;

    public GameController(PlayerRepo repo){
        this.playerRepo = repo;
    }

    @PostMapping("/game")
    public String move(@RequestParam String puuid,@RequestParam String gameuuid,@RequestBody TicTacToe.Move move) throws WrongTurnException, WrongDataException {
        Game game = Game.getGames().stream().filter(game1 -> game1.getUUID().equals(UUID.fromString(gameuuid))).findFirst().orElse(null);
        if(game == null)
            return "No game found";
        game.move(playerRepo.getPlayerByPrivateuuid(UUID.fromString(puuid)),move);
        return "Ok";
    }

}
