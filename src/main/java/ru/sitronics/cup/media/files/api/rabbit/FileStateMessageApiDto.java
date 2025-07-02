package ru.sitronics.cup.media.files.api.rabbit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.locationtech.jts.geom.Geometry;
import ru.sitronics.cup.media.files.api.enums.CameraTypeApiEnum;
import ru.sitronics.cup.media.files.api.enums.FileStageApiEnum;
import ru.sitronics.cup.media.files.api.enums.FileTypeApiEnum;
import ru.sitronics.cup.media.files.api.enums.ImageTypeApiEnum;
import ru.sitronics.cup.media.files.api.rest.dto.AnalyticResultApiDto;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FileStateMessageApiDto(
    UUID id,
    UUID flightId,
    UUID missionId,
    UUID taskId,
    String name,
    String path,
    String miniPath,
    FileStageApiEnum stage,
    String errorMessage,
    List<AnalyticResultApiDto> analyticResult,
    String cameraName,
    CameraTypeApiEnum cameraType,
    OffsetDateTime shootingTs,
    Integer durationSec,
    Geometry route,
    ImageTypeApiEnum imageType,
    FileTypeApiEnum type,
    Integer resolutionV,
    Integer resolutionH,
    Long size
) {
}
