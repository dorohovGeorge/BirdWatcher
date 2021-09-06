package com.example.birdwatcher.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Bird {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bird_gen")
    @SequenceGenerator(name = "bird_gen", sequenceName = "bird_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String color;

    private boolean isFlyingBird;


    public Bird(Long id, String name, String color, boolean isFlyingBird) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.isFlyingBird = isFlyingBird;
    }

    public Bird(String name, String color, boolean isFlyingBird) {
        this.name = name;
        this.color = color;
        this.isFlyingBird = isFlyingBird;
    }

    public Bird() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getIsFlyingBird() {
        return isFlyingBird;
    }

    public void setIsFlyingBird(boolean flyingBird) {
        isFlyingBird = flyingBird;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", isFlyingBird=" + isFlyingBird +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bird bird = (Bird) o;
        return isFlyingBird == bird.isFlyingBird && Objects.equals(name, bird.name) && Objects.equals(color, bird.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, isFlyingBird);
    }
}
