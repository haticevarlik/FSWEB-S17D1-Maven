package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/workintech/animal")
public class AnimalController {
    Map<Integer, Animal> animals = new HashMap<>();

    @Value("${course.name}")
    private String projectName;
    @Value("${project.developer.fullname}")
    private String devName;

    @PostConstruct
    public void loadAll(){
        this.animals.put(1, new Animal(1, "kartal"));
        System.out.println("Animals: "+animals);
        System.out.println("course name: "+projectName+"    Developer: "+devName);
    }

    @GetMapping
    public List<Animal> getAnimals(){
        return new ArrayList<>(animals.values());
    }

    @GetMapping("{id}")
    public Animal getAnimal(@PathVariable("id") int id){
        return animals.get(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        System.out.println(animal + " added");
        this.animals.put(animal.getId(),animal);
    }

    @PutMapping("{id}")
    public Animal updateAnimal(@PathVariable("id") int id, @RequestBody Animal animal){
        System.out.println(this.animals.get(id)+" deleted. New animal: "+animal);
        this.animals.replace(id,animal);
        return animal;
    }

    @DeleteMapping("{id}")
    public void deleteAnimal(@PathVariable("id") int id){
        System.out.println("animal deleted: "+this.animals.get(id));
        this.animals.remove(id);

    }

}
