package com.example.sb.slf4j.logback.rest.service;


import com.example.sb.slf4j.logback.api.Item;
import com.example.sb.slf4j.logback.api.ItemRestApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ItemRestService implements ItemRestApi {

  // same as LogManager.getLogger(ItemRestService.class.getName());
  private static final Logger logger = LogManager.getLogger();

  @Override
  public Item byId(Integer id) {

    logger.info(() -> "rest call - get item by id = " + id);
    logger.debug(() -> "debug message - rest call - get item by id = " + id);

    return Item.fromName("item-" + id);
  }
}
