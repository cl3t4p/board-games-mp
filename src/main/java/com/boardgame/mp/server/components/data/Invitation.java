package com.boardgame.mp.server.components.data;


import com.boardgame.mp.server.games.Games;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Invitation {

    Games game;
    @Id
    @GeneratedValue(generator ="UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column( updatable = false, nullable = false)
    UUID uuid;
    UUID owneruuid;
    UUID reciveruuid;



    public Invitation(Games game,UUID reciver,UUID owner) {
        this.game = game;
        this.reciveruuid = reciver;
        this.owneruuid = owner;
    }




}
