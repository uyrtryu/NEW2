package ru.sitronics.cup.media.files.api.rest;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface AnalyticFilesApi {

    @Operation(summary = "Download analytic result of Media File", description = "Download analytic result of Media Files by" +
            " ids. " + "Return file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping(value = "/files/{fileId}/download/analytics/{analyticsId}",
            produces = "application/octet-stream",
            consumes = "application/json")
    ResponseEntity<StreamingResponseBody> downloadAnalyticFile(
            @PathVariable UUID fileId,
            @PathVariable UUID analyticsId);

    @Operation(summary = "Get available object types for detecting", description = "Get available object types for detecting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @GetMapping(value = "/object-types",
            produces = "application/json")
    List<String> getAvailableObjectTypes();


}
