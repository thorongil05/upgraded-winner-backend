package it.upgraded.winner.utils.logging;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_SELF;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;

public class LoggerFactoryMockProvider {

  public static LoggerFactory provide() {
    LoggerFactory loggerFactory = Mockito.mock(LoggerFactory.class);
    Logger logger = Mockito.mock(Logger.class, RETURNS_SELF);
    when(loggerFactory.getLogger(any())).thenReturn(logger);
    return loggerFactory;
  }

}
