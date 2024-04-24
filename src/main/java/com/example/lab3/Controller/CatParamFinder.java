package com.example.lab3.Controller;

import com.example.lab3.DAL.models.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class CatParamFinder {
    String name;
    LocalDate birthday;
    String type;
    Color color;
}
