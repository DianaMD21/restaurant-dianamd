package com.diana.restaurant.queue;

import com.diana.restaurant.entities.Order;
import com.diana.restaurant.entities.QueueMessage;
import com.diana.restaurant.enums.Queues;
import com.diana.restaurant.exceptions.KeyNotFoundException;
import com.diana.restaurant.exceptions.queueManager.QueueOverflowException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class QueueManager {
  private QueueManager queueManager;
  private final Map<Queues, Queue> instanceMap = new ConcurrentHashMap<>();

  public QueueManager(QueueManager queueManager) {
    this.queueManager = queueManager;
  }

  private void registerQueues(Map<Queues, Queue> instanceMap) {
    instanceMap.putIfAbsent(
        Queues.CHECK_INGREDIENTS_QUEUE, new PriorityQueue<QueueMessage<Order>>());
  }

  private <T> T get(String key) {
    Optional.ofNullable(key).orElseThrow(NullPointerException::new);
    return (T)
        Optional.ofNullable(instanceMap.get(key))
            .orElseThrow(() -> new KeyNotFoundException("Queue"));
  }

  private void enQueue(String queueName, QueueMessage entity) {
    var queue =
        (PriorityQueue)
            Optional.ofNullable(this.get(queueName))
                .orElseThrow(() -> new KeyNotFoundException("Queue"));
    Optional.of(queue)
        .filter(q -> q.size() <= Queues.MAX_QUEUE_CAPACITY)
        .orElseThrow(
            () ->
                new QueueOverflowException(
                    Queues.MAX_QUEUE_CAPACITY, Queues.CHECK_INGREDIENTS_QUEUE.toString()));
    Optional.ofNullable(entity).orElseThrow(NullEntityException::new);
    queue.add(entity);
  }

  private void deQueue(String queueName, QueueMessage entity) {
    var queue =
        (PriorityQueue)
            Optional.ofNullable(this.get(queueName))
                .orElseThrow(() -> new KeyNotFoundException("Queue"));
    Optional.ofNullable(entity).orElseThrow(NullEntityException::new);
    queue.remove(entity);
  }
}
