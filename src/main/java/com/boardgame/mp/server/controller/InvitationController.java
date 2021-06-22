package  com.boardgame.mp.server.controller;


import com.boardgame.mp.server.Repository.GameRepo;
import com.boardgame.mp.server.Repository.InvitationRepo;
import com.boardgame.mp.server.Repository.PlayerRepo;
import com.boardgame.mp.server.components.Exception.NotFoundByUUID;
import com.boardgame.mp.server.components.Exception.PlayerNotFoundByUUID;
import com.boardgame.mp.server.components.data.Invitation;
import com.boardgame.mp.server.components.data.Player;
import com.boardgame.mp.server.games.Games;
import com.boardgame.mp.server.games.game.Game;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.*;

@RestController
@RequestMapping("/invites")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class InvitationController {


    InvitationRepo invitationRepo;
    PlayerRepo playerRepo;
    GameRepo gameRepo;

    @PostMapping("/myinvites")
    public ResponseEntity<List<Invitation>> showMyInvitations(@RequestParam UUID uuid){
        Player player = playerRepo.getPlayerByPrivateuuid(uuid)
                .orElseThrow(PlayerNotFoundByUUID::new);

        return ResponseEntity.ok().body(invitationRepo.getInvitationByReciveruuid(player.getPublicuuid()));
    }

    @GetMapping("types")
    public Games[] getTypesGames(){
        return Games.class.getEnumConstants();
    }


    @DeleteMapping("/")
    public ResponseEntity<Object> delInvitation(@RequestParam String uuid) {
        Invitation invitation = invitationRepo.getInvitationByUuid(UUID.fromString(uuid)).orElseThrow(()-> new NotFoundByUUID("There is no Invite with that UUID!"));
        invitationRepo.delete(invitation);
        return ResponseEntity.ok().body(invitation);
    }

    @PostMapping("/")
    public ResponseEntity<Object> newInvitation(@RequestParam UUID owner,@RequestParam UUID reciver,@RequestParam Integer game) {

        if(playerRepo.existsPlayerByPrivateuuid(owner) && playerRepo.existsPlayerByPublicuuid(reciver)){
            Invitation invitation = new Invitation(Games.getGamesByID(game), reciver, owner);
            invitationRepo.save(invitation);
            return ResponseEntity.ok().body(invitation);
        }
        throw new PlayerNotFoundByUUID();
    }

    //Create the instance of a game and enable connection for the websocket on the GameUUID
    @PostMapping("/newgame")
    public ResponseEntity<Object> newGame(@RequestParam UUID playeruuid, @RequestParam UUID gameuuid) throws Exception {
        Invitation invitation = invitationRepo.getInvitationByUuid(gameuuid)
                .orElseThrow(()-> new NotFoundByUUID("There is no Invite with that UUID!"));

        Player player2 = playerRepo.getPlayerByPrivateuuid(playeruuid)
                .orElseThrow(PlayerNotFoundByUUID::new);



        if(!invitation.getReciveruuid().equals(player2.getPublicuuid()))
            throw new Exception("You are not the invited Player");

        Player player1 = playerRepo.getPlayerByPrivateuuid(invitation.getOwneruuid())
                .orElseThrow(PlayerNotFoundByUUID::new);

        Game game = invitation.getGame().createInstance(player1,player2,gameRepo);

        gameRepo.add(game);

        invitationRepo.delete(invitation);

        return ResponseEntity.ok().body(game);
    }







}
