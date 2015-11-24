package CloudScrapeAPI;

import org.apache.http.Header;

public class CloudScrapeResponse {
    private String content;
    private String contentType;
    private String statusDescription;
    private int statusCode;
    private Header[] headers;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }
}
