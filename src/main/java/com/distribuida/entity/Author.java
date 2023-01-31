package com.distribuida.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "authors")

public class Author{
    @Setter @Getter
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Setter @Getter
    private String first_name;
    @Setter @Getter
    private String last_name;

}

