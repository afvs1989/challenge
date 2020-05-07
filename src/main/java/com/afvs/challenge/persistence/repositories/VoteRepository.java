package com.afvs.challenge.persistence.repositories;

import com.afvs.challenge.persistence.entities.VoteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository extends CrudRepository<VoteEntity, Long> {

    @Query("select v from VoteEntity v where v.userVoteId=?1 AND v.userReceivesVoteId.zone.id=?2")
    List<VoteEntity> findByUserZone(Long userVoteId, Long zoneId);

    @Query("SELECT DISTINCT v.userReceivesVoteId.name, COUNT(v.userReceivesVoteId.name) \n" +
            "FROM VoteEntity v \n" +
            "GROUP BY v.userReceivesVoteId.name \n" +
            "ORDER BY COUNT(v.userReceivesVoteId.name) DESC")
    List<VoteEntity> getUsersMoreVotes(LocalDateTime startDate, LocalDateTime endDate);
}
