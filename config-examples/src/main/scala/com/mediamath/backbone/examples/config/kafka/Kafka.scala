package com.mediamath.backbone.examples.config.kafka

import java.io.Serializable

import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

import com.mediamath.backbone.config.Blueprint
import com.mediamath.backbone.examples.config.ConfigNames

class Kafka(
  val brokerList:Seq[String],
  val defaultTopic:String,
  val registry:String,
  val groupId: String
) extends Blueprint with Serializable {

  def props:java.util.HashMap[String, Object] = new java.util.HashMap[String, Object]()
}

object Kafka {
  def apply(cfg:Config):Kafka = new Kafka(
    brokerList = cfg.as[Seq[String]](ConfigNames.Kafka.BrokerList),
    defaultTopic = cfg.as[String](ConfigNames.Kafka.DefaultTopic),
    registry = cfg.as[String](ConfigNames.Kafka.RegistryUrl),
    groupId = cfg.as[String](ConfigNames.Kafka.GroupId)
  )
}
