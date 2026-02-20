package ploopy;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ploopy.command.AddCommand;
import ploopy.command.ExitCommand;
import ploopy.command.ListCommand;
import ploopy.command.MarkCommand;

public class ParserTest {
    @Test
    public void parseTest() {
        try {
            assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
            assertInstanceOf(ListCommand.class, Parser.parse("list"));
            assertInstanceOf(AddCommand.class, Parser.parse("todo deadline"));
            assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
            assertInstanceOf(AddCommand.class, Parser.parse("deadline return book /by 2026-03-01"));
        } catch (PloopyException e) {
            System.out.println("Error");
        }
    }
    @Test
    public void parseTestBadInput() {
        assertThrows(PloopyException.class, () -> Parser.parse("byebye"));
        assertThrows(PloopyException.class, () -> Parser.parse("listy"));
        assertThrows(PloopyException.class, () -> Parser.parse("lis"));
        assertThrows(PloopyException.class, () -> Parser.parse("marky 1"));
    }
}
