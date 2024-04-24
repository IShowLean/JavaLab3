package com.example.lab3.DAL.dao;

import com.example.lab3.DAL.entities.cat.Cat;
import com.example.lab3.DAL.models.Color;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CatDao extends JpaRepository<Cat, Integer> {
    List<Cat> findAll();
    List<Cat> findAll(Sort sort);
    List<Cat> save(Iterable<? extends Cat> entities);
    void flush();
    Cat saveAndFlush(Cat entity);
    void deleteInBatch(Iterable<Cat> entities);
    List<Cat> findByColor(Color color);
    List<Cat> findByName(String name);
    List<Cat> findByType(String type);
    List<Cat> findByBirthday(LocalDate birthday);
}
