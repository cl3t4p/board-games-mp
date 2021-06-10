package server.Controller;

import org.springframework.web.bind.annotation.*;
import server.Components.Exception.NoPlayerByUUID;
import server.Games.Game.Game;
import server.Components.Player;
import server.Games.Games;
import server.Components.Invitation;
import server.Games.TicTacToe;
import server.Repository.InvitationRepo;
import server.Repository.PlayerRepo;

import java.util.*;

@RestController
@RequestMapping("/invites")
public class InvitationController {


    private final InvitationRepo invitationRepo;
    private final PlayerRepo playerRepo;

    public InvitationController(InvitationRepo invitationRepo,PlayerRepo playerRepo) {
        this.invitationRepo = invitationRepo;
        this.playerRepo = playerRepo;
    }

    @GetMapping("/myinvites")
    public List<Invitation> showMyInvitations(@RequestParam UUID uuid){
        Player player = playerRepo.getPlayerByPrivateuuid(uuid);
        return invitationRepo.getInvitationByReciveruuid(player.getPublicuuid());
    }

    @GetMapping("/")
    public List<Invitation> allInvite(){
        return invitationRepo.findAll();
    }

    @DeleteMapping("/")
    public Invitation delInvitation(@RequestParam String uuid) {

        Invitation invitation = invitationRepo.getInvitationByUuid(UUID.fromString(uuid));
        invitationRepo.delete(invitation);
        return invitation;
    }

    @PostMapping("/")
    public Invitation newInvitation(@RequestParam UUID owner,@RequestParam UUID reciver,@RequestParam Integer game) {
        if(playerRepo.existsPlayerByPrivateuuid(owner) && playerRepo.existsPlayerByPublicuuid(reciver)){
            Invitation invitation = new Invitation(Games.getGamesByID(game), reciver, owner);
            invitationRepo.save(invitation);
            return invitation;
        }
        return null;
    }
    @PostMapping("/newgame")
    public Game newGame(@RequestParam UUID playeruuid,@RequestParam UUID gameuuid){
        Invitation invitation = invitationRepo.getInvitationByUuid(gameuuid);
        Player player2 = playerRepo.getPlayerByPrivateuuid(playeruuid);
        if(!invitation.getReciveruuid().equals(player2.getPublicuuid()))
            return null;
        Player player1 = playerRepo.getPlayerByPrivateuuid(invitation.getOwneruuid());
        Game game = new TicTacToe(player1,player2);
        invitationRepo.delete(invitation);
        return game;
    }





}
