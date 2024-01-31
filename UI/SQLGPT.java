package UI;

import BusinessLogic.DataAccess.Service.DASInterface;
import BusinessLogic.DataAccess.Service.DataAccessService;
import BusinessLogic.LanguageModel.GPT;
import BusinessLogic.LanguageModel.LanguageModelInterface;

public class SQLGPT {
    public static void main(String[] args) {
        LanguageModelInterface languageModel = new GPT();
        DASInterface service = new DataAccessService();

        //TODO: Create SQL prompt from tables and question and give to chat gpt to receive SQL statement
        //TODO: Take gpt.sql and give it to chat as a prompt
        String schema = "Tables:" +
                "Library (library_id INT, location VARCHAR(50))" +
                "Book (book_id INT, title VARCHAR(50), author VARCHAR(50), genre VARCHAR(50))" +
                "LibraryBook (library_id INT, book_id INT, copies_available INT)" +
                "People (person_id INT, first_name VARCHAR(50), last_name VARCHAR(255))" +
                "CheckedOutBooks (person_id INT, book_id INT, checkout_date DATE, return_date DATE)";

        String question = "Give me the people who have checked out a book and what book they have checked out";

        String SQL = languageModel.chat("Here is my Database schema: " +
                schema +
                "I want you to give me a MYSQL query that answers this question: " +
                question + "Do not explain. Only return MYSQL.");

        //TODO: Run SQL statement from gpt as a prompt to get SQL information
        String noNewLines = removeNewlines(SQL);
        String retrievedData = service.retrieveData(noNewLines);

        //TODO: Give gpt SQL information as a prompt to receive friendly response to the original prompt
        System.out.println(languageModel.chat("Answer this question: " + question + "Using this database schema: " + schema + " and using this data from my database: " + retrievedData));
    }

    public static String removeNewlines(String input) {
        // Replace newline characters with an empty string
        String result = input.replaceAll("\\\\n", " ");
        return result;
    }
}
