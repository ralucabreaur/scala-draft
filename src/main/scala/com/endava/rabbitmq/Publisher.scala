package com.endava.rabbitmq

import com.rabbitmq.client.Channel
import akka.actor._
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext.Implicits.global

object Publisher {

  def startSending() {

    println("Publisher: start sending...")
    val connection = RabbitMqConnection.establishConnection()
    val sendingChannel = connection.createChannel()

    sendingChannel.exchangeDeclare("exchangeName","fanout")
    println("Channel used for sending messages: " + sendingChannel.toString)

    ActorSystem.create().scheduler.schedule(
      Duration.create(3, TimeUnit.SECONDS),
      Duration.create(1, TimeUnit.SECONDS),
      ActorSystem.create().actorOf(Props(new PublishingActor(channel = sendingChannel, exchange = "exchangeName"))),
      "MSG to Exchange")

    // define callback for our listener
    val callback = (x: String) => println("Received on exchange callback: " + x)
    val listenChannel = connection.createChannel()
    setupListener(listenChannel, listenChannel.queueDeclare().getQueue,"exchangeName", callback)

  }

  private def setupListener(channel: Channel, queueName: String, exchange: String, f: (String) => Any) {
    channel.queueBind(queueName, exchange, "")
    println("Listener set for queue: " + queueName)
    ActorSystem.create().scheduler.scheduleOnce(
      Duration.create(2, TimeUnit.SECONDS),
      ActorSystem.create().actorOf(Props(new ListeningActor(channel, queueName, f))),
      " ")
  }
}

class PublishingActor(channel: Channel,exchange: String) extends Actor {
  val msg = "Hello world"

  def receive = {
    case some: String => {
      channel.basicPublish(exchange, " ", null, msg.getBytes)
    }
      println("Published message: "+ msg)

    case _ => {}
  }
}

