package BusinessLogic.LanguageModel;

import BusinessLogic.data.GPTCONN;
import BusinessLogic.data.GPTINFO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GPT implements LanguageModelInterface {
    private static final GPTINFO gptinfo = new GPTINFO();
    private static final GPTCONN gpt = new GPTCONN(
            gptinfo.getURL(),
            gptinfo.getApiKey(),
            gptinfo.getModel()
    );

    @Override
    public String chat(String prompt) {
        try {
            return returnResponseAsString(getResponse(prompt, gpt));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String returnResponseAsString(BufferedReader br) throws IOException {
        String line;
        StringBuffer response = new StringBuffer();

        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        return extractMessageFromJSONResponse(response.toString());
    }

    private BufferedReader getResponse(String prompt, GPTCONN gpt) throws IOException {
        URL obj = new URL(gpt.getUrl());
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + gpt.getApiKey());
        connection.setRequestProperty("Content-Type", "application/json");

        // The request body
        String body = "{\"model\": \"" + gpt.getModel() + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(body);
        writer.flush();
        writer.close();

        // Response from ChatGPT
        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }

    private String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);
    }
}