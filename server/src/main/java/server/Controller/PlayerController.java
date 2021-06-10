package server.Controller;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import server.Components.Player;
import server.Repository.PlayerRepo;


import java.util.UUID;

@RestController
@Transactional
@RequestMapping("/players")
public class PlayerController {



    private final PlayerRepo playerRepo;

    public PlayerController(PlayerRepo repo){
        this.playerRepo = repo;
        playerRepo.save(new Player("Player1",
                UUID.fromString("f5697643-b874-4aff-baf6-bd493a6d0ca3"),
                UUID.fromString("d7ff4d67-4039-4650-8083-66a435054748")));
        playerRepo.save(new Player("Player2",
                UUID.fromString("fc965d91-fd75-448c-8b12-da38fd68167c"),
                UUID.fromString("ba50aaf4-0da4-4701-8e96-c12bccb986f6")));
    }



    //Debugging only
    @GetMapping("/")
    public Iterable<Player> getPlayers(){
        return playerRepo.findAll();
    }

   @DeleteMapping("/")
    public Player delPlayer(@RequestParam String uuid) {
        Player player =  playerRepo.getPlayerByPrivateuuid(UUID.fromString(uuid));
        playerRepo.delete(player);
        return player;
    }

    @PostMapping("/")
    public Player newPlayer(@RequestParam String name) {
        Player player = new Player(name);
        playerRepo.save(player);
        return player;
    }

}