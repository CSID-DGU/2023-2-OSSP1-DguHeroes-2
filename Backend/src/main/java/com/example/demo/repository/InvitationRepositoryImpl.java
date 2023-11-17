package com.example.demo.repository;

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
public class InvitationRepositoryImpl implements InvitationRepository{
    private EntityManager em;
    public InvitationRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(Project project, User user){
        Invitation invitation = new Invitation();
        invitation.setProject(project);
        invitation.setUser(user);
        invitation.setState("초대됨");
        em.persist(invitation);
    }
    @Override
    public Invitation findById(Long invitation_id){
        String sql = "select invitation from Invitation invitation where id = :invitation_id";
        TypedQuery<Invitation> query = em.createQuery(sql, Invitation.class);
        query.setParameter("invitation_id", invitation_id);
        List<Invitation> list = query.getResultList();
        for (Invitation entity : list) {
            return entity; 
        }
        return null;
    }
    @Override
    public List<Project> findInviteProjectList(String user_id){
        String sql = "select invitation.project from Invitation invitation where user = :user_id and state=:state";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setParameter("user_id", user_id);
        query.setParameter("state","PENDING");
        List<Project> list = query.getResultList();
        return list;
    }
    @Override
    public void updateState(Long invitation_id){
        Invitation invitation = findById(invitation_id);
        invitation.setState("APPROVE");
        em.persist(invitation);
    }

    @Override
    public Long findIdByUser_id(String user_id){
        String sql = "select invitation from Invitation invitation where user = :user_id";
        TypedQuery<Invitation> query = em.createQuery(sql, Invitation.class);
        query.setParameter("user_id", user_id);
        List<Invitation> list = query.getResultList();
        for (Invitation entity : list) {
            return entity.getId();
        }
        return null;
    }

    @Override
    public List<Invitation> findAll() {
        return null;
    }

    @Override
    public List<Invitation> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Invitation> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Invitation> findAllById(Iterable<Integer> integers) {
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
    public void delete(Invitation entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Invitation> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Invitation> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Invitation> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Invitation> findById(Integer integer) {
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
    public <S extends Invitation> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Invitation> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Invitation> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Invitation getOne(Integer integer) {
        return null;
    }

    @Override
    public Invitation getById(Integer integer) {
        return null;
    }

    @Override
    public Invitation getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Invitation> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Invitation> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Invitation> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Invitation> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Invitation> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Invitation> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Invitation, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
