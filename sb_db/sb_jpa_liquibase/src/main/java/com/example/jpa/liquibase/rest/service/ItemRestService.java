package com.example.jpa.liquibase.rest.service;


import com.example.jpa.liquibase.api.Item;
import com.example.jpa.liquibase.api.ItemRestApi;
import com.example.jpa.liquibase.service.ItemService;
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
    return itemService.findById(id).orElseThrow(NotFoundException::new);
  }

  @Override
  public List<Item> items() {
    return itemService.items();
  }

  @Override
  public Item create(Item newItem) {
    return itemService.create(newItem);
  }

  @Override
  public void update(Item item) {
    itemService.update(item);
  }
}
