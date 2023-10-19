package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.event.ViewEventsCommand;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    /**
     * Returns true if the tags panel should be displayed to the user, else false.
     * @return true if the tags panel should be displayed to the user, else false
     */
    public boolean shouldDisplayTagsPanel() {
        if (feedbackToUser.equals(ViewTagsCommand.MESSAGE_SUCCESS)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the contacts panel should be displayed to the user, else false.
     * @return true if the contacts panel should be displayed to the user, else false
     */
    public boolean shouldDisplayContactsPanel() {
        if (feedbackToUser.equals(ListCommand.MESSAGE_SUCCESS)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the events panel should be displayed to the user, else false.
     * @return true if the events panel should be displayed to the user, else false
     */
    public boolean shouldDisplayEventsPanel() {
        if (feedbackToUser.equals(ViewEventsCommand.MESSAGE_SUCCESS)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .toString();
    }

}
