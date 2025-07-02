package ru.sitronics.cup.media.files.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import ru.sitronics.cup.media.files.api.rest.dto.DownloadFilesRequestApi;
import ru.sitronics.cup.media.files.api.rest.dto.FlightMediaFilesTotalApi;
import ru.sitronics.cup.media.files.api.rest.dto.MediaFileApiDto;
import ru.sitronics.cup.media.files.api.rest.dto.MediaFilesSearchFilterApiDto;

import java.util.List;
import java.util.UUID;

public interface MediaFilesApi {

    //------------------------------------- API V1 -------------------------------------------------------------

    @Operation(summary = "Get Media Files", description = "Get Media Files by page and filter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @PostMapping(value = "/files/search",
        produces = "application/json",
        consumes = "application/json")
    Page<MediaFileApiDto> getMediaFilesPage(
        @RequestBody MediaFilesSearchFilterApiDto filter,
        @ParameterObject @PageableDefault(value = 20) Pageable pageable);

    @Operation(summary = "Get Flight total info", description = "Get Flight total info by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping(value = "/flights/files/total",
        produces = "application/json")
    List<FlightMediaFilesTotalApi> getFlightTotal(@RequestBody List<UUID> flightIds);

    @Operation(summary = "Upload Media File", description = "Single media file or zip with media files")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @PostMapping(value = "/flights/{flightId}/files",
        produces = "application/json")
    void uploadFile(
        @PathVariable UUID flightId,
        @RequestParam("taskId") UUID taskId,
        @RequestParam("missionId") UUID missionId,
        @RequestParam("opticalCameraName") @NotBlank String opticalCameraName,
        @RequestParam("thermalCameraName") @NotBlank String thermalCameraName,
        HttpServletRequest request
    );

    @Deprecated
    @Operation(summary = "Download list of Media Files", description = "Download list of Media Files by" +
                                                                       " ids & requested type (SOURCE, ANALYTIC). " +
                                                                       "Return zip archive if more than one file")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @PostMapping(value = "/files/download",
        produces = "application/octet-stream",
        consumes = "application/json")
    ResponseEntity<StreamingResponseBody> downloadFiles(
        @RequestBody DownloadFilesRequestApi downloadFilesRequestApi);

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @PostMapping(value = "/files/download/{flightId}",
        produces = "application/octet-stream",
        consumes = "application/json")
    ResponseEntity<StreamingResponseBody> downloadFilesByFlightId(
        @PathVariable UUID flightId);

    @Operation(summary = "Download preview of Media File", description = "Download preview of Media File by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "File not found"),
    })
    @GetMapping(value = "/files/{fileId}/preview",
        produces = "application/octet-stream")
    ResponseEntity<Resource> getFilePreview(@PathVariable UUID fileId);

    @Operation(summary = "Delete Media Files", description = "Delete Media Files by ids")
    @PostMapping(value = "/files/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteFiles(
        @RequestParam(name = "flightId") UUID flightId,
        @RequestBody List<UUID> fileIds
    );

    @Operation(summary = "Set Media File as verified", description = "Set Media File as verified by id and send " +
                                                                     "source file to 5Gen")
    @PostMapping(value = "/files/{fileId}/setVerified")
    void setVerified(@PathVariable UUID fileId, @RequestParam(name = "value") Boolean verified);

    @Operation(summary = "Play video", description = "Play video by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "File not found"),
    })
    @GetMapping(value = "/files/{fileId}/play",
        produces = "video/mp4")
    ResponseEntity<StreamingResponseBody> playVideo(
        @PathVariable UUID fileId,
        @RequestHeader(value = "Range", required = false) String rangeHeader
    );

    @Operation(summary = "Reconvert Media Files", description = "Reconvert Media Files by ids")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "File not found"),
    })
    @PostMapping(value = "/files/convert")
    void convertFiles(
        @RequestParam(name = "flightId") UUID flightId,
        @RequestBody List<UUID> fileId);

    @Operation(summary = "Reanalyze Media Files", description = "Reanalyze Media Files by ids")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "File not found"),
    })
    @PostMapping(value = "/files/analyze")
    void analyzeFiles(
        @RequestParam(name = "flightId") UUID flightId,
        @RequestBody List<UUID> fileId);

    @Operation(summary = "Get Media File By Id", description = "Get Media File by specified id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(value = "/files/{fileId}",
        produces = "application/json")
    ResponseEntity<MediaFileApiDto> getFileById(@PathVariable UUID fileId);

    @Operation(summary = "Download single file of Media File", description = "Download single file of Media Files by" +
            " id. " + "Return file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(value = "/files/{fileId}/download",
            produces = "application/octet-stream",
            consumes = "application/json")
    ResponseEntity<StreamingResponseBody> downloadSingleFile(
            @PathVariable UUID fileId);


    //------------------------------------- API V2 -------------------------------------------------------------

    @Operation(summary = "Download list of Media Files", description = "Download list of Media Files by" +
            " ids & requested types (SOURCE, ANALYTIC). " +
            "Return zip archive if more than one file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping(value = "/v2/files/download",
            produces = "application/octet-stream",
            consumes = "application/json")
    ResponseEntity<StreamingResponseBody> downloadFilesV2(
            @RequestParam("include_source") Boolean include_source,
            @RequestParam("include_analytic") Boolean include_analytic,
            @RequestBody List<UUID> downloadIds);
}