public class GPTCONN {
    private String url = "https://api.openai.com/v1/chat/completions";
    private String apiKey = "YOUR API KEY HERE";
    private String model = "gpt-3.5-turbo";

    public GPTCONN(String url, String apiKey, String model) {
        this.url = url;
        this.apiKey = apiKey;
        this.model = model;
    }

    public String getUrl() {
        return url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getModel() {
        return model;
    }
}
