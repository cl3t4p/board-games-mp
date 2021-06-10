package server.Components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import server.Games.Games;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Invitation {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Games game;


    @Column(unique = true,updatable = false)
    private UUID uuid;


    private UUID owneruuid;
    private UUID reciveruuid;



    public Invitation(Games game,UUID reciver,UUID owner) {
        this.game = game;
        this.reciveruuid = reciver;
        this.owneruuid = owner;
        this.uuid = UUID.randomUUID();
    }

    public Invitation() {
    }


    //Getters and Setters

    public Games getGame() {
        return game;
    }

    public void setGame(Games game) {
        this.game = game;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getReciveruuid() {
        return reciveruuid;
    }

    public void setReciver(UUID reciver) {
        this.reciveruuid = reciver;
    }

    @JsonIgnore
    public UUID getOwneruuid() {
        return owneruuid;
    }

    public void setOwneruuid(UUID invitant) {
        this.owneruuid = invitant;
    }
}
