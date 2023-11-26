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
        Optional<Questionnaire> questionnaireOptional = question_rp.findById(questionnaire.getId());
        if (questionnaireOptional.isPresent()) {
            Questionnaire questionnaire1 = questionnaireOptional.get();
            // update only present fields
            if(questionnaire.getContent() != null) questionnaire1.setContent(questionnaire.getContent());
            if(questionnaire.getTotal() != 0) questionnaire1.setTotal(questionnaire.getTotal());
            if(questionnaire.getVersion() != 0) questionnaire1.setVersion(questionnaire.getVersion());
            question_rp.save(questionnaire1);
        }
    }

   @Transactional
    public void modify(Questionnaire questionnaire) {
        Optional<Questionnaire> questionnaireOptional = question_rp.findById(questionnaire.getId());
        if (questionnaireOptional.isPresent()) {
            Questionnaire questionnaire1 = questionnaireOptional.get();
            // update only present fields
            if(questionnaire.getContent() != null) questionnaire1.setContent(questionnaire.getContent());
            if(questionnaire.getTotal() != 0) questionnaire1.setTotal(questionnaire.getTotal());
            if(questionnaire.getVersion() != 0) questionnaire1.setVersion(questionnaire.getVersion());
            question_rp.save(questionnaire1);
        }
    }
}