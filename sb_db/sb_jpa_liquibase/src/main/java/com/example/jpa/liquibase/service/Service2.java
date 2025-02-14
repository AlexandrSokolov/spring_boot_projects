package com.example.jpa.liquibase.service;

import com.example.jpa.liquibase.api.Item;
import com.example.jpa.liquibase.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Service2 {

  @Autowired
  private ItemRepository itemRepository;

  public Item findById(Integer id) {
    return itemRepository.findById(id.longValue())
      .map(entity -> new Item(entity.getId(), entity.getName(), entity.getDateTime()))
      .orElseThrow(() -> new EntityNotFoundException("Item with id " + id + " not found"));
  }
}
