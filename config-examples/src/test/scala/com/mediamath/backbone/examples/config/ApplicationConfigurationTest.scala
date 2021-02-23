package com.mediamath.backbone.examples.config

import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.ProducerConfig
import org.scalatest.{FreeSpec, Matchers}

class ApplicationConfigurationTest extends FreeSpec with Matchers {

  "Configuration should" - {
    "for valid configuration" - {
      val config = ApplicationConfiguration( ConfigFactory.load("application.config.conf") )

      "be correctly loaded" in {
        config.spark.getAll.length should be(2)
        config.spark.get("spark.sql.parquet.binaryAsString") should be("true")

        config.session.master should be("local")

        config.kafka.brokerList.size should be(2)
        config.kafka.brokerList shouldEqual config.get.brokerList
        config.get.props.size should be(5)
        config.get.props.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG) shouldEqual config.kafka.brokerList.mkString(",")

        config.filter.services.size should be(2)
        config.filter.dataset.sourcePath shouldEqual "/filter/source"
        config.filter.dataset.sinkPath shouldEqual "/filter/sink"
      }
      "be validated as correct" in { pending }
    }
    "for invalid configuration" - {
      "fail validation" in { pending }
    }
  }
}
