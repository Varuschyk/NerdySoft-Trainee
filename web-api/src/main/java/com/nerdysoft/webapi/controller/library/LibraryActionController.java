package com.nerdysoft.webapi.controller.library;

import com.nerdysoft.webapi.dto.book.BookResponseDto;
import com.nerdysoft.webapi.dto.book.BooksResponseDto;
import com.nerdysoft.webapi.dto.library.BorrowBooksRequestDto;
import com.nerdysoft.webapi.dto.library.ReturnBooksRequestDto;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Controller with operations for managing data of according library purposes.
 */
public interface LibraryActionController {

  @Operation(summary = "Getting borrowed books by specified member name.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The borrowed books by the provided member name.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = BookResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The member does not exist with the given name.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @GET
  @Path("/api/v1/library/book/borrowed/member")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object getBorrowedByMemberName(@Valid @NotBlank @Parameter(name = "memberName",
      description = "The member name by that borrowed books will be retrieved.",
      example = "Alexander Varushchyk", required = true) String memberName);

  @Operation(summary = "Getting borrowed books by specified title.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The borrowed books by the provided title.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = BooksResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The books does not exist with the given title.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @GET
  @Path("/api/v1/library/book/borrowed/title")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object getBorrowedByTitle(@Valid @NotBlank @Parameter(name = "title",
      description = "The title by that borrowed books will be retrieved.",
      example = "Java", required = true) String title);

  @Operation(summary = "Borrow specified book for provided member.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The book was borrowed successfully.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MemberResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The member or book does not exist with the given ids.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @POST
  @Path("/api/v1/library/book/borrow")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object borrowBook(@Valid @NotNull @RequestBody BorrowBooksRequestDto borrowBooksRequestDto);

  @Operation(summary = "Return specified book from provided member.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The book was returned successfully.",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = MemberResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "The request is malformed.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "404",
          description = "The member or book does not exist with the given ids.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error.",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponseDto.class))})})
  @DELETE
  @Path("/api/v1/library/book/return")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object returnBook(@Valid @NotNull @RequestBody ReturnBooksRequestDto returnBooksRequestDto);
}
