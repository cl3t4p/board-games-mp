package com.boardgame.mp.server.repository;

import com.boardgame.mp.server.components.data.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvitationRepo extends JpaRepository<Invitation, UUID> {

  Optional<Invitation> getInvitationByUuid(UUID privateuuid);

  List<Invitation> getInvitationsByReciveruuid(UUID uuid);
}
