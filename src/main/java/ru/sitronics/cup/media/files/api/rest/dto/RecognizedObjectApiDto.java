package ru.sitronics.cup.media.files.api.rest.dto;

import java.util.UUID;

public record RecognizedObjectApiDto(
    UUID id,
    String type,
    Double confidence
) {
}
