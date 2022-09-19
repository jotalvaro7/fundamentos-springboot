package com.fundamentosplatzi.springboot.fundamentos.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;


    @Column(name = "description", length = 255)
    private String description;


    @ManyToOne
    private User user;

}
