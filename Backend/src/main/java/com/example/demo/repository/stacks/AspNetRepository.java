package com.example.demo.repository.stacks;

import com.example.demo.domain.Apply;
import com.example.demo.domain.stacks.AspNet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AspNetRepository extends JpaRepository<AspNet, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE AspNet d SET d.score = :score WHERE d.user.id = :userId")
    void updateScoreByUserId(Long userId, Float score);
}