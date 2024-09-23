package com.example.external.api;


import com.example.external.config.AppExternalConfiguration;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path(AppConfigRestApi.PATH)
@Produces({MediaType.APPLICATION_JSON})
public interface AppConfigRestApi {
  String PATH = "/config";

  @GET
  AppExternalConfiguration config();
}
