package server.Components;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Player {

    private String name;

    @Column(unique=true)
    private UUID privateuuid;

    private UUID publicuuid;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    public Player(String name) {
        this.name = name;
        UUID private_uuid = UUID.randomUUID();
        UUID public_uuid = UUID.randomUUID();
        this.privateuuid = private_uuid;
        this.publicuuid = public_uuid;
    }

    public Player(String name, UUID privateuuid, UUID publicuuid) {
        this.name = name;
        this.privateuuid = privateuuid;
        this.publicuuid = publicuuid;
    }

    public Player() {

    }


    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getPrivateuuid() {
        return privateuuid;
    }

    public void setPrivateuuid(UUID private_uuid) {
        this.privateuuid = private_uuid;
    }

    public UUID getPublicuuid() {
        return publicuuid;
    }

    public void setPublicuuid(UUID public_uuid) {
        this.publicuuid = public_uuid;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return publicuuid.toString();
    }
}
