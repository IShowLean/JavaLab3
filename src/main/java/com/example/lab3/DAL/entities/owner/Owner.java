package com.example.lab3.DAL.entities.owner;

import com.example.lab3.DAL.entities.cat.Cat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Table(name="owner")
public class Owner {
    @Id
    @Column(name = "owner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ownerID;
    private String name;
    private LocalDate birthday;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Cat> cats = new ArrayList<Cat>();

    public Owner(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Owner(Integer ownerID, String name, LocalDate birthday) {
        this.ownerID = ownerID;
        this.name = name;
        this.birthday = birthday;
    }

    public Owner() {

    }
}
