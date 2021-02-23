package com.mediamath.backbone.examples.config.action

import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

import com.mediamath.backbone.config.Blueprint
import com.mediamath.backbone.examples.config.ConfigNames

/** Service to be cleaned configuration settings
  *
  * @param name       service name
  * @param readPath   path where to read the data to be cleaned
  * @param writePath  path where to write cleaned data
  */
case class Service(name:String, readPath:Seq[String], writePath:Seq[String]) extends Blueprint

object Service {
  def apply(cfg:Config):Service = Service(
    name = cfg.as[String](ConfigNames.Service.Name),
    readPath = cfg.as[Seq[String]](ConfigNames.Service.ReadPath),
    writePath = cfg.as[Seq[String]](ConfigNames.Service.WritePath)
  )
}
