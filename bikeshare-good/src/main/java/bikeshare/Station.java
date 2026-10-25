package bikeshare;

public class Station {
    private final String name;
    private int availableBikes;

    public Station(String name, int initialBikes) {
        this.name = name;
        this.availableBikes = initialBikes;
    }

    public String getName() {
        return name;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public boolean rentBike() {
        if (availableBikes > 0) {
            availableBikes--;
            return true;
        }
        return false;
    }

    public void returnBike() {
        availableBikes++;
    }
}