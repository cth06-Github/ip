package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

/**
 * Models after a command that takes in a key word, finds tasks that matches the key word
 * and shows a list of matched tasks.
 */
public class FindCommand extends Command {
    FindCommand(CommandVerb verb, String wordToSearch) {
        super(verb, wordToSearch); // verb must be CommandVerb.FIND
    }

    /**
     * Executes the FindCommand which finds all tasks with the description that contains the word to be
     * searched as a substring or string. It returns the list of matched tasks in the bot's GUI.
     *
     * @param tasks The object storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The object containing the file that saves the list of tasks.
     * @return The list of matched tasks in the bot's GUI after command execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String wordToSearch = this.description;
        TaskList filteredTasks = tasks.filter(
                task -> task.getDescription().contains(wordToSearch));
        String matchedTaskListInString = filteredTasks.toString();
        return ui.showFind(matchedTaskListInString);
    }
}
