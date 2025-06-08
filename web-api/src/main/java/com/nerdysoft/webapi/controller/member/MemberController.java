package com.nerdysoft.webapi.controller.member;

import com.nerdysoft.webapi.dto.member.MemberRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

public interface MemberController {

  @GET
  @Path("/api/v1/member/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object get(@Valid @NotNull @PathParam("id") Long id);

  @POST
  @Path("/api/v1/member")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object create(@Valid @NotNull MemberRequestDto memberRequestDto);

  @PUT
  @Path("/api/v1/member/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object update(@Valid @NotNull @PathParam("id") Long id,
                @Valid @NotNull MemberRequestDto requestDto);

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Object delete(@Valid @NotNull @PathParam("id") Long id);

}
