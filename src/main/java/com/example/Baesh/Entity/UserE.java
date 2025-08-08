package com.example.Baesh.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String residence;

    @Column(nullable = false)
    private String role;

    @Column
    private String intro;

    @Column
    private String imageUrl;

    @Column
    private String aiModelName;

    @ManyToMany
    @JoinTable(
            name = "user_tag",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagE> tags = new HashSet<>();

    public UserE(){}

    public UserE(String userId, String password, String name ,String email, String phoneNumber, String residence,String intro , String role){
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.residence = residence;
        this.intro = intro;
        this.role = role;
    }
    //구글 로그인
    public UserE(String userId, String name ,String email, String role){
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.residence = residence;
        this.role = role;
    }

}
