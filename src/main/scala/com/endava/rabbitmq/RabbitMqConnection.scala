package com.endava.rabbitmq

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

object RabbitMqConnection {

  private val connection: Connection = null;

  def establishConnection(): Connection = {
    connection match {
      case null => {
        println("Connection to RabbitMQ established...")
        val factoryConnection = new ConnectionFactory();
        factoryConnection.setHost("localhost")
        factoryConnection.newConnection()
      }

      case _ => connection

    }
  }

}