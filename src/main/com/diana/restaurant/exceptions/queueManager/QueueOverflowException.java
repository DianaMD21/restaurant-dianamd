package com.diana.restaurant.exceptions.queueManager;

public class QueueOverflowException extends RuntimeException {
  public QueueOverflowException(Long maxCapacity, String queue) {
    super(queue + " reached max capacity of " + maxCapacity);
  }
}
