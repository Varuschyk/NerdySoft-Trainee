package com.nerdysoft.webapi.controller.book;

import com.nerdysoft.webapi.dto.book.BookRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

public interface BookController {

  @Operation(summary = "Updates the existing candidate with the provided data "
      + "by the provided static id.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The candidate was found by the provided static id and updated.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = BookRequestDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = BookRequestDto.class))}),
      @ApiResponse(responseCode = "401", description = "The requester is not authenticated.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = BookRequestDto.class))}),
      @ApiResponse(responseCode = "403",
          description = "The requester does not access have to the requested resource.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = BookRequestDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The user does not exist with the given static id.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = BookRequestDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = BookRequestDto.class))})})
  @GET
  @Path("/api/v1/book/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object get(@Valid @NotNull @PathParam("id")
             @Parameter(name = "id",
                 description = "The static id of the user for who the candidate profile is created.",
                 example = "1", required = true) Long id);

  @POST
  @Path("/api/v1/book")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object create(@Valid @NotNull BookRequestDto memberRequestDto);

  @PUT
  @Path("/api/v1/book/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object update(@Valid @NotNull @PathParam("id") Long id,
                @Valid @NotNull BookRequestDto requestDto);

  @DELETE
  @Path("/api/v1/book/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object delete(@Valid @NotNull @PathParam("id") Long id);
}
