package com.example.jakarta_resteasy.service;

import com.example.jakarta_resteasy.api.Item;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
public class ItemService {

  public static final int ID = 1;

  Map<Integer, Item> items = Map.of(ID, Item.fromName("test item"));

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
