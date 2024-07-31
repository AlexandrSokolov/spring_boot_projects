package com.example.jakarta_resteasy.rest.config;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Provider
 public class ViolationValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  @Context
  UriInfo uriInfo;

  @Override
  public Response toResponse(ConstraintViolationException exception) {

    final var jsonObject = JsonNodeFactory.instance.objectNode()
      .put("host", uriInfo.getAbsolutePath().getHost())
      .put("request_url", uriInfo.getRequestUri().toString())
      .put("title", "Validation Errors")
      .set("errors", JsonNodeFactory.instance.arrayNode()
        .addAll(exception.getConstraintViolations().stream()
          .map(constraintViolation -> {
            var className = constraintViolation.getLeafBean().toString().split("@")[0];
            var message = constraintViolation.getMessage();
            var methodName = constraintViolation.getPropertyPath().toString().split("\\.")[0];
            //var propertyPath = constraintViolation.getPropertyPath().toString().split("\\.")[2];
            return JsonNodeFactory.instance.objectNode()
              .put("class", className)
              .put("methodName", methodName)
//              .put("field", propertyPath)
              .put("violationMessage", message);
          }).collect(Collectors.toList())));

    return Response.status(Response.Status.BAD_REQUEST)
      .header("validation-exception", true)
      .entity(jsonObject)
      .type(MediaType.APPLICATION_JSON)
      .build();
  }

}
