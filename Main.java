// Name: Yumna Khan, netID: YXK220040

//import libraries
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[]args){
        

        //initialize scanner, get input file
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter the file: ");
        String fileName = scanner.nextLine();
        scanner.close();
        
        //initialize arrays
        String[] pilotNames = new String[20];
        double [][][] coordinates = new double[20][16][2];

        //call methods
        int allPilots = readData(fileName, pilotNames, coordinates);
        double[] allAreas = calculateArea(coordinates, allPilots);
        writeData(pilotNames, allAreas, allPilots);
    }

    //method for reading file data 
    public static int readData(String fileName, String pilotNames[], double[][][] coordinates){
        int allPilots = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            
            //filter through pilots, populate arrays
            while ((line = reader.readLine()) != null && allPilots < 20) {
                String[] parts = line.split(" ");
                pilotNames[allPilots] = parts[0];

                //set coordinates
                for (int i = 1; i < parts.length; i++) {
                    String[] coord = parts[i].split(",");
                    coordinates[allPilots][i-1][0] = Double.parseDouble(coord[0]); 
                    coordinates[allPilots][i-1][1] = Double.parseDouble(coord[1]);
                } allPilots++;
            } 
            } catch(IOException e){
                System.err.println("Error");
        } return allPilots;
    }
    
    //method for calculating the area
    public static double[] calculateArea(double [][][] coordinates, int allPilots){
        double[] allAreas = new double[allPilots];
        for (int pilot = 0; pilot < allPilots; pilot++){
            double [][] pilotCoords = coordinates[pilot];
            double area = 0;
            
            //getting all coordinates to calculate area
            for (int i = 0; i < pilotCoords.length - 1; i++) {
                double x1 = pilotCoords[i][0];
                double x2 = pilotCoords[i][1];
                double y1 = pilotCoords[i + 1][0];
                double y2 = pilotCoords[i + 1][1];
                area += ((x1 * y2) - (x2 * y1)); //calculating area
            } 
            allAreas[pilot] = Math.abs(area)/2.0; //calculating area
        } return allAreas;
    }
    
    //method for writing output file
    public static void writeData(String[] pilotNames, double[] pilotAreas, int allPilots){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("pilot_areas.txt"));
            for (int pilot = 0; pilot < allPilots; pilot++){
                double area = pilotAreas[pilot];
                String output = String.format("%s %.2f", pilotNames[pilot], area); //formatting output line
                writer.write(output);
                writer.newLine();
            }
            writer.close();
            System.out.println("pilot_areas.txt is ready.");
        } catch (IOException e){
            System.err.println("Error writing file");
        }
    }
}