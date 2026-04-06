package com.aoizora.api.dto;

import java.util.Objects;

public class BookDTO {

    private String id;
    private Integer bookcaseLevel;
    private Integer bookcaseOrder;

    public BookDTO() {

    }

    public BookDTO(String id, Integer bookcaseLevel, Integer bookcaseOrder) {
        this.id = id;
        this.bookcaseLevel = bookcaseLevel;
        this.bookcaseOrder = bookcaseOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBookcaseLevel() {
        return bookcaseLevel;
    }

    public void setBookcaseLevel(Integer bookcaseLevel) {
        this.bookcaseLevel = bookcaseLevel;
    }

    public Integer getBookcaseOrder() {
        return bookcaseOrder;
    }

    public void setBookcaseOrder(Integer bookcaseOrder) {
        this.bookcaseOrder = bookcaseOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
