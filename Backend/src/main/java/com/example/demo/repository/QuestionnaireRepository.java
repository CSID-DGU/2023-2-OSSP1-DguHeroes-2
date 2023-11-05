package com.example.demo.repository;

import com.example.demo.domain.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
    public void insert(Questionnaire questionnaire);
    public Questionnaire findQuestionnaire(String developmentStack);
}
