package ru.danila.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Animal entity class
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnimalEntity {
    int id;
    String name;
    String description;

    public AnimalEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
