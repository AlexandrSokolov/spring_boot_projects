package com.example.jakarta_resteasy.api;


import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path(ItemRestApi.ECHO_PATH)
@Produces({MediaType.APPLICATION_JSON})
public interface ItemRestApi {
  String ECHO_PATH = "/items";

  @GET
  @Path("{id : \\d+}") //support digit only
  Item byId(@PathParam("id") Integer id);

  @GET
  @Path("/echo/{message}")
  Item echo(@PathParam("message") String message);

  @GET
  List<Item> items();

  @PUT
  @Consumes({MediaType.TEXT_PLAIN})
  Item create(@NotEmpty(message = "Path parameter must not be empty") String echoText);
}
