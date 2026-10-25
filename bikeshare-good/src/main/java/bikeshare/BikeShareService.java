package bikeshare;

import java.util.HashMap;
import java.util.Map;

public class BikeShareService {
    private final Map<String, Station> stations = new HashMap<>();
    private final RideRepository rideRepository;
    private String activeRiderId;
    private MemberType activeMemberType;
    private String activeBikeId;

    public BikeShareService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
        stations.put("Library", new Station("Library", 2));
        stations.put("Canteen", new Station("Canteen", 2));
        stations.put("Dorm", new Station("Dorm", 2));
    }

    public void rent(String memberId, MemberType type, String stationName) {
        Station station = stations.get(stationName);
        if (station == null) {
            System.out.println("Invalid station.");
            return;
        }
        if (station.rentBike()) {
            this.activeRiderId = memberId;
            this.activeMemberType = type;
            this.activeBikeId = stationName + "-" + (station.getAvailableBikes() + 1);
            System.out.println("Rented bike " + activeBikeId + " from " + stationName);
        } else {
            System.out.println("No bikes available at " + stationName);
        }
    }

    public void returnBike(String stationName, int minutes) {
        Station station = stations.get(stationName);
        if (station == null) {
            System.out.println("Invalid station.");
            return;
        }
        station.returnBike();
        double fee = minutes * activeMemberType.getRatePerMinute();

        System.out.println("--- RECEIPT ---");
        System.out.println("Member: " + activeRiderId);
        System.out.println("Bike: " + activeBikeId);
        System.out.println("Minutes: " + minutes);
        System.out.println("Fee: " + fee + " baht");
        System.out.println("----------------");

        rideRepository.saveRide(activeRiderId, activeBikeId, minutes, fee);
    }
}