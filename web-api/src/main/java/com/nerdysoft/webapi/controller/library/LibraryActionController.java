package com.nerdysoft.webapi.controller.library;

import com.nerdysoft.webapi.dto.library.BorrowBooksRequestDto;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

public interface LibraryActionController {

  @POST
  @Path("/api/v1/borrow")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object borrowBooks(@NotNull final BorrowBooksRequestDto borrowBooksRequestDto);

}
