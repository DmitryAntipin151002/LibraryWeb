package com.example.Library.mapper;

import com.example.Library.dto.BookDTO;
import com.example.Library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    // Маппинг всех полей сущности Book в DTO BookDTO
    @Mapping(source = "bookId", target = "bookId")
    @Mapping(source = "isbn", target = "isbn")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "authors.firstName", target = "firstName")  // Убедитесь, что это поле правильно указано
    @Mapping(source = "authors.lastName", target = "lastName")    // Убедитесь, что это поле правильно указано
    BookDTO toDTO(Book book);

    List<BookDTO> toDTOList(List<Book> books);

    // Маппинг всех полей DTO BookDTO в сущность Book
    @Mapping(source = "bookId", target = "bookId")
    @Mapping(source = "isbn", target = "isbn")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "genre", target = "genre")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "authors", ignore = true)  // Игнорируем поле author, если оно не нужно
    Book toEntity(BookDTO bookDTO);
}


