package server.Games.Components;

import server.Games.Games;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

public class Invitation {
    private static final Set<UUID> invitationUUID = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    private final Games game;
    private final UUID uuid;

    private final UUID reciver;
    private final UUID invitant;


    public Invitation(Games game,UUID reciver,UUID invitant) {

        this.game = game;
        this.reciver = reciver;
        this.invitant = invitant;
        UUID uuid = UUID.randomUUID();
        while(invitationUUID.contains(uuid)){
            uuid = UUID.randomUUID();
        }
        this.uuid = uuid;
        invitationUUID.add(this.uuid);
    }


    /**/

    public Games getGame() {
        return game;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getReciver() {
        return reciver;
    }

    public UUID getInvitant() {
        return invitant;
    }
}
