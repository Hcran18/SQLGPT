import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SQLGPT {
    private static final GPTINFO gptinfo = new GPTINFO();
    private static final GPTCONN gpt = new GPTCONN(
            gptinfo.getURL(),
            gptinfo.getApiKey(),
            gptinfo.getModel()
    );

    public static String chatGPT(String prompt) {
        try {
            return returnResponseAsString(getResponse(prompt, gpt));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String returnResponseAsString(BufferedReader br) throws IOException {
        String line;
        StringBuffer response = new StringBuffer();

        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        return extractMessageFromJSONResponse(response.toString());
    }

    private static BufferedReader getResponse(String prompt, GPTCONN gpt) throws IOException {
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
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return br;
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);
    }

    public static void main(String[] args) {
        System.out.println(chatGPT("hello, how are you? Can you tell me what's a Fibonacci Number?"));
    }
}
