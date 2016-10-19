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

        Spark.post(
                "/upload",
                (request, response) -> {

                    return null
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
    public static void insertUser(Connection conn, String name, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, password);
        stmt.execute();
    }
    public static User selectUser(Connection conn, String name, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
        stmt.setString(1, name);
        ResultSet results = stmt.executeQuery();

        //password auth

        if (results.next()) {
            int id = results.getInt("id");
            return new User(id, name, password);
        }
        return null;
    }
    static int insertCar(Connection conn, Car car) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO cars VALUES (NULL, ?, ?, ?, ?, ?)");
        stmt.setString(1, car.make);
        stmt.setString(2, car.model);
        stmt.setInt(3, car.year);
        stmt.setString(4, car.color);
        stmt.execute();
        ResultSet results = stmt.getGeneratedKeys();
        if (results.next()) {
            return results.getInt(1);
        }
        return 0;
    }
    static ArrayList<Car> selectCars(Connection conn) throws SQLException {
        ArrayList<Car> images = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cars");
        ResultSet results = stmt.executeQuery();
        while (results.next()) {
            int id = results.getInt("images.id");
            String filename = results.getString("images.filename");
            String author = results.getString("users.name");
            Car img = new Car(id, filename, author);
            images.add(img);
        }
        return images;
    }
}