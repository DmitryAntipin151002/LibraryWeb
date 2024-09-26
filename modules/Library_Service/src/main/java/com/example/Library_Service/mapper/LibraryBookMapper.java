package com.example.Library_Service.mapper;

import com.example.Library_Service.dto.LibraryBookDTO;
import com.example.Library_Service.model.LibraryBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryBookMapper {
    LibraryBookDTO toDTO(LibraryBook libraryBook);
    LibraryBook toEntity(LibraryBookDTO libraryBookDTO);
}

