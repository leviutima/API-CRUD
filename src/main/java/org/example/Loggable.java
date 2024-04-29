package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public interface Loggable<T> {
    Logger LOGGER = LogManager.getLogger(Loggable.class);

    default void logInfo(T message) {
        LOGGER.info(message);
    }

    default void logError(T message) {
        LOGGER.error(message);
    }

    default void logDebug(T message) {
        LOGGER.debug(message);
    }
}
