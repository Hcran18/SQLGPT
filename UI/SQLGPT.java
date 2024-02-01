package UI;

import BusinessLogic.DataAccess.Service.DASInterface;
import BusinessLogic.DataAccess.Service.DataAccessService;
import BusinessLogic.LanguageModel.ChatGPT;
import BusinessLogic.LanguageModel.LanguageModelInterface;

public class SQLGPT {
    public static void main(String[] args) {
        LanguageModelInterface languageModel = new ChatGPT();
        DASInterface service = new DataAccessService();

        //Zero Shot Query

        // Create the Schema
        String schema = "Tables:" +
                "Library (library_id INT, location VARCHAR(50))" +
                "Book (book_id INT, title VARCHAR(50), author VARCHAR(50), genre VARCHAR(50))" +
                "LibraryBook (library_id INT, book_id INT, copies_available INT)" +
                "People (person_id INT, first_name VARCHAR(50), last_name VARCHAR(50))" +
                "CheckedOutBooks (person_id INT, book_id INT, checkout_date DATE, return_date DATE)";

        // Create the Question
        String question = "How many available copies of to kill a mocking bird are there?";

        // Retrieve SQL for question from LLM
        String SQL = languageModel.chat("Here is my Database schema: " +
                schema +
                "I want you to give me a MYSQL query that answers this question: " +
                question +
                "Do not explain. Only return MYSQL.");

        System.out.println("SQL Query from LLM: \n" + SQL);

        // Remove new lines from SQL statement
        String noNewLines = removeNewlines(SQL);

        // Retrieve data from DB using SQL statement from LLM
        String retrievedData = service.retrieveData(noNewLines);

        System.out.println("Retrieved Data: \n" + retrievedData);

        // Ask LLM to answer the original question based on the retrieved information
        System.out.println(languageModel.chat("Answer this question: " + question +
                " Using this database schema: " + schema +
                " Use this data I retrieved from the database to answer: " + retrievedData + " Use only text when responding"));
    }

    private static String removeNewlines(String input) {
        // Replace newline characters with a space
        return input.replaceAll("\\\\n", " ");
    }
}
