package ru.sitronics.cup.media.files.api.rest.dto;

import ru.sitronics.cup.media.files.api.enums.CameraTypeApiEnum;
import ru.sitronics.cup.media.files.api.enums.FileTypeApiEnum;
import ru.sitronics.cup.media.files.api.enums.ImageTypeApiEnum;
import ru.sitronics.cup.media.files.api.enums.ProcessingTypeApiEnum;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record MediaFilesSearchFilterApiDto(
    List<UUID> flightIds,
    List<UUID> taskIds,
    List<UUID> missionIds,
    FileTypeApiEnum type,
    ImageTypeApiEnum imageType,
    String cameraName,
    CameraTypeApiEnum cameraType,
    ProcessingTypeApiEnum processingType,
    OffsetDateTime shootingTsFrom,
    OffsetDateTime shootingTsTo,
    List<CoordinateDto> area,
    List<String> detectedObjects
) {
}
