package com.afvs.challenge.services.impl;

import com.afvs.challenge.models.dtos.LoginDto;
import com.afvs.challenge.models.dtos.UserDto;
import com.afvs.challenge.persistence.entities.RoleEntity;
import com.afvs.challenge.persistence.entities.UserEntity;
import com.afvs.challenge.persistence.entities.VoteEntity;
import com.afvs.challenge.persistence.entities.ZoneEntity;
import com.afvs.challenge.persistence.repositories.UserRepository;
import com.afvs.challenge.persistence.repositories.VoteRepository;
import com.afvs.challenge.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Value("${jwt.secret}")
    private String secretJwt;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VoteRepository voteRepository;

    @PostConstruct
    public void insertDefaultInfo() {
        Random random = new Random();
        String[] zones = {"Team player", "Technical referent", "Key Player", "Client satisfaction", "Motivation"};
        List<UserEntity> userEntities = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            UserEntity userEntity = new UserEntity("Andres Valenzuela" + i, "user" + i,
                    "user" + i);
            RoleEntity roleEntity;
            if (i % 2 == 0) {
                roleEntity = new RoleEntity("ADMIN");
            } else {
                roleEntity = new RoleEntity("EMPLEADO");
            }
            userEntity.setRole(roleEntity);
            ZoneEntity zoneEntity = new ZoneEntity(zones[random.nextInt(zones.length)]);
            userEntity.setZone(zoneEntity);
            userEntities.add(userEntity);
        }
        this.userRepository.saveAll(userEntities);
    }

    @Override
    public List getAllUsers() {
        Iterable<UserEntity> usersEntities = this.userRepository.findAll();
        List<UserDto> userDtos = StreamSupport
                .stream(usersEntities.spliterator(), false)
                .map(x -> new UserDto(x.getId(), x.getName(), x.getUsername(), x.getRole().getName()))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List getUsersMoreVotes(Integer numberMonth) {
        List<VoteEntity> votesEntities = this.voteRepository.getUsersMoreVotes(LocalDateTime.now(), LocalDateTime.now());
        return votesEntities;
    }

    @Override
    public LoginDto login(String username, String password) {
        return new LoginDto(this.getJWTToken(username, "ADMIN"));
    }

    private String getJWTToken(String username, String rolName) {
        String secretKey = this.secretJwt;
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rolName);
        String token = Jwts
                .builder()
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return token;
    }
}
