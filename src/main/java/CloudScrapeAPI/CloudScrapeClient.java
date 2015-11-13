package CloudScrapeAPI;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CloudScrapeClient {
    private String apiKey;
    private String accountId;

    private String endPoint = "https://app.cloudscrape.com/api/";
    private String userAgent = "CS-JAVA-CLIENT/1.0";
    private int requestTimeout = 3600;

    private CloudScrapeRuns runs;
    private CloudScrapeExecutions executions;

    public CloudScrapeClient(String apiKey, String accountId) {
        this.apiKey = apiKey;
        this.accountId = accountId;

        this.executions = new CloudScrapeExecutions(this);
        this.runs = new CloudScrapeRuns(this);
    }

    public CloudScrapeResponse request(String url) throws Exception {
        return this.request(url, "GET", null);
    }

    public CloudScrapeResponse request(String url, String method) throws Exception {
        return this.request(url, method, null);
    }

    public CloudScrapeResponse request(String url, String method, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpUriRequest httpRequest;

        if (method.equalsIgnoreCase("POST")) {
            httpRequest = new HttpPost(this.endPoint + url);
        } else {
            httpRequest = new HttpGet(this.endPoint + url);
        }

        httpRequest.addHeader("X-CloudScrape-Access", DigestUtils.md5Hex(this.accountId + this.apiKey).toLowerCase());
        httpRequest.addHeader("X-CloudScrape-Account", this.accountId);
        httpRequest.addHeader("User-Agent", this.userAgent);
        httpRequest.addHeader("Accept", "application/json");
        httpRequest.addHeader("Content-type", "application/json");

        String response;
        CloseableHttpResponse httpResponse = null;
        CloudScrapeResponse cloudScrapeResponse = null;

        try
        {
            httpResponse = httpClient.execute(httpRequest);
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity);

            cloudScrapeResponse = new CloudScrapeResponse();
            cloudScrapeResponse.setStatusCode(httpResponse.getStatusLine().getStatusCode());
            cloudScrapeResponse.setContentType(httpResponse.getFirstHeader("Content-Type").getValue());
            cloudScrapeResponse.setHeaders(httpResponse.getAllHeaders());
            cloudScrapeResponse.setStatusDescription(httpResponse.getStatusLine().getReasonPhrase());
            cloudScrapeResponse.setContent(response);
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpResponse != null)
            {
                httpResponse.close();
            }
        }

        return cloudScrapeResponse;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getRequestTimeout() {
        return this.requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public CloudScrapeExecutions getExecutions() {
        return this.executions;
    }

    public CloudScrapeRuns getRuns() {
        return this.runs;
    }

    public String requestJson(String url) throws Exception {
        return this.requestJson(url, "GET", null);
    }

    public String requestJson(String url, String method) throws Exception {
        return this.requestJson(url, method, null);
    }

    public String requestJson(String url, String method, String body) throws Exception {
        CloudScrapeResponse cloudScrapeResponse = this.request(url, method, body);
        return cloudScrapeResponse.getContent();
    }

    public boolean requestBoolean(String url) throws Exception {
        return this.requestBoolean(url, "GET", null);
    }

    public boolean requestBoolean(String url, String method) throws Exception {
        return this.requestBoolean(url, method, null);
    }

    public boolean requestBoolean(String url, String method, String body) throws Exception {
        this.request(url, method, body);
        return true;
    }
}
