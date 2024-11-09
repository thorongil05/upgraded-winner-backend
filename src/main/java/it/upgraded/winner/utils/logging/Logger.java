package it.upgraded.winner.utils.logging;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Logger {

  private final org.slf4j.Logger slf4jlogger;
  private final ObjectMapper objectMapper;
  private List<String> messageList = new ArrayList<>();
  private Level currentLevel = Level.UNDEF;

  public Logger(org.slf4j.Logger slf4jlogger, ObjectMapper objectMapper) {
    this.slf4jlogger = slf4jlogger;
    this.objectMapper = objectMapper;
  }

  public Logger info() {
    this.currentLevel = Level.INFO;
    return this;
  }

  public Logger error() {
    this.currentLevel = Level.ERROR;
    return this;
  }

  public Logger trace() {
    this.currentLevel = Level.TRACE;
    return this;
  }

  public Logger token(String key, String value) {
    String message = String.format("%s {%s}", key, value);
    messageList.add(message);
    return this;
  }

  public Logger object(String key, Object object) {
    String objectAsString;
    try {
      objectAsString = objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      objectAsString = "Error deserializing the object";
    }
    String message = String.format("%s {%s}", key, objectAsString);
    messageList.add(message);
    return this;
  }

  public void end() {
    String message = String.join("-", messageList);
    switch (currentLevel) {
    case INFO:
      slf4jlogger.info(message);
      break;
    case ERROR:
      slf4jlogger.error(message);
      break;
    case TRACE:
      slf4jlogger.trace(message);
      break;
    case WARN:
      slf4jlogger.warn(message);
      break;
    default:
      throw new IllegalArgumentException("Log level has not been set.");
    }
    clear();
  }

  private void clear() {
    this.messageList = new ArrayList<>();
    this.currentLevel = Level.UNDEF;
  }

  enum Level {
    TRACE, INFO, WARN, ERROR, UNDEF
  }

}
