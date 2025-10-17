public class TripProfile {
    public enum Destination { BEACH, MOUNTAINS, CITY, ABROAD }
    public enum Duration { SHORT, MEDIUM, LONG }

    private Destination destination;
    private Duration duration;
    private boolean hiking, photography, business;

    public TripProfile(Destination destination, Duration duration,
                       boolean hiking, boolean photography, boolean business) {
        this.destination = destination;
        this.duration = duration;
        this.hiking = hiking;
        this.photography = photography;
        this.business = business;
    }

    public Destination getDestination() { return destination; }
    public Duration getDuration() { return duration; }
    public boolean isHiking() { return hiking; }
    public boolean isPhotography() { return photography; }
    public boolean isBusiness() { return business; }
}