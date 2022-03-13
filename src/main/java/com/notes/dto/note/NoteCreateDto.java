package com.notes.dto.note;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;

@Getter
@Setter
public class NoteCreateDto {

    @NotEmpty
    private Long userId;

    @NotEmpty
    private String name;

    @NotEmpty
    private ZonedDateTime creationDate;
}
