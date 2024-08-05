package com.example.jpa.liquibase.api;

import java.time.ZonedDateTime;

public record Item(Long id, String name, ZonedDateTime dateTime) {
}
