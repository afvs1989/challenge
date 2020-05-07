package com.afvs.challenge.models.dtos;

public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String roleName;


    public UserDto() {
    }

    public UserDto(Long id, String name, String username, String roleName) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
