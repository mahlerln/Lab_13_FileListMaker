import java.io.IOException;
import java.util.Scanner;

public class FileListMaker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListManager listManager = new ListManager();
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("A - Add an item to the list");
            System.out.println("D - Delete an item from the list");
            System.out.println("I - Insert an item into the list");
            System.out.println("M - Move an item");
            System.out.println("O - Open a list file from disk");
            System.out.println("S - Save the current list to disk");
            System.out.println("C - Clear the current list");
            System.out.println("V - View the list");
            System.out.println("Q - Quit the program");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "A":
                    listManager.addItem(scanner);
                    break;
                case "D":
                    listManager.deleteItem(scanner);
                    break;
                case "I":
                    listManager.insertItem(scanner);
                    break;
                case "M":
                    listManager.moveItem(scanner);
                    break;
                case "O":
                    listManager.openList(scanner);
                    break;
                case "S":
                    listManager.saveList(scanner);
                    break;
                case "C":
                    listManager.clearList(scanner);
                    break;
                case "V":
                    listManager.viewList();
                    break;
                case "Q":
                    if (listManager.quitProgram(scanner)) {
                        running = false;
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}