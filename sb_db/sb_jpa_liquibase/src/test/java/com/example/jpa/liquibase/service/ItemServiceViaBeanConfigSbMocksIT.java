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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.time.ZonedDateTime;
import java.util.List;

import static com.example.jpa.liquibase.service.ItemServiceSbMocksIT.ITEM_NAME;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ItemServiceViaBeanConfigSbMocksIT {

  @TestConfiguration
  public static class TestItemServiceConfig {
    @Bean
    public Service2 service2() {
      var service2 = Mockito.mock(Service2.class);
      given(service2.findById(anyInt())).willReturn(item());
      return service2;
    }
    private Item item() {
      return new Item(1L, ITEM_NAME, ZonedDateTime.now());
    }
  }

  @Autowired
  private ItemService itemService;

  @MockBean
  private ItemRepository itemRepository;

  @BeforeEach
  public void init() {
    given(this.itemRepository.findAll()).willReturn(List.of(
      itemEntity(),
      itemEntity(),
      itemEntity()));
  }

  @Test
  public void testSave() {
    itemRepository.save(itemEntity());
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

}
