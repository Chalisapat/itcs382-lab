package bikeshare;

public interface RideRepository {
    void saveRide(String memberId, String bikeId, int minutes, double fee);
}