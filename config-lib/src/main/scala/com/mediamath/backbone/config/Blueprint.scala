package com.mediamath.backbone.config

import com.typesafe.scalalogging.LazyLogging

/** Base trait for all configuration objects */
trait Blueprint extends LazyLogging {

  /**
   * Validate configuration parameters and collect errors, if any
   *
   * @return collection of errors
   */
  def validate: Seq[String] = Seq.empty[String]

  /** Print configuration settings for element */
  def print(): Unit = ()
}
