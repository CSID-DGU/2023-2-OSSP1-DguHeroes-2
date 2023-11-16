package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String password;
    private File profile;
    private String introduce;
    private String email;
    private int score_percent;
    private int is_admin;
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Invitation> invitations;
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<ProjectLike> project_likes;
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<DevelopmentStack> development_stacks;
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Apply> applys;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Member> members;
}

