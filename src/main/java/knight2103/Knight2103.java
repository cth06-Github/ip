package knight2103;

import knight2103.command.InvalidCommandException;
import knight2103.command.Parser;
import knight2103.command.Command;
import knight2103.command.MissingCommand;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

import java.io.FileNotFoundException;

public class Knight2103 {
    private String welcomeMessage;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Knight2103(String filePath) {
        this.welcomeMessage = "Hello! I'm Knight2103\nWhat can I do for you?";
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) { // file be loaded regardless
            this.welcomeMessage += "\nNote: the Storage File to initialise is not found. "
                    + "Will create new file instead\n. Existing storage file should be"
                    + "named as \"savedTaskList.txt\" in root directory";
            this.tasks = new TaskList();
        }
    }

    /**
     * Trigger a welcome message of the chatbot.
     */
    public String enableWelcome() { // or directly return this.welcomeMessage?
        return ui.showWelcome(this.welcomeMessage);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String output;
            try {
                Command c = Parser.parse(input).orElseThrow(() -> new MissingCommand());
                output = c.execute(tasks, ui, storage);
                return output;
            } catch (MissingCommand e) {
                return e.getMessage();
            } catch (InvalidCommandException e) {
                return "Instruction wrong format.";
            }

            // input to allow chatbot to exit the application?
    }
}