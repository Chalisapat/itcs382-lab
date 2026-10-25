package bikeshare;

public enum MemberType {
    STUDENT(2.0),
    STAFF(3.0);

    private final double ratePerMinute;

    MemberType(double ratePerMinute) {
        this.ratePerMinute = ratePerMinute;
    }

    public double getRatePerMinute() {
        return ratePerMinute;
    }
}