package com.afvs.challenge.persistence.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String commentary;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserEntity userReceivesVoteId;
    @Column(nullable = false)
    private Long userVoteId;
    @Column(nullable = false)
    private LocalDateTime nowDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public UserEntity getUserReceivesVoteId() {
        return userReceivesVoteId;
    }

    public void setUserReceivesVoteId(UserEntity userReceivesVoteId) {
        this.userReceivesVoteId = userReceivesVoteId;
    }

    public Long getUserVoteId() {
        return userVoteId;
    }

    public void setUserVoteId(Long userVoteId) {
        this.userVoteId = userVoteId;
    }

    public LocalDateTime getNowDate() {
        return nowDate;
    }

    public void setNowDate(LocalDateTime nowDate) {
        this.nowDate = nowDate;
    }
}
