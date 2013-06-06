package com.endava.rabbitmq

import com.sun.xml.internal.ws.resources.WsservletMessages

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

object Main {


  def main(args: Array[String]) {

    println("start sending.....")
    Publisher.startSending

  }
}
