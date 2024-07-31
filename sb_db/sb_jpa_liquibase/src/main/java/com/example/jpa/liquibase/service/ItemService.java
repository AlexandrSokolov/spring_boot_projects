package com.example.jpa.liquibase.service;

import com.example.jpa.liquibase.api.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
public class ItemService {

  public static final int ID = 1;

  Map<Integer, Item> items = Map.of(ID, Item.fromName("test item"));

  public Optional<Item> findById(Integer id) {
    return Optional.ofNullable(items.get(id));
  }

  public List<Item> items() {
    return items.values()
      .stream()
      .toList();
  }

  public Item update(String message) {
    items = Map.of(ID, Item.fromName(message));
    return items.get(ID);
  }
}
