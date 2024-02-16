package com.bibernate.example.exception;

/**
 * Exception thrown when opening a session fails.
 */
public class SessionOpenException extends RuntimeException {

  /**
   * Constructs a {@code SessionOpenException} with the specified detail message.
   *
   * @param message the detail message.
   */
  public SessionOpenException(String message) {
    super(message);
  }

  /**
   * Constructs a {@code SessionOpenException} with the specified detail message and cause.
   *
   * @param message the detail message.
   * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()}
   *                method).
   */
  public SessionOpenException(String message, Throwable cause) {
    super(message, cause);
  }
}
