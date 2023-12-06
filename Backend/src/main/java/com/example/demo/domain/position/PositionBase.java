package com.example.demo.domain.position;

import com.example.demo.domain.Project;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class PositionBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer required_member;
    private Integer current_member;
    private String stacks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}
