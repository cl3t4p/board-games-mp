package server.Controller;


import org.springframework.web.bind.annotation.*;
import server.Games.Components.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private static final Set<Player> players = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));


    public PlayerController() {
        players.add(new Player("Alex"));
        players.add(new Player("Elisa"));
    }

    @GetMapping("/")
    public Set<Player> getPlayers(){
        return players;
    }

    @DeleteMapping("/")
    public Player delPlayer(@RequestParam String uuid) {
        Player result = players.stream().filter(player -> player.getPrivate_uuid().equals(UUID.fromString(uuid))).findAny().orElse(null);
        players.remove(result);
        return  result;
    }

    @PostMapping("/")
    public Player newPlayer(@RequestParam String name) {
        Player player = new Player(name);
        players.add(player);
        return player;
    }

}