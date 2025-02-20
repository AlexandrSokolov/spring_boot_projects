package com.example.jpa.liquibase.service;

import com.example.jpa.liquibase.api.Item;
import com.example.jpa.liquibase.repository.ItemEntity;
import com.example.jpa.liquibase.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class ItemService {

  @Autowired
  ItemRepository itemRepository;

  @Autowired
  Service2 service2;

  //fake implementation to use explicative repo and service2
  public Item findByIdOrError(Integer id) {
    var r1 = service2.findById(id);
    var r2 = this.findById(id).orElse(null);
    return r1 == null ? r2 : r1;
  }

  public Optional<Item> findById(Integer id) {
    return itemRepository.findById(id.longValue())
      .map(this::from);
  }

  public List<Item> items() {
    return itemRepository.findAll().stream().map(this::from).toList();
  }

  public Item create(Item item) {
    if (item.id() != null) {
      throw new IllegalStateException("Cannot create a new item with predefined id");
    }
    return from(
      itemRepository.save(from(item)));
  }

  public void update(Item item) {
    var e = itemRepository.findById(item.id())
      .orElseThrow(() -> new IllegalStateException("Item not found"));
    e.setName(item.name());
    e.setDateTime(item.dateTime());
    itemRepository.save(e);
  }

  private Item from(ItemEntity entity) {
    return new Item(entity.getId(), entity.getName(), entity.getDateTime());
  }

  private ItemEntity from(Item item) {
    var e = new ItemEntity();
    e.setId(item.id());
    e.setName(item.name());
    e.setDateTime(item.dateTime());
    return e;
  }
}
