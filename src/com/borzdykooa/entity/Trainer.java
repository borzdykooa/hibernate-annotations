package com.borzdykooa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*
Класс, соответствующий таблице trainer в базе данных
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "trainees")
@Entity
@Table(name = "trainer", schema = "hibernate_annotations_database")
public class Trainer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "language")
    private String language;

    @Column(name = "experience")
    private Integer experience;

    @OneToMany(mappedBy = "trainer")
    private Set<Trainee> trainees = new HashSet<>();


    public Trainer(String name, String language, Integer experience) {
        this.name = name;
        this.language = language;
        this.experience = experience;
    }
}
