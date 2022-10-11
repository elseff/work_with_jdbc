package ru.danila.persistence.dao;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ru.danila.DBConnector;
import ru.danila.persistence.AnimalEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Realisation AnimalDao interface
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class AnimalDaoImpl implements AnimalDao {
    static Statement statement;
    static ResultSet resultSet;

    public AnimalDaoImpl() throws SQLException {
        statement = new DBConnector().getStatement();
    }

    @Override
    public AnimalEntity getAnimalById(int id) {
        try {
            resultSet = statement.executeQuery(String.format("""
                        select * from animals where id = %d;
                    """, id));
            while (resultSet.next()) {
                return new AnimalEntity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public AnimalEntity getAnimalByName(String name) {
        if (name.isEmpty()) {
            log.error("name shouldn't be empty");
            return null;
        }
        try {
            resultSet = statement.executeQuery(String.format("""
                        select * from animals where name = '%s';
                    """,name));
            while (resultSet.next()) {
                return new AnimalEntity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public AnimalEntity createAnimal(String name, String description) {
        AnimalEntity animalEntity = null;
        try {
            statement.executeUpdate(String.format("""
                        insert into animals("name","description") values('%s','%s');
                    """, name, description));
            resultSet = statement.executeQuery(String.format("""
                        select * from animals where name = '%s';
                    """, name));
            while (resultSet.next()) {
                animalEntity = new AnimalEntity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return animalEntity;
    }

    @Override
    public AnimalEntity updateAnimal(int id, String name, String description) {
        try {
            statement.executeUpdate(String.format("""
                    update animals set (name,description) = ('%s','%s') where id = %d;
                    """, name, description, id));
            resultSet = statement.executeQuery(String.format("""
                        select * from animals where id = %d;
                    """, id));
            while (resultSet.next()) {
                return new AnimalEntity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public AnimalEntity updateAnimalName(int id, String name) {
        try {
            statement.executeUpdate(String.format("""
                    update animals set name = '%s' where id = %d;
                    """, name, id));
            resultSet = statement.executeQuery(String.format("""
                        select * from animals where id = %d;
                    """, id));
            while (resultSet.next()) {
                return new AnimalEntity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public AnimalEntity updateAnimalDescription(int id, String description) {
        try {
            statement.executeUpdate(String.format("""
                    update animals set description = '%s' where id = %d;
                    """, description, id));
            resultSet = statement.executeQuery(String.format("""
                        select * from animals where id = %d;
                    """, id));
            while (resultSet.next()) {
                return new AnimalEntity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAnimal(int id) {
        try {
            statement.executeUpdate(String.format("""
                    delete from animals where id = %d;
                    """, id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<AnimalEntity> getAllAnimals() {
        return new ArrayList<>() {
            {
                try {
                    resultSet = statement.executeQuery("select * from animals");
                    while (resultSet.next()) {
                        add(new AnimalEntity(resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3)));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
    }

    @Override
    public void deleteAllAnimals() {
        try {
            statement.executeUpdate("""
                    delete from animals where id>0;
                    """);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
