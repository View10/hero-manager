package kevin.hero_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class HeroDAO {
    private static final String url = "jdbc:mysql://localhost:3306/heroes";
    private static final String user = "root";
    private static final String pass = "root";

    public static Hero selectedHero = null;

    public static void add(Hero h){
        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            String query = """
                    INSERT INTO heroes (hClass, name, health, ability)
                    VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, h.gethClass().toString());
            ps.setString(2, h.getName());
            ps.setInt(3, h.getHp());
            ps.setString(4, h.getSa().toString());

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void update(Hero h){
        String query = """
        UPDATE heroes
        SET hClass = ?, name = ?, health = ?, ability = ?
        WHERE hid = ?
        """;

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, h.gethClass().toString());
            ps.setString(2, h.getName());
            ps.setInt(3, h.getHp());
            ps.setString(4, h.getSa().toString());
            ps.setInt(5, h.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Hero h){
        String query = "DELETE FROM heroes WHERE hid = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, h.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Hero> getData(){
        ArrayList<Hero> heroes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            String query = """
                    SELECT h.hid, h.hClass, h.name, h.health, h.ability
                    FROM heroes h
                    """;

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("hid");
                String hClass = rs.getString("hClass");
                String name = rs.getString("name");
                int health = rs.getInt("health");
                String sa = rs.getString("ability");

                Hero h = new Hero(id, name, HeroClass.valueOf(hClass), health, SpecialAbility.valueOf(sa));

                heroes.add(h);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return FXCollections.observableArrayList(heroes);
    }
}
