package com.example.lab3.DAL.entities.cat;

import com.example.lab3.DAL.models.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CatDto {
    private Integer catID;
    private String name;
    private LocalDate birthday;
    private String type;
    private Color color;
    private Integer owner;
    private List<Integer> friends;
}
