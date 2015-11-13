package CloudScrapeAPI;

public class CloudScrapeExecutionDTO {
    private String _id;
    private CloudScrapeStates state;
    private long starts;
    private long finished;

    public String getId() {
        return _id;
    }

    public CloudScrapeStates getState() {
        return state;
    }

    public long getStarts() {
        return starts;
    }

    public long getFinished() {
        return finished;
    }
}
