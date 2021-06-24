package  com.boardgame.mp.server.controller;


import com.boardgame.mp.server.repository.GameRepo;
import com.boardgame.mp.server.repository.PlayerRepo;
import com.boardgame.mp.server.components.exception.NotFoundByUUID;
import com.boardgame.mp.server.components.exception.PlayerNotFoundByUUID;
import com.boardgame.mp.server.games.game.Game;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/moves")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class GameController {


    GameRepo gameRepo;
    PlayerRepo playerRepo;


    /**
     * Call the move method of the Game
     * @param puuid Priviate UUID of the player
     * @param gameuuid UUID of the game
     * @param move JSONObject of that will be persed inside a Game Class
     * @return Will return the response of the object
     */
    @PostMapping("/game")
    public ResponseEntity<Object> move(@RequestParam String puuid, @RequestParam UUID gameuuid, @RequestBody String move) throws Exception {
        Game game = gameRepo.GameByUUID(gameuuid)
                .orElseThrow(()-> new NotFoundByUUID("There is not game with that UUID"));

        return game.move(playerRepo.getPlayerByPrivateuuid(UUID.fromString(puuid))
                .orElseThrow(PlayerNotFoundByUUID::new),move);
    }

}
