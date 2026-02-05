package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClothingItemDAO {

    public void printAllItems() {
        String sql = "SELECT * FROM clothing_item";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            System.out.println("\n--- ITEMS FROM DATABASE ---");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("item_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("size") + " | " +
                                rs.getDouble("price") + " | " +
                                rs.getString("brand") + " | " +
                                rs.getString("type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔥 ВАЖНО: сигнатура ПОД MenuManager
    public boolean updateItem(
            int id,
            String name,
            String size,
            double price,
            String brand,
            String type
    ) {
        String sql = """
                UPDATE clothing_item
                SET name = ?, size = ?, price = ?, brand = ?, type = ?
                WHERE item_id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ps.setString(2, size);
            ps.setDouble(3, price);
            ps.setString(4, brand);
            ps.setString(5, type);
            ps.setInt(6, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteItem(int id) {
        String sql = "DELETE FROM clothing_item WHERE item_id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void searchByName(String name) {
        String sql = "SELECT * FROM clothing_item WHERE name ILIKE ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("item_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByPriceRange(double min, double max) {
        String sql = "SELECT * FROM clothing_item WHERE price BETWEEN ? AND ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("item_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
