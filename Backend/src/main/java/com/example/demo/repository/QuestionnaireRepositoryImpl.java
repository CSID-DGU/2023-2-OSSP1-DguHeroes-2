package com.example.demo.repository;

import com.example.demo.domain.Questionnaire;
import com.example.demo.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class QuestionnaireRepositoryImpl implements QuestionnaireRepository{
    private EntityManager em;
    public QuestionnaireRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(Questionnaire questionnaire){
        em.persist(questionnaire);
    }
    @Override
    public Questionnaire findQuestionnaire(String developmentStack) {
        String sql = "select q from Questionnaire q where q.development_stack = :development_stack";
        TypedQuery<Questionnaire> query = em.createQuery(sql, Questionnaire.class);
        query.setParameter("development_stack", developmentStack);

        List<Questionnaire> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;


    }

    @Override
    public List<Questionnaire> findAll() {return null;    }

    @Override
    public List<Questionnaire> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Questionnaire> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Questionnaire> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Questionnaire entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Questionnaire> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Questionnaire> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Questionnaire> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Questionnaire> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Questionnaire> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Questionnaire> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Questionnaire> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Questionnaire getOne(Integer integer) {
        return null;
    }

    @Override
    public Questionnaire getById(Integer integer) {
        return null;
    }

    @Override
    public Questionnaire getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Questionnaire> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Questionnaire> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Questionnaire> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Questionnaire> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Questionnaire> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Questionnaire> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Questionnaire, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
