package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, message = "Name cannnot be empty")
    private String name;

    @ManyToMany
    private List<Cheese> cheeses;

    public Menu() {
    }

    public int getId() {
        return id;
    }

    public Menu setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Menu setName(String name) {
        this.name = name;
        return this;
    }

    public Menu setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
        return this;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }


}
