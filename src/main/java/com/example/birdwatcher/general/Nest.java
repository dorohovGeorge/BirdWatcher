package com.example.birdwatcher.general;

import javax.persistence.*;
import java.util.List;

@Entity
public class Nest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nest_gen")
    @SequenceGenerator(name = "nest_gen", sequenceName = "nest_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_nest")
    private List<Bird> birds;

    public Nest(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Nest() {

    }

    public Nest(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Bird> getBirds() {
        return birds;
    }

    public void setBirds(List<Bird> birds) {
        this.birds = birds;
    }

    public void addBird(Bird bird) {
        birds.add(bird);
    }

    public void removeBird(Bird bird) {
        birds.remove(bird);
    }

    public boolean isExistBird(Bird bird) {
        for (Bird tempBird : birds) {
            if (tempBird.getId().equals(bird.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Nest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
