package BusinessLogic.data;

public class GPTINFO {
    private String URL = "https://api.openai.com/v1/chat/completions";
    private String apiKey = "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";
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
