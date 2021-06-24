package com.boardgame.mp.server.components.data;

import com.boardgame.mp.server.games.Games;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.UUID;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Invitation {


  @Id
  @Column(updatable = false, nullable = false)
  UUID uuid;
  UUID owneruuid;
  UUID reciveruuid;

  Integer game;

  public Invitation(Games game, UUID reciver, UUID owner) {
    this.game = Games.getGames().indexOf(game);
    this.reciveruuid = reciver;
    this.owneruuid = owner;
    this.uuid = UUID.randomUUID();
  }

  public HashMap<String, String> safeInvitation() {
    HashMap<String, String> result = new HashMap<>();
    result.put("invitationuuid", uuid.toString());
    result.put("reciveruuid", uuid.toString());
    result.put("gametype", Games.getGamesByID(game).getName());
    return result;
  }
}
