public class GPTINFO {
    private String URL = "https://api.openai.com/v1/chat/completions";
    private String apiKey = null;
    private String model = "gpt-3.5-turbo";

    public String getURL() {
        return URL;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getModel() {
        return model;
    }
}
