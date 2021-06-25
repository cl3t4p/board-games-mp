package com.boardgame.mp.server.controller;

import com.boardgame.mp.server.components.data.Invitation;
import com.boardgame.mp.server.components.data.Player;
import com.boardgame.mp.server.components.exception.NotFoundByUUID;
import com.boardgame.mp.server.components.exception.PlayerNotFoundByUUID;
import com.boardgame.mp.server.games.Games;
import com.boardgame.mp.server.games.game.Game;
import com.boardgame.mp.server.repository.GameRepo;
import com.boardgame.mp.server.repository.InvitationRepo;
import com.boardgame.mp.server.repository.PlayerRepo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invites")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvitationController {

  InvitationRepo invitationRepo;
  PlayerRepo playerRepo;
  GameRepo gameRepo;

  /**
   * Will show the invites that the person has
   *
   * @param uuid The Private UUID of the Player
   * @return List of invites
   */
  @PostMapping("/myinvites")
  public ResponseEntity<Object> showMyInvitations(@RequestParam UUID uuid) {
    Player player = playerRepo.getPlayerByPrivateuuid(uuid).orElseThrow(PlayerNotFoundByUUID::new);

    return ResponseEntity.ok()
        .body(
            invitationRepo.getInvitationsByReciveruuid(player.getPublicuuid()).stream()
                .map(invitation -> {
                  try {
                    return invitation.safeInvitation();
                  } catch (Exception exception) {
                    exception.printStackTrace();
                  }
                  return null;
                })
                .collect(Collectors.toSet()));
  }

  /**
   * Will get the ID and the name of the All the loaded Games
   *
   * @return List of Game Names
   */
  @GetMapping("types")
  public HashMap<Integer, String> getTypesGames() {
    HashMap<Integer, String> hashMap = new HashMap<>();
    for (int i = 0; i < Games.getGamesList().size(); i++) {
      hashMap.put(i, Games.getGamesList().get(i).getName());
    }
    return hashMap;
  }

  /**
   * For Deleting an invitation
   *
   * @param uuid InvitationUUID
   * @return The deleted Invitation
   */
  @DeleteMapping("/")
  public ResponseEntity<Object> delInvitation(@RequestParam String uuid) throws Exception {
    Invitation invitation =
        invitationRepo
            .getInvitationByUuid(UUID.fromString(uuid))
            .orElseThrow(() -> new NotFoundByUUID("There is no Invite with that UUID!"));

    invitationRepo.delete(invitation);
    return ResponseEntity.ok().body(invitation.safeInvitation());
  }

  /**
   * Create a new invitation
   *
   * @param owner PrivateUUID of the owner
   * @param reciver PublicUUID of the reciver
   * @param game Game ID
   * @return Safe version of the Invitation
   */
  @PostMapping("/")
  public ResponseEntity<Object> newInvitation(
      @RequestParam UUID owner, @RequestParam UUID reciver, @RequestParam Integer game) throws Exception {

    if (playerRepo.existsPlayerByPrivateuuid(owner)
        && playerRepo.existsPlayerByPublicuuid(reciver)) {
      Invitation invitation = new Invitation(Games.getGamesByID(game), reciver, owner);
      invitationRepo.save(invitation);

      return ResponseEntity.ok().body(invitation.safeInvitation());
    }
    throw new PlayerNotFoundByUUID();
  }

  /**
   * Create the instance of a game and enable the connection for through websocket with the GameUUID
   *
   * @param playeruuid PrivateUUID of the reciver
   * @param invitationuuid Invitation UUID
   * @return Game UUID
   */
  @PostMapping("/newgame")
  public ResponseEntity<Object> newGame(
      @RequestParam UUID playeruuid, @RequestParam UUID invitationuuid) throws Exception {
    Invitation invitation =
        invitationRepo
            .getInvitationByUuid(invitationuuid)
            .orElseThrow(() -> new NotFoundByUUID("There is no Invite with that UUID!"));

    Player player2 =
        playerRepo.getPlayerByPrivateuuid(playeruuid).orElseThrow(PlayerNotFoundByUUID::new);

    if (!invitation.getReciveruuid().equals(player2.getPublicuuid()))
      throw new Exception("You are not the invited Player");

    Player player1 =
        playerRepo
            .getPlayerByPrivateuuid(invitation.getOwneruuid())
            .orElseThrow(PlayerNotFoundByUUID::new);

    Game game = Games.getGamesByID(invitation.getGame()).createInstance(player1, player2, gameRepo);

    gameRepo.add(game);

    invitationRepo.delete(invitation);

    return ResponseEntity.ok().body(game);
  }
}
