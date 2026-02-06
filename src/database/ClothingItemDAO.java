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

    public void insertItem(
            int itemId,
            String name,
            String size,
            double price,
            String brand,
            String type
    ) {
        String sql = """
                INSERT INTO clothing_item (item_id, name, size, price, brand, type)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, itemId);
            ps.setString(2, name);
            ps.setString(3, size);
            ps.setDouble(4, price);
            ps.setString(5, brand);
            ps.setString(6, type);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed", e);
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

    public void searchByMinPrice(double minPrice) {
        String sql = "SELECT * FROM clothing_item WHERE price >= ? ORDER BY price";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setDouble(1, minPrice);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- ITEMS WITH PRICE >= " + minPrice + " ---");
            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("item_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("size") + " | " +
                                rs.getDouble("price") + " | " +
                                rs.getString("brand") + " | " +
                                rs.getString("type")
                );
            }

            if (!found) {
                System.out.println("No items found");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Search by min price failed", e);
        }
    }

    public void viewPantsOnly() {
        viewByType("Pants");
    }

    public void viewShirtsOnly() {
        viewByType("Shirt");
    }

    private void viewByType(String type) {
        String sql = "SELECT * FROM clothing_item WHERE type = ? ORDER BY item_id";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- " + type.toUpperCase() + " ONLY ---");
            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("item_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("size") + " | " +
                                rs.getDouble("price") + " | " +
                                rs.getString("brand")
                );
            }

            if (!found) {
                System.out.println("No " + type + " found");
            }

        } catch (SQLException e) {
            throw new RuntimeException("View by type failed", e);
        }
    }

}
