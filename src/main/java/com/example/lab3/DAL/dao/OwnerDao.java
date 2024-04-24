package com.example.lab3.DAL.dao;

import com.example.lab3.DAL.entities.cat.Cat;
import com.example.lab3.DAL.entities.owner.Owner;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OwnerDao extends JpaRepository<Owner, Integer> {
    List<Owner> findAll();
    List<Owner> findAll(Sort sort);
    List<Owner> save(Iterable<? extends Owner> entities);
    void flush();
    Owner saveAndFlush(Owner entity);
    void deleteInBatch(Iterable<Owner> entities);
    List<Owner> findByName(String name);
    List<Owner> findByBirthday(LocalDate birthday);
}

