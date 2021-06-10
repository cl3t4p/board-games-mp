package server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.Components.Player;

import java.util.UUID;

public interface PlayerRepo extends JpaRepository<Player, Integer> {

    Player getPlayerByPrivateuuid(UUID privateuuid);
    Player getPlayerByPublicuuid(UUID publicuuid);

    boolean existsPlayerByPrivateuuid(UUID privateuuid);
    boolean existsPlayerByPublicuuid(UUID publicuuid);
}