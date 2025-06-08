package com.nerdysoft.webapi.controller.member;

import com.nerdysoft.webapi.dto.member.MemberRequestDto;
import com.nerdysoft.webapi.dto.member.MemberResponseDto;
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
 * Controller with CRUD operations for managing data of MemberController.
 */
public interface MemberController {

  @Operation(summary = "Getting member by specified id.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The member was found by the provided id.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MemberResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The member does not exist with the given member.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @GET
  @Path("/api/v1/member/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  MemberResponseDto get(@Valid @NotNull @Parameter(name = "id",
      description = "The id of the member that will be retrieved.",
      example = "1", required = true) @PathParam("id") Long id);

  @Operation(summary = "Creating member by provided data.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The member was create successfully.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MemberResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @POST
  @Path("/api/v1/member")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  MemberResponseDto create(@Valid @NotNull @RequestBody MemberRequestDto memberRequestDto);

  @Operation(summary = "Updating member by specified id and provided data.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The member was updated successfully.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MemberResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The member does not exist with the given id.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @PUT
  @Path("/api/v1/member/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  MemberResponseDto update(@Valid @NotNull @Parameter(name = "id",
                               description = "The id of the member that will be updated.",
                               example = "1", required = true) @PathParam("id") Long id,
                           @Valid @NotNull @RequestBody MemberRequestDto requestDto);

  @Operation(summary = "Deleting member by specified id.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The member deleted by the provided id.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MemberResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The member does not exist with the given id.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  MemberResponseDto delete(@Valid @NotNull @Parameter(name = "id",
      description = "The id of the member that will be deleted.",
      example = "1", required = true) @PathParam("id") Long id);

}
