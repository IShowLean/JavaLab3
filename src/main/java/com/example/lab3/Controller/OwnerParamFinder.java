package com.example.lab3.Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class OwnerParamFinder {
    String name;
    LocalDate birthday;
}
