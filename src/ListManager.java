import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListManager {
    private List<String> list = new ArrayList<>();
    private String currentFilename = null;
    private boolean needsToBeSaved = false;

    public void addItem(Scanner scanner) {
        System.out.print("Enter an item to add: ");
        String item = scanner.nextLine();
        list.add(item);
        needsToBeSaved = true;
    }

    public void deleteItem(Scanner scanner) {
        viewList();
        System.out.print("Enter the index of the item to delete: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (isValidIndex(index)) {
            list.remove(index);
            needsToBeSaved = true;
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void insertItem(Scanner scanner) {
        System.out.print("Enter an item to insert: ");
        String item = scanner.nextLine();
        System.out.print("Enter the index to insert at: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (isValidIndex(index) || index == list.size()) {
            list.add(index, item);
            needsToBeSaved = true;
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void moveItem(Scanner scanner) {
        viewList();
        System.out.print("Enter the index of the item to move: ");
        int fromIndex = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the new index for the item: ");
        int toIndex = Integer.parseInt(scanner.nextLine());
        if (isValidIndex(fromIndex) && (isValidIndex(toIndex) || toIndex == list.size())) {
            String item = list.remove(fromIndex);
            list.add(toIndex, item);
            needsToBeSaved = true;
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void openList(Scanner scanner) {
        if (needsToBeSaved) {
            System.out.print("You have unsaved changes. Save before opening a new list? (Y/N): ");
            String choice = scanner.nextLine().toUpperCase();
            if (choice.equals("Y")) {
                saveList(scanner);
            }
        }

        System.out.print("Enter the filename to open: ");
        String filename = scanner.nextLine();
        try {
            list = FileOperations.loadFromFile(filename);
            currentFilename = filename;
            needsToBeSaved = false;
            System.out.println("List loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public void saveList(Scanner scanner) {
        if (currentFilename == null) {
            System.out.print("Enter a filename to save the list: ");
            currentFilename = scanner.nextLine();
        }

        try {
            FileOperations.saveToFile(currentFilename, list);
            needsToBeSaved = false;
            System.out.println("List saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void clearList(Scanner scanner) {
        System.out.print("Are you sure you want to clear the list? (Y/N): ");
        String choice = scanner.nextLine().toUpperCase();
        if (choice.equals("Y")) {
            list.clear();
            needsToBeSaved = true;
        }
    }

    public void viewList() {
        if (list.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + ": " + list.get(i));
            }
        }
    }

    public boolean quitProgram(Scanner scanner) {
        if (needsToBeSaved) {
            System.out.print("You have unsaved changes. Save before quitting? (Y/N): ");
            String choice = scanner.nextLine().toUpperCase();
            if (choice.equals("Y")) {
                saveList(scanner);
            }
        }
        return true;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < list.size();
    }
}