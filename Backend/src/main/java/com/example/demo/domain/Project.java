package com.example.demo.domain;

import com.example.demo.domain.position.ProjectBack;
import com.example.demo.domain.position.ProjectEtc;
import com.example.demo.domain.position.ProjectFront;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;
    private int like_count;
    private int visited_number;
    private Timestamp created_at;
    public Timestamp getCreatedAt() {
        return created_at;
    }

    private Timestamp updated_at;
    private String project_type;
    private String start_date;
    private String end_date;
    private int location;
    private String is_available;
    private Float avg_score;

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectFront> projectFronts = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectBack> projectBacks = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectEtc> projectEtcs = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Invitation> invitations = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectLike> projectLikes = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Apply> applies = new ArrayList<>();

}
