package com.example.lab3.DAL.entities.owner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OwnerDto {
    private Integer ownerID;
    private String name;
    private LocalDate birthday;
    private List<Integer> cats;
}
