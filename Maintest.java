//Name: Yumna Khan ID: YK220040

import java.io.*;
import java.util.*;

public class Maintest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file containing driver routes: ");
        String driverFileName = scanner.nextLine();
        System.out.println("Enter the file containing search and sort commands: ");
        String commandFileName = scanner.nextLine();
        scanner.close();

        MyLinkedList<Driver> driverList = new MyLinkedList<>();
        readDriverRoutes(driverFileName, driverList);

        // Read the command file and execute commands
        try {
            BufferedReader commandReader = new BufferedReader(new FileReader(commandFileName));
            String line;

            while ((line = commandReader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length > 0) {
                    String command = parts[0];

                    if (command.equals("sort")) {
                        if (parts.length >= 3) {
                            String criteria = parts[1];
                            String order = parts[2];
                            if (criteria.equals("area") && (order.equals("asc") || order.equals("des"))) {
                                driverList.sort();
                            } else if (criteria.equals("driver")) {
                                driverList.sort();
                            }
                        }
                    } else if (command.equals("search")) {
                        if (parts.length >= 2) {
                            String searchValue = parts[1];
                            if (isNumeric(searchValue)) {
                                double area = Double.parseDouble(searchValue);
                                MyLinkedList<Driver> matchedDrivers = driverList.searchArea(area);
                                displayDrivers(matchedDrivers);
                            } else {
                                MyLinkedList<Driver> matchedDrivers = driverList.searchName(searchValue);
                                displayDrivers(matchedDrivers);
                            }
                        }
                    }
                }
            }
            commandReader.close();
        } catch (IOException e) {
            System.err.println("Error reading command file");
        }
    }

    public static void readDriverRoutes(String fileName, MyLinkedList<Driver> driverList) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length > 0) {
                    String driverName = parts[0];
                    MyLinkedList<Driver> coordinates = new MyLinkedList<>();
                    for (int i = 1; i < parts.length; i++) {
                        // Parse and validate coordinates (you can implement this logic)
                        String[] coord = parts[i].split(",");
                        if (coord.length == 2 && isNumeric(coord[0]) && isNumeric(coord[1])) {
                            double x = Double.parseDouble(coord[0]);
                            double y = Double.parseDouble(coord[1]);
                            coordinates.add(new Node<>(new Driver(driverName, x, y)));
                        }
                    }
                    if (coordinates.size() > 2) {
                        driverList.add(new Node<>(new Driver(driverName, coordinates)));
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading driver route file");
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static void displayDrivers(MyLinkedList<Driver> drivers) {
        if (drivers.size() > 0) {
            Node<Driver> current = drivers.getHead();
            while (current != null) {
                System.out.println(current.getPayload());
                current = current.getNext();
            }
        } else {
            System.out.println("Not found");
        }
        System.out.println();
    }
}

