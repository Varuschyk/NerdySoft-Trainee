package com.nerdysoft.webapi.controller.library;

import com.nerdysoft.webapi.dto.library.BorrowBooksRequestDto;
import com.nerdysoft.webapi.dto.library.ReturnBooksRequestDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

public interface LibraryActionController {

  @GET
  @Path("/api/v1/library/book/borrowed/member")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object getBorrowedByMemberName(@Valid @NotBlank String memberName);

  @GET
  @Path("/api/v1/library/book/borrowed/title")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object getBorrowedByTitle(@Valid @NotBlank String title);

  @POST
  @Path("/api/v1/library/book/borrow")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object borrowBook(@Valid @NotNull @RequestBody BorrowBooksRequestDto borrowBooksRequestDto);

  @DELETE
  @Path("/api/v1/library/book/return")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object returnBook(@Valid @NotNull @RequestBody ReturnBooksRequestDto returnBooksRequestDto);
}
