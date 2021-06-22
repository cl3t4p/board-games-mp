package  com.boardgame.mp.server.components.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Player {

    private String name;

    @Id
    @GeneratedValue(generator ="UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID privateuuid;


    @Column(updatable = false, nullable = false)
    private UUID publicuuid;


    public Player(String name) {
        this.name = name;
        publicuuid = UUID.randomUUID();
    }

    public Player() {

    }


}
