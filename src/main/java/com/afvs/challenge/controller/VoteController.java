package com.afvs.challenge.controller;

import com.afvs.challenge.models.dtos.GenericResponseMessageDto;
import com.afvs.challenge.models.dtos.VoteDto;
import com.afvs.challenge.services.VoteService;
import com.afvs.challenge.utils.exeptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api/v1/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public GenericResponseMessageDto saveVote(@RequestBody VoteDto voteDto) {
        GenericResponseMessageDto genericResponseMessageDto = new GenericResponseMessageDto();
        try {
            this.voteService.saveVote(voteDto);
            genericResponseMessageDto.setMessage("Tu voto fue procesado con exito");
        } catch (CustomException e) {
            genericResponseMessageDto.setMessage(e.getMessage());
        } catch (NoSuchElementException e) {
            genericResponseMessageDto.setMessage("Usuario No existe");
        } catch (Exception e) {
            System.out.println("En esta linea se puede enviar a logear");
        }
        return genericResponseMessageDto;
    }
}
