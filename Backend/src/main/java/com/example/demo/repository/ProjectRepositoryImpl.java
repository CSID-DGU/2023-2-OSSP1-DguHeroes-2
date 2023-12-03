package com.example.demo.repository;

import com.example.demo.domain.Project;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.function.Function;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private EntityManager em;
    ProjectRepository project_rp;
    public ProjectRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Project insert(Project project) {
        em.persist(project);
        return project;
    }

    @Override
    public List<Project> findPopularProject() {
        List<Project> allProjects = project_rp.findAll();
        Map<Integer, Integer> popular_map = new HashMap<Integer, Integer>();

        for (Project project : allProjects) {
            popular_map.put(project.getId(), (project.getLike_count() * 5 + project.getVisited_number()));
        }

        List<Integer> keySet = new ArrayList<>(popular_map.keySet());
        keySet.sort((o1, o2) -> popular_map.get(o2).compareTo(popular_map.get(o1)));

        List<Project> popular_project_list = new ArrayList<>();
        int num = 0;
        for (Integer project_id : keySet) {
            popular_project_list.add(project_rp.findByProjectId(project_id));
        }

        return popular_project_list;


    }


    @Override
    public Project findByProjectId(int project_id) {
        String sql = "select project from Project project where id = :project_id";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setParameter("project_id", project_id);
        List<Project> list = query.getResultList();
        for (Project entity : list) {
            return entity;
        }
        return null;
    }

    @Override
    public int delete(int project_id){
        Project project = findByProjectId(project_id);
        em.remove(project);
        Project removed_project = findByProjectId(project_id);
        if (removed_project == null) return 1;
        else return 0;
    }

    @Override
    public int getLike_count(int project_id) {
        return 0;
    }


    public List<Project> findAllProjectList() {
        String sql = "select project from Project project";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        List<Project> list = query.getResultList();
        return list;
    }


    @Override
    public int getVisited_number(int project_id) {
        return 0;
    }

    @Override
    public List<Project> findEndProjects(String user_id){
        String sql = "select project from Project project where project = :user_id and project.is_available = :is_available";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setParameter("user_id", user_id);
        query.setParameter("is_available", "EXPIRED");
        List<Project> list = query.getResultList();
        return list;
    }

    @Override
    public List<ProjectStack> findProjectStackByProjectId(int project_id){
        String sql = "select project_stacks from Project ps where ps.id =: project_id";
        TypedQuery<ProjectStack> query = em.createQuery(sql, ProjectStack.class);
        query.setParameter("project_id", project_id);
        List<ProjectStack> list = query.getResultList();
        return list;
    }

    @Override
    public List<Project> findAll() {
        return findAll();
    }

    @Override
    public List<Project> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Project> findAllById(Iterable<Integer> integers) {
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
    public void delete(Project entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Project> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Project> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Project> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Project> findById(Integer integer) {
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
    public <S extends Project> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Project> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Project> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Project getOne(Integer integer) {
        return null;
    }

    @Override
    public Project getById(Integer integer) {
        return null;
    }

    @Override
    public Project getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Project> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Project> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Project> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Project> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Project> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Project> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Project, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
