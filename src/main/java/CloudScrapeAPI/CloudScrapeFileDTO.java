package CloudScrapeAPI;

public class CloudScrapeFileDTO {
    private String mimeType;
    private String contents;

    public String getMimeType() {
        return this.mimeType;
    }

    private void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getContents() {
        return this.contents;
    }

    private void setContents(String contents) {
        this.contents = contents;
    }

    public CloudScrapeFileDTO(String mimeType, String contents) {
        this.setMimeType(mimeType);
        this.setContents(contents);
    }
}
