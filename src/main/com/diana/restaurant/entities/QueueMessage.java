package com.diana.restaurant.entities;

public class QueueMessage<T> {
  private Long priority;
  private T data;
  private String queue;

  public QueueMessage() {}

  public Long getPriority() {
    return priority;
  }

  public void setPriority(Long priority) {
    this.priority = priority;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getQueue() {
    return queue;
  }

  public void setQueue(String queue) {
    this.queue = queue;
  }

  public Long compareTo(QueueMessage<T> entity) {
    return (long) Long.compare(this.priority, entity.priority);
  }
}
