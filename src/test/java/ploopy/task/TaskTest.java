package ploopy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ploopy.PloopyException;

public class TaskTest {
    @Test
    public void encodeTest() {
        Task t = new ToDo("borrow book");
        assertEquals("T/0/borrow book", t.encodeString());
        try {
            t = new Event("midterms", "2026-03-01", "2026-03-14");
            t.mark(true);
            assertEquals("E/1/midterms/2026-03-01/2026-03-14", t.encodeString());
        } catch (PloopyException e) {
            assertEquals(4, 3);
        }
    }
}
