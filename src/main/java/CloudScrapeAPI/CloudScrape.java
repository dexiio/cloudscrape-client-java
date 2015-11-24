package CloudScrapeAPI;

public class CloudScrape {
    private CloudScrapeClient cloudScrapeClient;

    public void init(String apiKey, String accountId) {
        this.cloudScrapeClient = new CloudScrapeClient(apiKey, accountId);
    }

    public CloudScrapeClient defaultClient() throws Exception {
        this.checkState();
        return this.cloudScrapeClient;
    }

    public CloudScrapeExecutions executions() throws Exception {
        this.checkState();
        return this.cloudScrapeClient.getExecutions();
    }

    public CloudScrapeRuns runs() throws Exception {
        this.checkState();
        return this.cloudScrapeClient.getRuns();
    }

    private void checkState() throws Exception {
        if (this.cloudScrapeClient == null) {
            throw new Exception("You must first call init before using the API");
        }
    }
}
