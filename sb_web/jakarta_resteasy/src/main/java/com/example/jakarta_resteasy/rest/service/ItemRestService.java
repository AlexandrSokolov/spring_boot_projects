package com.example.jakarta_resteasy.rest.service;


import com.example.jakarta_resteasy.api.Item;
import com.example.jakarta_resteasy.api.ItemRestApi;
import com.example.jakarta_resteasy.service.ItemService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemRestService implements ItemRestApi {

  private static final Logger logger = LogManager.getLogger(ItemRestService.class.getName());

  @Autowired
  ItemService itemService;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ServletContext servletContext;

  @Autowired
  private HttpServletRequest httpServletRequest;

  @Context
  private HttpServletRequest httpServletRequestViaContext;



  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public ServletContext getServletContext() {
    return servletContext;
  }

  public HttpServletRequest getHttpServletRequest() {
    return httpServletRequest;
  }

  public HttpServletRequest getHttpServletRequestViaContext() {
    return httpServletRequestViaContext;
  }

  @Override
  public Item byId(Integer id) {

    logger.debug(() -> "rest call - get item by id = " + id);

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
