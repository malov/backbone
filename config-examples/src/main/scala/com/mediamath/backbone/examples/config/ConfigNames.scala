package com.mediamath.backbone.examples.config

/** Convenience object holding all configuration parameter names in use */
object ConfigNames {
  val Main = "application"
  val Spark = "spark"

  object Application {
    val Session = "session"
    val Get = "get"
    val Filter = "filter"
    val Kafka = "kafka"
  }
  object Session {
    val Master = "master"
    val Kryo = "kryo-registered-classes"
  }
  object Action {
    val Dataset = "dataset"
    val Services = "services"
  }
  object Dataset {
    val SourcePath = "source-path"
    val SinkPath = "sink-path"
  }
  object Service {
    val Name = "name"
    val ReadPath = "read-path"
    val WritePath = "write-path"
  }
  object Kafka {
    val BrokerList = "broker-list"
    val DefaultTopic = "default-topic"
    val RegistryUrl = "registry-url"
    val GroupId = "group-id"
    val RespondingSystem = "responding-system"
    val KafkaFailureThreshold = "kafka-failure-threshold"
  }
}
