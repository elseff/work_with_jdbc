package ru.danila;

import lombok.extern.slf4j.Slf4j;
import ru.danila.persistence.dao.AnimalDao;
import ru.danila.persistence.dao.AnimalDaoImpl;

import java.sql.SQLException;

@Slf4j
public class Main {
    public static void main(String[] args) throws SQLException {
        AnimalDao dao = new AnimalDaoImpl();
    }
}
