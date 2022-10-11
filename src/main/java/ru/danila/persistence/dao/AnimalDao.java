package ru.danila.persistence.dao;

import ru.danila.persistence.AnimalEntity;

import java.util.List;

/**
 * Dao layer
 */
public interface AnimalDao {
    AnimalEntity getAnimalById(int id);

    AnimalEntity getAnimalByName(String name);

    AnimalEntity createAnimal(String name, String description);

    AnimalEntity updateAnimal(int id, String name, String description);

    AnimalEntity updateAnimalName(int id, String name);

    AnimalEntity updateAnimalDescription(int id, String description);

    void deleteAnimal(int id);

    List<AnimalEntity> getAllAnimals();

    void deleteAllAnimals();
}
