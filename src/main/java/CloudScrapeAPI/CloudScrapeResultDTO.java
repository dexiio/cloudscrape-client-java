package CloudScrapeAPI;

public class CloudScrapeResultDTO {
    private String[] headers;
    private String[][] rows;

    public String[] getHeaders() {
        return headers;
    }

    public String[][] getRows() {
        return rows;
    }

    public int getTotalRows() {
        return rows.length;
    }
}
