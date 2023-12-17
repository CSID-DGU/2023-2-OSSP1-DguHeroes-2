package com.example.demo.domain.stacks;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class StackBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

}
