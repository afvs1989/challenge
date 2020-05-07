package com.afvs.challenge.services.impl;

import com.afvs.challenge.models.dtos.VoteDto;
import com.afvs.challenge.persistence.entities.UserEntity;
import com.afvs.challenge.persistence.entities.VoteEntity;
import com.afvs.challenge.persistence.repositories.UserRepository;
import com.afvs.challenge.persistence.repositories.VoteRepository;
import com.afvs.challenge.services.VoteService;
import com.afvs.challenge.utils.exeptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveVote(VoteDto voteDto) throws CustomException {
        if (voteDto.getUserVoteId() == voteDto.getUserReceivesVoteId()) {
            throw new CustomException("No puede realizar voto al mismo usuario");
        }
        this.userRepository.findById(voteDto.getUserVoteId()).get();
        UserEntity userEntity = this.userRepository.findById(voteDto.getUserReceivesVoteId()).get();
        Long userReceivesZoneId = userEntity.getZone().getId();
        List<VoteEntity> votesEntities = this.voteRepository.findByUserZone(voteDto.getUserVoteId(), userReceivesZoneId);
        if (votesEntities.size() != 0) {
            throw new CustomException("El usuario votante ya tiene un voto a esta área");
        }
        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setCommentary(voteDto.getCommentary());
        voteEntity.setUserReceivesVoteId(userEntity);
        voteEntity.setUserVoteId(voteDto.getUserVoteId());
        voteEntity.setNowDate(LocalDateTime.now());
        this.voteRepository.save(voteEntity);
    }
}
