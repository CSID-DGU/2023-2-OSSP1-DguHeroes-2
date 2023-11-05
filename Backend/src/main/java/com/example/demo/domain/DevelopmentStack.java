package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="developmentStack")
public class DevelopmentStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stack_id;
    @ManyToOne
    @JsonBackReference
    private User user;
    private String development_stack;
    private int grade;
    private int version;
    private int score_percent;

}
