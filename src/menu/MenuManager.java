package menu;

import database.ClothingItemDAO;
import exception.InvalidInputException;

import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final ClothingItemDAO dao = new ClothingItemDAO();
    private boolean running = true;

    @Override
    public void run() {
        while (running) {
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> dao.printAllItems();
                case 2 -> insertItem();
                case 3 -> updateItem();
                case 4 -> deleteItem();
                case 5 -> searchByName();
                case 6 -> searchByPrice();
                case 7 -> searchByMinPrice();
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("""
                
                1. Show all items
                2. Insert item
                3. Update item
                4. Delete item
                5. Search by name
                6. Search by price range
                7. Search by min price
                0. Exit
                """);
        System.out.print("Enter choice: ");
    }

    private void insertItem() {
        try {
            System.out.print("Item ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Size: ");
            String size = scanner.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Brand: ");
            String brand = scanner.nextLine();

            System.out.print("Type (Shirt / Pants): ");
            String type = scanner.nextLine();

            dao.insertItem(id, name, size, price, brand, type);
            System.out.println("Item inserted successfully");

        } catch (Exception e) {
            throw new InvalidInputException("Insert failed");
        }
    }

    private void updateItem() {
        System.out.print("Item ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Size: ");
        String size = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        if (dao.updateItem(id, name, size, price, brand, type)) {
            System.out.println("Item updated successfully");
        } else {
            System.out.println("Item not found");
        }
    }

    private void deleteItem() {
        System.out.print("Item ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (dao.deleteItem(id)) {
            System.out.println("Item deleted");
        } else {
            System.out.println("Item not found");
        }
    }

    private void searchByName() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        dao.searchByName(name);
    }

    private void searchByPrice() {
        System.out.print("Min price: ");
        double min = Double.parseDouble(scanner.nextLine());

        System.out.print("Max price: ");
        double max = Double.parseDouble(scanner.nextLine());

        dao.searchByPriceRange(min, max);
    }

    private void searchByMinPrice() {
        System.out.print("Enter minimum price: ");
        double minPrice = Double.parseDouble(scanner.nextLine());
        dao.searchByMinPrice(minPrice);
    }

}
