package it.upgraded.winner.utils.logging;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LoggerFactory {

  private ObjectMapper objectMapper;

  @Inject
  public LoggerFactory(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public Logger getLogger(Class<?> clazz) {
    String className = clazz.getName();
    org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(className);
    return new Logger(slf4jLogger, objectMapper);
  }

}
