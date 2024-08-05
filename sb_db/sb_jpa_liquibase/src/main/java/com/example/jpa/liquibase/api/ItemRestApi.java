package com.example.jpa.liquibase.api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path(ItemRestApi.ITEMS_PATH)
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ItemRestApi {
  String ITEMS_PATH = "/items";

  @GET
  @Path("{id : \\d+}") //support digit only
  Item byId(@PathParam("id") Integer id);

  @GET
  List<Item> items();

  @POST
  Item create(Item newItem);

  @PUT
  void update(Item item);
}
