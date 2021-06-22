package  com.boardgame.mp.server.repository;



import com.boardgame.mp.server.components.data.Player;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;


@Repository
public interface PlayerRepo extends JpaRepository<Player, UUID> {

    Optional<Player> getPlayerByPrivateuuid(UUID privateuuid);
    Optional<Player> getPlayerByPublicuuid(UUID publicuuid);

    boolean existsPlayerByPrivateuuid(UUID privateuuid);
    boolean existsPlayerByPublicuuid(UUID publicuuid);
}