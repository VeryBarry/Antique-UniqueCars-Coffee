package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import org.h2.tools.Server;
import spark.Session;
import spark.Spark;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {
        Server.createWebServer().start();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        createTables(conn);
        Spark.externalStaticFileLocation("public");
        Spark.init();

        Spark.post(
                "/login",
                (request, response) -> {
                    String body = request.body();
                    JsonParser parser = new JsonParser();
                    User user = parser.parse(body, User.class);
                    User uc = selectUser(conn, user.username);
                    if (uc == null) {
                        insertUser(conn, user.username, user.password);
                    }
                    else if (!user.password.equals(uc.password)) {
                        Spark.halt(403);
                        return "";
                    }
                    Session session = request.session();
                    session.attribute("email", user.username);
                    return "Logged In";
                }
        );

        Spark.get(
                "/user",
                (request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("username");
                    if (name != null) {
                        User user = selectUser(conn, name);
                        JsonSerializer serializer = new JsonSerializer();
                        return serializer.serialize(user);
                    }
                    return "";
                }
        );

        Spark.post(
                "/logout",
                (request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "Logged Out";
                }
        );

        Spark.post(
                "/add-car",
                (request, response) -> {
                    Session session = request.session();
                    String email = session.attribute("email");
                    User user = selectUser(conn, email);
                    String body = request.body();
                    JsonParser parser = new JsonParser();
                    Car car = parser.parse(body, Car.class);
                    insertCar(conn, car, user.id);
                    return "Car Added";
                }
        );
        Spark.get(
                "/car",
                (request, response) -> {
                    JsonSerializer serializer = new JsonSerializer();
                    return serializer.serialize(selectCars(conn));
                }
        );
        Spark.post(
                "/delete",
                (request, response) -> {
                    int id = Integer.valueOf((request.queryParams("id")));
                    deleteCar(conn, id);
                    response.redirect("/");
                    return "Car Deleted";
                }
        );
    }
    public static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS users (id IDENTITY, username VARCHAR, password VARCHAR)");
        stmt.execute("CREATE TABLE IF NOT EXISTS cars (id IDENTITY, make VARCHAR, model VARCHAR, year INT, user_id INT)");
    }
    public static void insertUser(Connection conn, String username, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?)");
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.execute();
    }
    public static User selectUser(Connection conn, String username) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
        stmt.setString(1, username);
        ResultSet results = stmt.executeQuery();
        if (results.next()) {
            int id = results.getInt("id");
            String password = results.getString("password");
            return new User(id, username, password);
        }
        return null;
    }
    public static void insertCar(Connection conn, Car car, int userId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO cars VALUES (NULL, ?, ?, ?, ?)");
        stmt.setString(1, car.make);
        stmt.setString(2, car.model);
        stmt.setInt(3, car.year);
        stmt.setInt(5, userId);
        stmt.execute();
    }
    public static ArrayList<Car> selectCars(Connection conn) throws SQLException {
        ArrayList<Car> cars = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cars INNER JOIN users ON cars.user_id = users.id");
        ResultSet results = stmt.executeQuery();
        while (results.next()) {
            int id = results.getInt("cars.id");
            String make = results.getString("cars.make");
            String model = results.getString("cars.model");
            int year = results.getInt("cars.year");
            Car c = new Car(id, make, model, year);
            cars.add(c);
        }
        return cars;
    }
    public static void editCar(Connection conn, Car car, int userId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE cars SET make = ?, model = ?, year = ? WHERE id = ?");
        stmt.setString(1, car.make);
        stmt.setString(2, car.model);
        stmt.setInt(3, car.year);
        stmt.setInt(5, userId);
        stmt.execute();
    }
    public static void deleteCar(Connection conn, int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM cars WHERE user_Id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }
}