package com.mediamath.backbone.examples.config.action

import com.typesafe.config.Config

import com.mediamath.backbone.examples.config.ConfigNames

/** Configuration settings for Filter action
  *
  * @param dataset  dataset settings
  * @param services service(s) to be cleaned
  */
case class Filter(
  override val dataset:Dataset,
  override val services:Seq[Service]
) extends Action

object Filter {
  import scala.collection.JavaConversions._

  def apply(cfg:Config):Filter = Filter(
    dataset = Dataset( cfg.getConfig(ConfigNames.Action.Dataset) ),
    services = cfg.getConfigList(ConfigNames.Action.Services).map( Service(_) )
  )
}
