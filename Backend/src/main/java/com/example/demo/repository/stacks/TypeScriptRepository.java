package com.example.demo.repository.stacks;

import com.example.demo.domain.Apply;
import com.example.demo.domain.stacks.TypeScript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TypeScriptRepository extends JpaRepository<TypeScript, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE TypeScript d SET d.score = :score WHERE d.user.id = :userId")
    void updateScoreByUserId(Long userId, Float score);
}