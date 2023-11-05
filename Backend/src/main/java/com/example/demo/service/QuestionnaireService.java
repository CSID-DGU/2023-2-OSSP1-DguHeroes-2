/*package com.example.demo.service;

import com.example.demo.domain.Questionnaire;
import com.example.demo.domain.User;
import com.example.demo.repository.DevelopmentStackRepository;
import com.example.demo.repository.QuestionnaireRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class QuestionnaireService {
    QuestionnaireRepository question_rp;
    public QuestionnaireService(QuestionnaireRepository questionnaireRepository){
        question_rp = questionnaireRepository;
    }

    public void insert(Questionnaire questionnaire){
        question_rp.insert(questionnaire);
    }

    public Questionnaire findQuestionnaire(String developmentStack){
        return question_rp.findQuestionnaire(developmentStack);
    }
}*/
package com.example.demo.service;

import com.example.demo.domain.Questionnaire;
import com.example.demo.repository.QuestionnaireRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireService {
    QuestionnaireRepository question_rp;
    public QuestionnaireService(QuestionnaireRepository questionnaireRepository){
        question_rp = questionnaireRepository;
    }

    @Transactional
    public void insert(Questionnaire questionnaire){
        question_rp.insert(questionnaire);
    }

    public Questionnaire findQuestionnaire(String developmentStack){
        return question_rp.findQuestionnaire(developmentStack);
    }

    public List<Questionnaire> findAll(){
        return question_rp.findAll();
    }

    @Transactional
    public void update(Questionnaire questionnaire) {
        Optional<Questionnaire> questionnaireOptional = question_rp.findById(questionnaire.getQuestionnaire_id());
        if (questionnaireOptional.isPresent()) {
            Questionnaire questionnaire1 = questionnaireOptional.get();
            // update only present fields
            if(questionnaire.getQuestionnaire_content() != null) questionnaire1.setQuestionnaire_content(questionnaire.getQuestionnaire_content());
            if(questionnaire.getQuestionnaire_total() != 0) questionnaire1.setQuestionnaire_total(questionnaire.getQuestionnaire_total());
            if(questionnaire.getVersion() != 0) questionnaire1.setVersion(questionnaire.getVersion());
            question_rp.save(questionnaire1);
        }
    }

   @Transactional
    public void modify(Questionnaire questionnaire) {
        Optional<Questionnaire> questionnaireOptional = question_rp.findById(questionnaire.getQuestionnaire_id());
        if (questionnaireOptional.isPresent()) {
            Questionnaire questionnaire1 = questionnaireOptional.get();
            // update only present fields
            if(questionnaire.getQuestionnaire_content() != null) questionnaire1.setQuestionnaire_content(questionnaire.getQuestionnaire_content());
            if(questionnaire.getQuestionnaire_total() != 0) questionnaire1.setQuestionnaire_total(questionnaire.getQuestionnaire_total());
            if(questionnaire.getVersion() != 0) questionnaire1.setVersion(questionnaire.getVersion());
            question_rp.save(questionnaire1);
        }
    }
}