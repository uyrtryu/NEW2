package ru.sitronics.cup.media.files.api.rest.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record MediaFileApiDto(
    UUID id,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt,
    UUID flightId,
    UUID missionId,
    UUID taskId,
    String name,
    String sourcePath,
    String miniPath,
    String type,
    String downloadStage,
    String convertStage,
    String coordinatesStage,
    String analyticStage,
    //TODO discuss
    List<AnalyticResultApiDto> analyticResult,
    String cameraName,
    String cameraType,
    OffsetDateTime startRecordingTs,
    Integer durationSec,
    String route,
    Boolean verified,
    String archiveStage,
    String imageType,
    String downloadError,
    String convertError,
    String coordinatesError,
    String analyticError,
    String archiveError,
    Integer resolutionV,
    Integer resolutionH,
    Long size
) {
}
