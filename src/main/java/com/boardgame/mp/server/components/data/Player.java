package com.boardgame.mp.server.components.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Player {

  private String name;

  @Id
  @Column(updatable = false, nullable = false)
  private UUID privateuuid;

  @Column(updatable = false, nullable = false)
  private UUID publicuuid;

  public Player(String name) {
    this.name = name;
    publicuuid = UUID.randomUUID();
    privateuuid = UUID.randomUUID();
  }

  public Player() {}

  public HashMap<String, Object> safeData() {
    HashMap<String, Object> result = new HashMap<>();
    result.put("name", name);
    result.put("publicUUID", publicuuid);
    return result;
  }
}
