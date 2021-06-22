package  com.boardgame.mp.server.controller;


import com.boardgame.mp.server.Repository.GameRepo;
import com.boardgame.mp.server.Repository.PlayerRepo;
import com.boardgame.mp.server.components.Exception.NotFoundByUUID;
import com.boardgame.mp.server.components.Exception.PlayerNotFoundByUUID;
import com.boardgame.mp.server.games.game.Game;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/moves")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class GameController {


    GameRepo gameRepo;
    PlayerRepo playerRepo;


    @PostMapping("/game")
    public ResponseEntity<Object> move(@RequestParam String puuid, @RequestParam UUID gameuuid, @RequestBody String move) throws Exception {
        Game game = gameRepo.GameByUUID(gameuuid)
                .orElseThrow(()-> new NotFoundByUUID("There is not game with that UUID"));

        return game.move(playerRepo.getPlayerByPrivateuuid(UUID.fromString(puuid))
                .orElseThrow(PlayerNotFoundByUUID::new),move);
    }

}
