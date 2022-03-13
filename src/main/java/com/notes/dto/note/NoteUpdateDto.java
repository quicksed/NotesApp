package com.notes.dto.note;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;

@Getter
@Setter
public class NoteUpdateDto {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private ZonedDateTime creationDate;

    @NotEmpty
    private Boolean isDeleted;

    private Long userId;
}
