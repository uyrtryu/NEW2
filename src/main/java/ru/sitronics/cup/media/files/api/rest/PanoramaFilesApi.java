package ru.sitronics.cup.media.files.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.UUID;

public interface PanoramaFilesApi {

    @Operation(summary = "Get panorama config json by file id", description = "Download panorama config json by file " +
        "id." +
        "Return file")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found"),
        @ApiResponse(responseCode = "409", description = "Conflict. It's not a panorama file")
    })
    @GetMapping(value = "/files/{fileId}/download/panorama/config",
        produces = "application/octet-stream",
        consumes = "application/json")
    ResponseEntity<StreamingResponseBody> downloadPanoramaConfigFile(@PathVariable UUID fileId);

    @Operation(summary = "Get tile file by file id and tile name", description = "Download tile file by file id and " +
        "tile name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found"),
        @ApiResponse(responseCode = "409", description = "Conflict. It's not a panorama file")
    })
    @GetMapping(value = "/file/{fileId}/download/panorama/{zoomLevel}/{tileName}", produces = "application/json")
    ResponseEntity<StreamingResponseBody> downloadPanoramaTileFile(
        @PathVariable(value = "fileId") UUID fileId,
        @PathVariable(value = "zoomLevel") String zoomLevel,
        @PathVariable(value = "tileName") String tileName);

}
