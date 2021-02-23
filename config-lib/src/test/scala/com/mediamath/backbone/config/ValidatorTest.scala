package com.mediamath.backbone.config

import org.scalatest.{FreeSpec, Matchers}

class ValidatorTest extends FreeSpec with Matchers {

  private lazy val validator = new Validator {}

  "Validator should" - {
    "validate" - {
      "generates no message if valid" in {
        validator.validate(true, "a message") should be(None)
      }
      "generates a suitable message if invalid" in {
        validator.validate(false, "a message") should be(Some(s"a message"))
      }
    }
    "combine" in {
      val a = "a"
      val b = "b"
      val c = "c"
      validator.combine(Some(a), None, Some(b), None, None, Some(c), None) should be(Seq(a, b, c))
    }
  }
}
