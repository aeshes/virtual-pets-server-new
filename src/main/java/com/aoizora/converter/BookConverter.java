package com.aoizora.converter;

import com.aoizora.api.BookDTO;
import com.aoizora.dao.domain.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class BookConverter implements Converter<Book, BookDTO> {

    @Nullable
    @Override
    public BookDTO convert(Book source) {
        return new BookDTO(source.getId(),
                source.getBookcaseLevel(),
                source.getBookcaseOrder());
    }
}
