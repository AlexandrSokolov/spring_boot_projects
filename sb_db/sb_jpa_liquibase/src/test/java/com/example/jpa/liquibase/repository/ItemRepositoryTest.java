package com.example.jpa.liquibase.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.ZonedDateTime;
import java.util.Set;

@DataJpaTest //instead of //@SpringBootTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=validate"})
public class ItemRepositoryTest {

  @Autowired
  ItemRepository itemRepository;

  @BeforeEach
  public void setUp() {
    var items = Set.of("item1", "item2", "item3").stream()
        .map(this::newItemEntity)
          .toList();
  }

  @Test
  public void testFindAll() {
    var items = itemRepository.findAll();
    Assertions.assertEquals(3, items.size());
  }

  private ItemEntity newItemEntity(String name) {
    var entity = new ItemEntity();
    entity.setName(name);
    entity.dateTime = ZonedDateTime.now();
    return itemRepository.save(entity);
  }
}
