package bikeshare;

import java.io.FileWriter;
import java.io.IOException;

public class CsvRideRepository implements RideRepository {
    @Override
    public void saveRide(String memberId, String bikeId, int minutes, double fee) {
        try (FileWriter writer = new FileWriter("rides.csv", true)) {
            writer.write(memberId + "," + bikeId + "," + minutes + "," + fee + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}