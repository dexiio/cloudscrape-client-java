import CloudScrapeAPI.*;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestRunner {
    private Configuration configuration;
    private CloudScrape cloudScrape;
    private CloudScrapeRuns cloudScrapeRuns;

    private void init() throws Exception {
        configuration = new PropertiesConfiguration("cloudscrapeapi.properties");
        cloudScrape = new CloudScrape();
        cloudScrape.init(configuration.getString("api.key"), configuration.getString("api.accountId"));
        cloudScrapeRuns = cloudScrape.runs();
    }

    @Test
    public void responseRunIdShouldMatchRequest() throws Exception {
        init();
        CloudScrapeRunDTO cloudScrapeRunDTO = cloudScrapeRuns.get(configuration.getString("api.runId"));
        assertEquals(configuration.getString("api.runId"), cloudScrapeRunDTO.getId());
    }

    @Test
    public void shouldHaveSomeExecutions() throws Exception {
        init();
        CloudScrapeExecutionListDTO cloudScrapeResults = cloudScrapeRuns.getExecutions(configuration.getString("api.runId"));
        assertNotEquals(0, cloudScrapeResults.getRows().length);
    }

    @Test
    public void executionsShouldBeValid() throws Exception {
        init();
        CloudScrapeExecutionListDTO cloudScrapeResults = cloudScrapeRuns.getExecutions(configuration.getString("api.runId"));
        CloudScrapeExecutionDTO cloudScrapeExecutionDTO = cloudScrapeResults.getRows()[0];
        assertNotEquals("", cloudScrapeExecutionDTO.getId());
    }

    @Test
    public void executionInOKStatus() throws Exception {
        init();
        CloudScrapeExecutionListDTO cloudScrapeResults = cloudScrapeRuns.getExecutions(configuration.getString("api.runId"));
        CloudScrapeExecutionDTO cloudScrapeExecutionDTO = cloudScrapeResults.getRows()[0];
        assertEquals(CloudScrapeStates.OK, cloudScrapeExecutionDTO.getState());
    }

    @Test
    public void executionHasRows() throws Exception {
        init();
        CloudScrapeExecutionListDTO cloudScrapeResults = cloudScrapeRuns.getExecutions(configuration.getString("api.runId"));
        CloudScrapeExecutionDTO cloudScrapeExecutionDTO = cloudScrapeResults.getRows()[0];
        CloudScrapeExecutions cloudScrapeExecutions = cloudScrape.executions();
        CloudScrapeResultDTO cloudScrapeResultDTO = cloudScrapeExecutions.getResult(cloudScrapeExecutionDTO.getId());
        assertNotEquals(0, cloudScrapeResultDTO.getTotalRows());
    }
}
