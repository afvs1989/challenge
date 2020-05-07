package com.afvs.challenge.models.dtos;

public class VoteDto {
    private Long userVoteId;
    private Long userReceivesVoteId;
    private String commentary;

    public Long getUserVoteId() {
        return userVoteId;
    }

    public void setUserVoteId(Long userVoteId) {
        this.userVoteId = userVoteId;
    }

    public Long getUserReceivesVoteId() {
        return userReceivesVoteId;
    }

    public void setUserReceivesVoteId(Long userReceivesVoteId) {
        this.userReceivesVoteId = userReceivesVoteId;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
