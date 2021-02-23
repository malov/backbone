package com.mediamath.backbone.examples.config

import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._
import org.apache.spark.SparkConf

import com.mediamath.backbone.config.Blueprint
import com.mediamath.backbone.examples.config.action.Filter
import com.mediamath.backbone.examples.config.kafka.{Get, Kafka}
import com.mediamath.backbone.examples.config.session.Session

/** The root of the configuration hierarchy.
  *
  * All config is represented as simple case classes that have no
  * knowledge of the Typesafe config library.  They simply contain state.
  *
  * If new entity is created inside application config, additional parameter need to be
  * added to this class, representing corresponding type. This type has to be expressed as
  * a case class with corresponding build method.
  *
  * If it is desirable, a config object can know whether or not it is valid by extending
  * `Validatable` and implementing the `validate` method.
  *
  * @param session  settings for spark session for the app
  * @param kafka    common kafka settings
  * @param get      settings for "get from kafka" action, inherits "kafka" with possible override
  * @param filter   settings for "filter out UUIDs from delete requests"
  * @param spark    Spark settings
  */
case class ApplicationConfiguration(
  session:Session,
  kafka:Kafka,
  get:Get,
  filter:Filter,
  spark:SparkConf
) extends Blueprint

object ApplicationConfiguration {

  def apply(cfg:Config):ApplicationConfiguration = {
    val sparkConfig = cfg.as[Map[String, String]](ConfigNames.Spark)
    val sc = {
      var res = new SparkConf()
      for ((key, value) <- sparkConfig) res = res.set(key, value)
      res
    }
    val appConfig = cfg.getConfig(ConfigNames.Main)

    ApplicationConfiguration(
      session = Session( appConfig.getConfig( ConfigNames.Application.Session) ),
      kafka = Kafka( appConfig.getConfig(ConfigNames.Application.Kafka) ),
      filter = Filter( appConfig.getConfig(ConfigNames.Application.Filter) ),
      get = Get( appConfig.getConfig(ConfigNames.Application.Get) ),
      spark = sc
    )
  }
}
