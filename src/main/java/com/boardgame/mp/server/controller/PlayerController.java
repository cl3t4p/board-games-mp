package  com.boardgame.mp.server.controller;




import com.boardgame.mp.server.Repository.PlayerRepo;
import com.boardgame.mp.server.components.Exception.PlayerNotFoundByUUID;
import com.boardgame.mp.server.components.data.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



import java.util.UUID;

@RestController
@Transactional
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {



    private final PlayerRepo playerRepo;





   @DeleteMapping("/")
    public ResponseEntity<Object> delPlayer(@RequestParam String uuid) throws PlayerNotFoundByUUID {
        Player player =  playerRepo.getPlayerByPrivateuuid(UUID.fromString(uuid)).orElseThrow(PlayerNotFoundByUUID::new);
        playerRepo.delete(player);
        return ResponseEntity.ok().body(player);
    }

    @PostMapping("/")
    public Player newPlayer(@RequestParam String name) {
        Player player = new Player(name);
        playerRepo.save(player);
        return player;
    }

}