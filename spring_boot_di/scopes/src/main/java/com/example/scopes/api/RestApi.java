package com.example.scopes.api;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path(RestApi.ECHO_PATH)
@Produces({MediaType.APPLICATION_JSON})
public interface RestApi {
  String ECHO_PATH = "/items";

  @GET
  @Path("/scope")
  String scope();
}
