package ru.sitronics.cup.media.files.api.rest.dto;

import java.util.UUID;

public record FlightMediaFilesTotalApi(
    UUID flightId,
    int videoCount,
    int imageCount,
    boolean hasErrors,
    boolean allProcessed
) {
}
