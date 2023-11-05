package com.example.demo.repository;

import com.example.demo.domain.DevelopmentStack;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class DevelopmentStackRepositoryImpl implements DevelopmentStackRepository{
    private EntityManager em;
    public DevelopmentStackRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(DevelopmentStack developmentStack){
        em.persist(developmentStack);
    }

    @Override
    public DevelopmentStack findDevelopmentStack(Long user_id){
        String sql = "select developmentStack from DevelopmentStack developmentStack where user = :user_id";
        TypedQuery<DevelopmentStack> query = em.createQuery(sql, DevelopmentStack.class);
        query.setParameter("user_id", user_id);
        List<DevelopmentStack> list = query.getResultList();

        for (DevelopmentStack entity : list) {
            return entity;
        }
        return null;
    }

    @Override
    public List<DevelopmentStack> findUsersByStacks(List<String> requiredStacks){
        String sql = "select developmentStack.user from DevelopmentStack developmentStack where development_stack = :requiredStacks ";
        return null;

    }

    @Override
    public List<DevelopmentStack> findAll() {
        return null;
    }

    @Override
    public List<DevelopmentStack> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<DevelopmentStack> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<DevelopmentStack> findAllById(Iterable<Integer> integers) {
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
    public void delete(DevelopmentStack entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends DevelopmentStack> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends DevelopmentStack> S save(S entity) {
        return null;
    }

    @Override
    public <S extends DevelopmentStack> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<DevelopmentStack> findById(Integer integer) {
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
    public <S extends DevelopmentStack> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends DevelopmentStack> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<DevelopmentStack> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public DevelopmentStack getOne(Integer integer) {
        return null;
    }

    @Override
    public DevelopmentStack getById(Integer integer) {
        return null;
    }

    @Override
    public DevelopmentStack getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends DevelopmentStack> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends DevelopmentStack> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends DevelopmentStack> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends DevelopmentStack> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends DevelopmentStack> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends DevelopmentStack> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends DevelopmentStack, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
