package com.nerdysoft.webapi.controller.book;

import com.nerdysoft.webapi.dto.book.BookRequestDto;
import com.nerdysoft.webapi.dto.book.BookResponseDto;
import com.nerdysoft.webapi.error.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Controller with CRUD operations for managing data of BookEntity.
 */
public interface BookController {

  @Operation(summary = "Getting book by specified id.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The book was found by the provided id.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = BookResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The book does not exist with the given id.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @GET
  @Path("/api/v1/book/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  BookResponseDto get(@Valid @NotNull @PathParam("id")
                      @Parameter(name = "id",
                          description = "The id of the book that will be retrieved.",
                          example = "1", required = true) Long id);

  @Operation(summary = "Creating book by provided data.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The book was create successfully.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = BookResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @POST
  @Path("/api/v1/book")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  BookResponseDto create(@Valid @NotNull @RequestBody BookRequestDto memberRequestDto);

  @Operation(summary = "Updating book by specified id and provided data.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The book was updated successfully.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = BookResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The book does not exist with the given id.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @PUT
  @Path("/api/v1/book/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  BookResponseDto update(@Valid @NotNull @Parameter(name = "id",
                             description = "The id of the book that will be updated.",
                             example = "1", required = true) @PathParam("id") Long id,
                         @Valid @NotNull @RequestBody BookRequestDto requestDto);

  @Operation(summary = "Deleting book by specified id.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The book deleted by the provided id.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = BookResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The book does not exist with the given id.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @DELETE
  @Path("/api/v1/book/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  BookResponseDto delete(@Valid @NotNull @Parameter(name = "id",
      description = "The id of the book that will be deleted.",
      example = "1", required = true) @PathParam("id") Long id);
}
