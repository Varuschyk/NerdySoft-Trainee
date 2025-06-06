package com.nerdysoft.webapi.controller.member;

import com.nerdysoft.webapi.dto.member.MemberRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;

public interface MemberController {

  @GET
  @Path("/api/v1/member/{id}")
  Object get(@Valid @NotNull @PathParam("id") Long id);

  @POST
  @Path("/api/v1/member")
  Object create(@Valid @NotNull MemberRequestDto memberRequestDto);

  @PUT
  @Path("/api/v1/member/{id}")
  Object update(@Valid @NotNull @PathParam("id") Long id,
                @Valid @NotNull MemberRequestDto requestDto);

  @DELETE
  Object delete(@Valid @NotNull @PathParam("id") Long id);

}
