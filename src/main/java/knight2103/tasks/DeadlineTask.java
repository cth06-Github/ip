package knight2103.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Models a task to do that contains a deadline for completion.
 */
public class DeadlineTask extends Task {
    protected final LocalDate deadline;
    private static final String DEADLINE_IDENTIFIER = "D";
    private static final String DATE_TIME_FORMAT_FOR_LIST = "d MMM yyyy";

    /**
     * Constructs a task-with-deadline object which contains a description of the task.
     * The object by default has the completion status set as not done.
     * The object also contains information of the deadline for task completion.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     * @throws DateTimeParseException If the deadline in DeadlineTask instance is not written in
     * yyyy-MM-dd format
     */
    public DeadlineTask(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the TaskType of the DeadlineTask object.
     *
     * @return TaskType of the DeadlineTask object.
     */
    public TaskType showTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toStringInFile() {
        return String.format("%s %s | %s",
                DEADLINE_IDENTIFIER, super.toStringInFile(), this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                DEADLINE_IDENTIFIER, super.toString(), this.deadline.format(
                        DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_FOR_LIST)));
    }
}