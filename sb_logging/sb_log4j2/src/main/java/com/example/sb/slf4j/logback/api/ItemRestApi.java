package com.example.sb.slf4j.logback.api;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path(ItemRestApi.ECHO_PATH)
@Produces({MediaType.APPLICATION_JSON})
public interface ItemRestApi {
  String ECHO_PATH = "/items";

  @GET
  @Path("{id : \\d+}") //support digit only
  Item byId(@PathParam("id") Integer id);
}
