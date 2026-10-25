package bikeshare;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RideRepository repository = new CsvRideRepository();
        BikeShareService service = new BikeShareService(repository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("BikeShare Good Version Started.");
        System.out.println("Commands: rent <memberId> <STUDENT/STAFF> <Library/Canteen/Dorm>, return <Library/Canteen/Dorm> <minutes>, quit");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("quit")) {
                break;
            }

            String[] parts = line.split("\\s+");
            if (parts.length > 0) {
                if (parts[0].equalsIgnoreCase("rent") && parts.length == 4) {
                    try {
                        MemberType type = MemberType.valueOf(parts[2].toUpperCase());
                        service.rent(parts[1], type, parts[3]);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid member type. Use STUDENT or STAFF.");
                    }
                } else if (parts[0].equalsIgnoreCase("return") && parts.length == 3) {
                    int minutes = Integer.parseInt(parts[2]);
                    service.returnBike(parts[1], minutes);
                }
            }
        }
        scanner.close();
    }
}