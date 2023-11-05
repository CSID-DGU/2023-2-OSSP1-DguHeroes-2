package com.example.demo.repository;

import com.example.demo.domain.ProjectStack;
import com.example.demo.domain.*;
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
public class ProjectStackRepositoryImpl implements ProjectStackRepository{

    private EntityManager em;
    public ProjectStackRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public ProjectStack insert(ProjectStack projectStack){
        em.persist(projectStack);
        return projectStack;
    }

    @Override
    public List<ProjectStack> findStackByProjectId(int project_id){
        String sql = "select projectStack.development_stack from ProjectStack projectStack where project = :project_id";
        TypedQuery<ProjectStack> query = em.createQuery(sql, ProjectStack.class);
        query.setParameter("project_id", project_id);
        List<ProjectStack> list = query.getResultList();
        return list;
    }

    @Override
    public List<ProjectStack> findAll() {
        return null;
    }

    @Override
    public List<ProjectStack> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ProjectStack> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ProjectStack> findAllById(Iterable<Integer> integers) {
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
    public void delete(ProjectStack entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends ProjectStack> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ProjectStack> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ProjectStack> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ProjectStack> findById(Integer integer) {
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
    public <S extends ProjectStack> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ProjectStack> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ProjectStack> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ProjectStack getOne(Integer integer) {
        return null;
    }

    @Override
    public ProjectStack getById(Integer integer) {
        return null;
    }

    @Override
    public ProjectStack getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends ProjectStack> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ProjectStack> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ProjectStack> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ProjectStack> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ProjectStack> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ProjectStack> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ProjectStack, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
