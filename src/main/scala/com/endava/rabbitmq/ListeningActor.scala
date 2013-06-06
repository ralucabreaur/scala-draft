package com.endava.rabbitmq

import akka.actor._
import com.rabbitmq.client.Channel
import com.rabbitmq.client.QueueingConsumer

class ListeningActor(channel: Channel, queue: String, f: (String) => Any) extends Actor {

  def receive = {
    case _ => startReceiving
  }

  def startReceiving = {

    println("ListenerActor in action...")
    val consumer = new QueueingConsumer(channel);
    channel.basicConsume(queue, true, consumer);

    while (true) {
      val delivery = consumer.nextDelivery();
      val msg = new String(delivery.getBody());

      // send message to the callback
      context.actorOf(Props(new Actor {
        def receive = {
          case some: String => f(some);
        }
      })) ! msg
    }
  }
}