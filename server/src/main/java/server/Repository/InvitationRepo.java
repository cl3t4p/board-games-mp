package server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.Components.Invitation;

import java.util.List;
import java.util.UUID;

public interface InvitationRepo extends JpaRepository<Invitation, Integer> {

    Invitation getInvitationByUuid(UUID privateuuid);


    List<Invitation> getInvitationByReciveruuid(UUID uuid);
}
