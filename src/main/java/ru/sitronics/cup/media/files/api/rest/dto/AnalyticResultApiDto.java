package ru.sitronics.cup.media.files.api.rest.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record AnalyticResultApiDto(
    UUID id,
    OffsetDateTime createdAt,
    String processor,
    String path,
    List<RecognizedObjectApiDto> detected_objects
) {
}
