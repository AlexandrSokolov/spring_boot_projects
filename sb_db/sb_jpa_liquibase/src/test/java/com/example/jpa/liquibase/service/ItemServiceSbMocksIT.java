package com.example.jpa.liquibase.service;

import com.example.jpa.liquibase.api.Item;
import com.example.jpa.liquibase.repository.ItemEntity;
import com.example.jpa.liquibase.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ItemServiceSbMocksIT {

  public static final String ITEM_NAME = "test name 758";

  @Autowired
  private ItemService itemService;

  @MockBean
  private ItemRepository itemRepository;

  @MockBean
  private Service2 service2;

  @BeforeEach
  public void init() {
    given(this.itemRepository.findAll()).willReturn(List.of(
      itemEntity(),
      itemEntity(),
      itemEntity()));

    given(this.service2.findById(anyInt())).willReturn(item());
  }

  @Test
  public void testFindByIdOrError() {
    Assertions.assertEquals(ITEM_NAME, itemService.findByIdOrError(1).name());
  }

  @Test
  public void testFindAll() {
    var result = itemService.items();
    Assertions.assertEquals(3, result.size());
  }


  private ItemEntity itemEntity() {
    return new ItemEntity();
  }

  private Item item() {
    return new Item(1L, ITEM_NAME, ZonedDateTime.now());
  }
}
