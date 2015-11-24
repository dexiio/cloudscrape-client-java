package CloudScrapeAPI;

public class CloudScrapeExecutionListDTO {
    private int offset;
    private int totalRows;
    private CloudScrapeExecutionDTO[] rows;

    public int getOffset() {
        return offset;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public CloudScrapeExecutionDTO[] getRows() {
        return rows;
    }
}
