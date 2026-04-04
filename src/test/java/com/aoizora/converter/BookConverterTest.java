package com.aoizora.converter;

import com.aoizora.api.BookDTO;
import com.aoizora.dao.domain.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookConverterTest {

    private static final float HIDDEN_OBJECTS_GAME_DROP_RATE = 0;
    private static final int BOOKCASE_ORDER = 0;
    private static final int BOOKCASE_LEVEL = 1;
    private static final String BOOK_ID = "DESTINY_BOOK";

    @Test
    public void convert() {
        BookConverter converter = new BookConverter();

        Book source = new Book(
                BOOK_ID,
                BOOKCASE_LEVEL,
                BOOKCASE_ORDER,
                HIDDEN_OBJECTS_GAME_DROP_RATE);

        var expected = new BookDTO(
                BOOK_ID,
                BOOKCASE_LEVEL,
                BOOKCASE_ORDER);

        var actual = converter.convert(source);

        assertEquals(expected, actual);
    }

}