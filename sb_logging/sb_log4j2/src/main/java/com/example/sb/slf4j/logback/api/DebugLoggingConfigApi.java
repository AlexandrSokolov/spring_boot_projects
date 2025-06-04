package com.example.sb.slf4j.logback.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path(DebugLoggingConfigApi.LOGGING_CONFIG_PATH)
@Produces({MediaType.APPLICATION_JSON})
public interface DebugLoggingConfigApi {

  String LOGGING_CONFIG_PATH = "/logging/config";

  @POST
  @Path("/debug/{packageName}")
  void enableDebugLevel(@PathParam("packageName") String packageName);

  @POST
  @Path("/info/{packageName}")
  void resetDebugLevel(@PathParam("packageName") String packageName);
}
