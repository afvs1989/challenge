package com.afvs.challenge.services;

import com.afvs.challenge.models.dtos.VoteDto;
import com.afvs.challenge.utils.exeptions.CustomException;

public interface VoteService {
    public void saveVote(VoteDto voteDto) throws CustomException;
}
