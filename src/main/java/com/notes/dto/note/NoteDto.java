package com.notes.dto.note;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDto {

    private Long id;

    private Long userId;

    private String name;

    private String creationDate;

    private Boolean isDeleted;
}
