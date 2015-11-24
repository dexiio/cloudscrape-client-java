package CloudScrapeAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CloudScrapeExecutions {
    private CloudScrapeClient cloudScrapeClient;
    private Gson gson = new GsonBuilder().create();

    public CloudScrapeExecutions(CloudScrapeClient cloudScrapeClient) {
        this.cloudScrapeClient = cloudScrapeClient;
    }

    public CloudScrapeExecutionDTO get(String executionId) throws Exception {
        String response = this.cloudScrapeClient.requestJson("executions/" + executionId);
        return this.gson.fromJson(response, CloudScrapeExecutionDTO.class);
    }

    public boolean remove(String executionId) throws Exception {
        return this.cloudScrapeClient.requestBoolean("executions/" + executionId, "DELETE");
    }

    public CloudScrapeResultDTO getResult(String executionId) throws Exception {
        String response = this.cloudScrapeClient.requestJson("executions/" + executionId + "/result");
        return this.gson.fromJson(response, CloudScrapeResultDTO.class);
    }

    public CloudScrapeFileDTO getResultFile(String executionId, String fileId) throws Exception
    {
        CloudScrapeResponse cloudScrapeResponse = this.cloudScrapeClient.request("executions/" + executionId + "/file/" + fileId);
        return new CloudScrapeFileDTO(cloudScrapeResponse.getContentType(), cloudScrapeResponse.getContent());
    }

    public boolean stop(String executionId) throws Exception
    {
        return this.cloudScrapeClient.requestBoolean("executions/" + executionId + "/stop", "POST");
    }

    public boolean resume(String executionId) throws Exception
    {
        return this.cloudScrapeClient.requestBoolean("executions/" + executionId + "/continue", "POST");
    }
}
