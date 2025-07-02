package ru.sitronics.cup.media.files.api.rest.dto;

import ru.sitronics.cup.media.files.api.enums.DownloadFileTypeApiEnum;

import java.util.List;
import java.util.UUID;

public record DownloadFilesRequestApi(
    List<UUID> downloadIds,
    DownloadFileTypeApiEnum type
) {
}
