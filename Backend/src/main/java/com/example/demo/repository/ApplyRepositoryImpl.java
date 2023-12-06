package com.example.demo.repository;

import com.example.demo.domain.Apply;
import com.example.demo.domain.Invitation;
import com.example.demo.domain.Project;
import com.example.demo.domain.User;
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
public class ApplyRepositoryImpl implements ApplyRepository{
    private EntityManager em;
    public ApplyRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(Project project, User user){
        Invitation invitation = Invitation.builder()
                .project(project)
                .user(user)
                .state("PENDING")
                .build();
        em.persist(invitation);
    }
    @Override
    public Apply findById(Long apply_id){
        String sql = "select apply from Apply apply where id = :apply_id";
        TypedQuery<Apply> query = em.createQuery(sql, Apply.class);
        query.setParameter("apply_id", apply_id);
        List<Apply> list = query.getResultList();
        for (Apply entity : list) {
            return entity;
        }
        return null;
    }
    @Override
    public Long findIdByProject_id(int project_id){
        String sql = "select apply from Apply apply where project = :project_id";
        TypedQuery<Apply> query = em.createQuery(sql, Apply.class);
        query.setParameter("project_id", project_id);
        List<Apply> list = query.getResultList();
        for (Apply entity : list) {
            return entity.getId();
        }
        return null;
    }

    @Override
    public List<Project> findPendingProjects(String user_id){
        String sql = "select apply.project from Apply apply where apply.user = :user_id and apply.state = :state";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setParameter("user_id", user_id);
        query.setParameter("state","PENDING");
        List<Project> list = query.getResultList();
        return list;
    }



    @Override
    public void updateState(Long apply_id){
        Apply apply = findById(apply_id);
        apply.setState("BELONG");
        em.persist(apply);
    }

    @Override
    //project
    public List<User> findApplyUsers(int project_id){
        String sql = "select apply.user from Apply apply where apply.project = :project_id and apply.state = :state";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        query.setParameter("project_id", project_id);
        query.setParameter("state","PENDING");
        List<User> list = query.getResultList();
        return list;
    }

    @Override
    public List<Apply> findAll() {
        return null;
    }

    @Override
    public List<Apply> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Apply> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Apply> findAllById(Iterable<Integer> integers) {
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
    public void delete(Apply entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Apply> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Apply> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Apply> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Apply> findById(Integer integer) {
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
    public <S extends Apply> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Apply> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Apply> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Apply getOne(Integer integer) {
        return null;
    }

    @Override
    public Apply getById(Integer integer) {
        return null;
    }

    @Override
    public Apply getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Apply> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Apply> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Apply> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Apply> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Apply> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Apply> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Apply, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
