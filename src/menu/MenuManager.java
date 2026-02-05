package menu;

import database.ClothingItemDAO;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final ClothingItemDAO dao = new ClothingItemDAO();

    @Override
    public void displayMenu() {
        System.out.println("\n=== CLOTHING STORE SYSTEM ===");
        System.out.println("1. View All Items");
        System.out.println("2. Update Item");
        System.out.println("3. Delete Item");
        System.out.println("4. Search by Name");
        System.out.println("5. Search by Price Range");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> dao.printAllItems();
                case 2 -> updateItem();
                case 3 -> deleteItem();
                case 4 -> searchByName();
                case 5 -> searchByPrice();
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
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
}
