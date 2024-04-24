package com.example.lab3.DAL.entities.cat;

import com.example.lab3.DAL.entities.owner.Owner;
import com.example.lab3.DAL.models.Color;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Table(name="cat")
public class Cat {
    @Id
    @Column(name = "cat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer catID;
    private String name;
    private LocalDate birthday;
    private String type;
    @Enumerated(EnumType.STRING)
    private Color color;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @ManyToMany(targetEntity = Cat.class, fetch = FetchType.EAGER)
    @JoinTable(name = "friend", joinColumns = @JoinColumn(name = "cat_id1"), inverseJoinColumns = @JoinColumn(name = "cat_id2"))
    private List<Cat> friends = new ArrayList<Cat>();

    public Cat(Integer catID, String name, LocalDate birthday, String type, Color color) {
        this.catID = catID;
        this.name = name;
        this.birthday = birthday;
        this.type = type;
        this.color = color;
    }

    public Cat(String name, LocalDate birthday, String type, Color color) {
        this.name = name;
        this.birthday = birthday;
        this.type = type;
        this.color = color;
    }

    public Cat() {
    }
}
