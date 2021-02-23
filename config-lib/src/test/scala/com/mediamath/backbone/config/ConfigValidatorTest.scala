package com.mediamath.backbone.config

import org.scalatest.{FreeSpec, Matchers}

class ConfigValidatorTest extends FreeSpec with Matchers {

  private def validConfig(): Blueprint = {
    new Blueprint {
      override def validate: Seq[String] = super.validate
    }
  }

  private def invalidConfig(msg: String): Blueprint = {
    new Blueprint {
      override def validate: Seq[String] = Seq(msg)
    }
  }

  "Config validator" - {
    "returns no errors on empty config object" in {
      ConfigValidator.validateConfig({}) should be(empty)
    }
    "returns no errors if all contained configs are valid" in {
      ConfigValidator.validateConfig(Seq(
        validConfig(),
        validConfig(),
        validConfig())) should be(empty)
    }
    "returns messages for all invalid configs" in {
      ConfigValidator.validateConfig(Seq(
        invalidConfig("foo"),
        validConfig(),
        invalidConfig("bar"),
        invalidConfig("baz"),
        validConfig())) should be(Seq("foo", "bar", "baz"))
    }
  }
}
