package com.company;

import jodd.json.JsonSerializer;
import spark.Session;
import spark.Spark;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        createTables(conn);

        Spark.externalStaticFileLocation("public");
        Spark.init();

        Spark.post(
                "/login",
                (request, response) -> {
                    String name = request.queryParams("username");
                    User user = selectUser(conn, name);
                    if (user == null) {
                        insertUser(conn, name);
                    }
                    Session session = request.session();
                    session.attribute("username", name);
                    response.redirect("/");
                    return null;
                }
        );

        Spark.get(
                "/user",
                (request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("username");
                    if (name == null) {
                        return "";
                    }
                    User user = selectUser(conn, name);
                    JsonSerializer serializer = new JsonSerializer();
                    return serializer.serialize(user);
                }
        );

        Spark.post(
                "/logout",
                (request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return null;
                }
        );

        Spark.get(
                "/images",
                (request, response) -> {

                    return null;
                }
        );
    }
    public static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS users (id IDENTITY, username VARCHAR, password VARCHAR)");
        stmt.execute("CREATE TABLE IF NOT EXISTS cars (id IDENTITY, make VARCHAR, model VARCHAR, year INT, color VARCHAR, user_id INT)");
    }
    public static void insertUser(Connection conn, String username, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?)");
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.execute();
    }
    public static User selectUser(Connection conn, String username, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
        stmt.setString(1, username);
        ResultSet results = stmt.executeQuery();
        if (results.next()) {
            int id = results.getInt("id");
            return new User(id, username, password);
        }
        return null;
    }
    static int insertCar(Connection conn, Car car, int userId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO cars VALUES (NULL, ?, ?, ?, ?, ?)");
        stmt.setString(1, car.make);
        stmt.setString(2, car.model);
        stmt.setInt(3, car.year);
        stmt.setString(4, car.color);
        stmt.setInt(5, userId);
        stmt.execute();
    }
    static ArrayList<Car> selectCars(Connection conn) throws SQLException {
        ArrayList<Car> cars = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cars INNER JOIN users ON cars.user_id = users.id");
        ResultSet results = stmt.executeQuery();
        while (results.next()) {
            int id = results.getInt("cars.id");
            String make = results.getString("cars.make");
            String model = results.getString("cars.model");
            int year = results.getInt("cars.year");
            String color = results.getString("cars.color");
            Car c = new Car(id, make, model, year, color);
            cars.add(c);
        }
        return cars;
    }
}