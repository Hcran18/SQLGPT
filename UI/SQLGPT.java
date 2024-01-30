package UI;

import BusinessLogic.LanguageModel.GPT;
import BusinessLogic.LanguageModel.LanguageModelInterface;

public class SQLGPT {
    public static void main(String[] args) {
        LanguageModelInterface languageModel = new GPT();
        //TODO: Create SQL Tables

        //TODO: Create SQL prompt from tables and question and give to chat gpt to receive SQL statement

        //TODO: Run SQL statement from gpt as a prompt to get SQL information

        //TODO: Give gpt SQL information as a prompt to receive friendly response to the original prompt
        System.out.println(languageModel.chat("hello, how are you? Can you tell me what's a Fibonacci Number?"));
    }
}
