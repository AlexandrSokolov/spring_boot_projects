package com.example.jpa.liquibase.service;

import com.example.jpa.liquibase.api.Item;
import com.example.jpa.liquibase.repository.ItemEntity;
import com.example.jpa.liquibase.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class ItemServiceTest {

  public static final String ITEM_NAME = "test name 758";

  private ItemService itemService = new ItemService();

  private ItemRepository itemRepository = mock(ItemRepository.class);

  private Service2 service2 = mock(Service2.class);

  @BeforeEach
  public void init() {
    itemService.itemRepository = itemRepository;
    itemService.service2 = service2;

    Mockito.doReturn(List.of(
      itemEntity(),
      itemEntity(),
      itemEntity()))
      .when(this.itemRepository).findAll();

    Mockito.doReturn(item()).when(service2).findById(anyInt());
  }

  @Test
  public void testFindByIdOrError() {
    Assertions.assertEquals(ITEM_NAME, itemService.findByIdOrError(1).name());
    Mockito.verify(service2, times(1)).findById(anyInt());
  }

  @Test
  public void testFindAll() {
    var result = itemService.items();
    Assertions.assertEquals(3, result.size());
    Mockito.verify(itemRepository, times(1)).findAll();
  }


  private ItemEntity itemEntity() {
    return new ItemEntity();
  }

  private Item item() {
    return new Item(1L, ITEM_NAME, ZonedDateTime.now());
  }
}
