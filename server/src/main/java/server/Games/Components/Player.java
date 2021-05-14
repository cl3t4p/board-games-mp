package server.Games.Components;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

public class Player {

    private static final Set<UUID> playerUUID = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));


    private final String name;
    private final UUID private_uuid;
    private final UUID public_uuid;


    public Player(String name) {
        this.name = name;
        UUID private_uuid = UUID.randomUUID();
        UUID public_uuid = UUID.randomUUID();
        while(playerUUID.contains(private_uuid) || playerUUID.contains(public_uuid)){
            private_uuid = UUID.randomUUID();
            public_uuid = UUID.randomUUID();
        }
        this.private_uuid = private_uuid;
        this.public_uuid = public_uuid;
        playerUUID.add(this.private_uuid);
        playerUUID.add(this.public_uuid);
    }




    public String getName() {
        return name;
    }

    public UUID getPrivate_uuid() {
        return private_uuid;
    }

    public UUID getPublic_uuid() {
        return public_uuid;
    }
}
