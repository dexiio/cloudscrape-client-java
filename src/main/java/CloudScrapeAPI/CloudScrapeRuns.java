package CloudScrapeAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CloudScrapeRuns {
    private CloudScrapeClient cloudScrapeClient;
    private Gson gson = new GsonBuilder().create();

    public CloudScrapeRuns(CloudScrapeClient cloudScrapeClient) {
        this.cloudScrapeClient = cloudScrapeClient;
    }

    public CloudScrapeRunDTO get(String runId) throws Exception {
        String response = this.cloudScrapeClient.requestJson("runs/" + runId);
        return this.gson.fromJson(response, CloudScrapeRunDTO.class);
    }

    public boolean remove(String runId) throws Exception {
        return this.cloudScrapeClient.requestBoolean("runs/" + runId, "DELETE");
    }

    public CloudScrapeExecutionDTO execute(String runId) throws Exception {
        String response = this.cloudScrapeClient.requestJson("runs/" + runId + "/execute", "POST");
        return this.gson.fromJson(response, CloudScrapeExecutionDTO.class);
    }

    public CloudScrapeResultDTO executeSync(String runId) throws Exception {
        String response = this.cloudScrapeClient.requestJson("runs/" + runId + "/execute/wait", "POST");
        return this.gson.fromJson(response, CloudScrapeResultDTO.class);
    }

    public CloudScrapeExecutionDTO executeWithInput(String runId, String inputs) throws Exception {
        String response = this.cloudScrapeClient.requestJson("runs/" + runId + "/execute/inputs", "POST", inputs);
        return this.gson.fromJson(response, CloudScrapeExecutionDTO.class);
    }

    public CloudScrapeExecutionDTO executeWithInputSync(String runId, String inputs) throws Exception {
        String response = this.cloudScrapeClient.requestJson("runs/" + runId + "/execute/inputs/wait", "POST", inputs);
        return this.gson.fromJson(response, CloudScrapeExecutionDTO.class);
    }

    public CloudScrapeResultDTO getLatestResult(String runId) throws Exception {
        String response = this.cloudScrapeClient.requestJson("runs/" + runId + "/latest/result");
        return this.gson.fromJson(response, CloudScrapeResultDTO.class);
    }

    public CloudScrapeExecutionListDTO getExecutions(String runId) throws Exception {
        return this.getExecutions(runId, 0, 30);
    }

    public CloudScrapeExecutionListDTO getExecutions(String runId, int offset) throws Exception {
        return this.getExecutions(runId, offset, 30);
    }

    public CloudScrapeExecutionListDTO getExecutions(String runId, int offset, int limit) throws Exception
    {
        String response = this.cloudScrapeClient.requestJson("runs/" + runId + "/executions?offset=" + offset + "&limit=" + limit);
        return this.gson.fromJson(response, CloudScrapeExecutionListDTO.class);
    }
}
