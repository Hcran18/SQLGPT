package BusinessLogic.LanguageModel.languageModelData;

public class GPTCONN {
    private String url;
    private String apiKey;
    private String model;

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
