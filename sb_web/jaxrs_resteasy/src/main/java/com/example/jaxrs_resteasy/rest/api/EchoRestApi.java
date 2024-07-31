package com.example.jaxrs_resteasy.rest.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.example.jaxrs_resteasy.rest.api.EchoRestApi.ECHO_PATH;

@Path(ECHO_PATH)
@Produces({MediaType.APPLICATION_JSON})
public interface EchoRestApi {
  String ECHO_PATH = "/echo";

  @GET
  @Path("/{message}")
  String echo(@PathParam("message") String message);
}
