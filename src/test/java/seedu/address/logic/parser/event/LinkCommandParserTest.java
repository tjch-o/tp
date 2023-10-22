package seedu.address.logic.parser.event;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;
import static seedu.address.testutil.person.TypicalPersons.BENSON;
import static seedu.address.testutil.person.TypicalPersons.CARL;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.event.LinkCommand;
import seedu.address.model.person.Name;
import seedu.address.testutil.event.EventUtil;

public class LinkCommandParserTest {
    private LinkCommandParser parser = new LinkCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(CARL.getName());
        LinkCommand expectedLinkCommand = new LinkCommand(JOBFEST.getName(), contactNameList);

        assertParseSuccess(parser, " " + EventUtil.getEventName(JOBFEST.getName())
                        + EventUtil.getContactName(CARL.getName()), expectedLinkCommand);
    }

    @Test
    public void parse_multipleEvents_failure() {
        assertParseFailure(parser, " " + EventUtil.getEventName(JOBFEST.getName())
                + EventUtil.getEventName(NTU.getName()) + EventUtil.getContactName(CARL.getName()),
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENT));
    }

    @Test
    public void parse_multipleContacts_success() {
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(CARL.getName());
        contactNameList.add(BENSON.getName());
        LinkCommand expectedLinkCommand = new LinkCommand(JOBFEST.getName(), contactNameList);

        assertParseSuccess(parser, " " + EventUtil.getEventName(JOBFEST.getName())
                + EventUtil.getContactName(CARL.getName()) + EventUtil.getContactName(BENSON.getName()),
                expectedLinkCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE);

        // missing contact prefix
        assertParseFailure(parser, " " + EventUtil.getEventName(JOBFEST.getName())
                + BENSON.getName().fullName, expectedMessage);

        // missing event prefix
        assertParseFailure(parser, " " + JOBFEST.getName().eventName
                + EventUtil.getContactName(CARL.getName()), expectedMessage);
    }
}