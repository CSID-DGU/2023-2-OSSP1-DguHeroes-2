package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevelopmentStackRepository extends JpaRepository<DevelopmentStack, Integer> {
    public void insert(DevelopmentStack developmentStack);
    public DevelopmentStack findDevelopmentStack(Long user_id);

    public List<DevelopmentStack> findUsersByStacks(List<String> requiredStacks);
}
