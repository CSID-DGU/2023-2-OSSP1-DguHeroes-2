package com.example.demo.repository;

import com.example.demo.domain.ProjectMember;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class ProjectMemberRepositoryImpl implements ProjectMemberRepository {
    private EntityManager em;
    public ProjectMemberRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(ProjectMember projectMember){
        em.persist(projectMember);
    }

    @Override
    public List<ProjectMember> findAll() {
        return null;
    }

    @Override
    public List<ProjectMember> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ProjectMember> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ProjectMember> findAllById(Iterable<Integer> integers) {
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
    public void delete(ProjectMember entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends ProjectMember> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ProjectMember> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ProjectMember> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ProjectMember> findById(Integer integer) {
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
    public <S extends ProjectMember> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ProjectMember> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ProjectMember> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ProjectMember getOne(Integer integer) {
        return null;
    }

    @Override
    public ProjectMember getById(Integer integer) {
        return null;
    }

    @Override
    public ProjectMember getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends ProjectMember> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ProjectMember> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ProjectMember> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ProjectMember> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ProjectMember> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ProjectMember> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ProjectMember, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
