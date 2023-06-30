package com.example.jakarta_resteasy.rest.service;


import com.example.jakarta_resteasy.api.Item;
import com.example.jakarta_resteasy.api.ItemRestApi;
import com.example.jakarta_resteasy.service.ItemService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemRestService implements ItemRestApi {

  @Autowired
  ItemService itemService;

  @Override
  public Item byId(Integer id) {
    return itemService.findById(id).orElseThrow(() -> new NotFoundException("Item by '" + id + "' not found"));
  }

  @Override
  public Item echo(String message) {
    return Item.fromName(message);
  }

  @Override
  public List<Item> items() {
    return itemService.items();
  }

  @Override
  public Item create(String message) {
    return itemService.update(message);
  }

}
