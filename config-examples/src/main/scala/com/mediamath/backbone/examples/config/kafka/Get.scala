package com.mediamath.backbone.examples.config.kafka

import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._
import org.apache.kafka.clients.consumer.ConsumerConfig

import com.mediamath.backbone.examples.config.ConfigNames
import com.mediamath.backbone.examples.config.action.{Action, Dataset}

/** Configuration settings for Get from Kafka blade
  *
  * @param brokerList             Kafka broker list
  * @param defaultTopic           Kafka default topic
  * @param registry               registry location for the schema
  * @param groupId                Kafka group id
  * @param dataset                dataset settings
  */
case class Get(
  override val brokerList:Seq[String],
  override val defaultTopic:String,
  override val registry:String,
  override val groupId:String,
  override val dataset:Dataset
) extends Kafka(brokerList, defaultTopic, registry, groupId) with Action {

  /** Convenience method to configure Kafka consumer
    *
    * @return properties map for Kafka consumer configuration
    */
  override def props:java.util.HashMap[String, Object] = {
    val props = new java.util.HashMap[String, Object]()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList.mkString(","))
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.ByteArrayDeserializer")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", groupId)
    props.put("auto.offset.reset", "earliest")
    props
  }
}

object Get {
  def apply(cfg:Config):Get = Get(
    brokerList = cfg.as[Array[String]](ConfigNames.Kafka.BrokerList),
    defaultTopic = cfg.as[String](ConfigNames.Kafka.DefaultTopic),
    registry = cfg.as[String](ConfigNames.Kafka.RegistryUrl),
    groupId = cfg.as[String](ConfigNames.Kafka.GroupId),
    dataset = Dataset( cfg.getConfig(ConfigNames.Action.Dataset) )
  )
}
