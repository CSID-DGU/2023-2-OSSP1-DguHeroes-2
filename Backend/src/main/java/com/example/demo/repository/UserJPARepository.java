package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserJPARepository  extends JpaRepository<User, Long> {

    // 깃허브아이디로 User 찾기
    User findByGithubId(String githubId);


    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.stacks = :stacks WHERE u.id = :userId")
    void updateStacksById(Long userId, String stacks);

    @Query("SELECT u.stacks FROM User u WHERE u.id = :userId")
    String findStacksByUserId(Long userId);

    @Query("SELECT u.githubId FROM User u WHERE u.id = :userId")
    String findGithubIdByUserId(Long userId);
}
