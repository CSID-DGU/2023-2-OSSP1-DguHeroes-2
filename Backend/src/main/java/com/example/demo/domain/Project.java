package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Project_id;
    private String project_title;
    private String project_content;
    private int like_count;
    private int visited_number;
    private Timestamp created_at;
    public Timestamp getCreatedAt() {
        return created_at;
    }


    private Timestamp updated_at;
    private String project_type;
    private String project_start_date;
    private String project_end_date;
    private int location;
    private String is_available;
    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private List<Member> members;
    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private List<Invitation> invitations;
    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private List<ProjectLike> project_likes;
    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private List<ProjectStack> project_stacks;
    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private List<Apply> applys;


}
