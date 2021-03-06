package com.yao.dto;

import com.yao.domain.Book;
import com.yao.util.CustomBeanUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * Created by Jack Yao on 2021/6/14 10:53 上午
 */
/*把data轉換成object*/

public class BookDTO {

    @Length(min = 3)
    private String author;
    @Length(max = 20)
    private String description;
    @NotBlank
    private String name;
    @NotNull
    private Integer status;

    public BookDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void convertToBook(Book book) {
        new BookConvert().convert(this,book);
    }
    public Book convertToBook() {
        return new BookConvert().convert(this);
    }

    public class BookConvert implements Convert<BookDTO, Book> {
        /*空直不複製，這不能只能空直，但是可以指定要傳哪些。不傳的丟在該陣列nullPropertyNames就好*/
        @Override
        public Book convert(BookDTO bookDTO, Book book) {
            String[] nullPropertyNames = CustomBeanUtils.getNullPropertyNames(bookDTO);
            /*bookDTO複製到book，但是過濾掉nullPropertyNames*/
            BeanUtils.copyProperties(bookDTO, book, nullPropertyNames);
            return book;
        }

        @Override
        public Book convert(BookDTO bookDTO) {
            Book book = new Book();
            BeanUtils.copyProperties(bookDTO, book);
            return book;
        }
    }
}
