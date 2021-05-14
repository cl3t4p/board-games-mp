package server.Controller;

import org.springframework.web.bind.annotation.*;
import server.Games.Games;
import server.Games.Components.Invitation;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/invite")
public class InvitationController {

    private static final Set<Invitation> invitations = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));


    @GetMapping("/")
    public Set<Invitation> showInvitations(){
        return invitations;
    }

    @DeleteMapping("/")
    public Invitation delInvitation(@RequestParam String uuid) {
        Invitation result = invitations.stream().filter(invitation -> invitation.getUuid().equals(UUID.fromString(uuid))).findAny().orElse(null);
        invitations.remove(result);
        return result;
    }

    @PostMapping("/")
    public Invitation newPlayer(@RequestParam String p1uuid,@RequestParam String p2uuid,@RequestParam Integer game) {
        Invitation invitation = new Invitation(Games.getGamesByID(game),UUID.fromString(p1uuid),UUID.fromString(p2uuid));
        invitations.add(invitation);
        return invitation;
    }

}
