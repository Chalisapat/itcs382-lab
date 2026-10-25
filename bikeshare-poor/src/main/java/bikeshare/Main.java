package bikeshare;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    // Public static fields for shared data (poor design pattern)
    public static String[] stations = {"Library", "Canteen", "Dorm"};
    public static int[] stationBikes = {2, 2, 2};
    public static String currentRiderId = "";
    public static int currentRiderType = 0; // 1 = student, 2 = staff
    public static String rentedBikeId = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BikeShare Poor Version Started.");
        System.out.println("Commands: rent <memberId> <type:1=student,2=staff> <stationIndex>, return <stationIndex> <minutes>, quit");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("quit")) {
                break;
            }

            String[] parts = line.split("\\s+");
            if (parts.length > 0) {
                if (parts[0].equalsIgnoreCase("rent") && parts.length == 4) {
                    currentRiderId = parts[1];
                    currentRiderType = Integer.parseInt(parts[2]);
                    int stIndex = Integer.parseInt(parts[3]);
                    handleRide(true, stIndex, 0);
                } else if (parts[0].equalsIgnoreCase("return") && parts.length == 3) {
                    int stIndex = Integer.parseInt(parts[1]);
                    int minutes = Integer.parseInt(parts[2]);
                    handleRide(false, stIndex, minutes);
                }
            }
        }
        scanner.close();
    }

    // A method whose behavior changes based on a boolean flag
    public static void handleRide(boolean isRent, int stationIndex, int minutes) {
        if (stationIndex < 0 || stationIndex >= stations.length) {
            System.out.println("Invalid station.");
            return;
        }

        if (isRent) {
            if (stationBikes[stationIndex] > 0) {
                stationBikes[stationIndex]--;
                rentedBikeId = stations[stationIndex] + "-" + (stationBikes[stationIndex] + 1);
                System.out.println("Rented bike " + rentedBikeId + " from " + stations[stationIndex]);
            } else {
                System.out.println("No bikes available at " + stations[stationIndex]);
            }
        } else {
            stationBikes[stationIndex]++;
            double fee = 0.0;
            if (currentRiderType == 1) {
                fee = minutes * 2.0;
            } else {
                fee = minutes * 3.0;
            }

            // Print receipt
            System.out.println("--- RECEIPT ---");
            System.out.println("Member: " + currentRiderId);
            System.out.println("Bike: " + rentedBikeId);
            System.out.println("Minutes: " + minutes);
            System.out.println("Fee: " + fee + " baht");
            System.out.println("----------------");

            // Save to file
            try (FileWriter writer = new FileWriter("rides.csv", true)) {
                writer.write(currentRiderId + "," + rentedBikeId + "," + minutes + "," + fee + "\n");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
    }
}