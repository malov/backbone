package com.mediamath.backbone.examples.config.action

import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

import com.mediamath.backbone.config.Blueprint
import com.mediamath.backbone.examples.config.ConfigNames

/** Configuration settings for dataset
  *
  * @param sourcePath where to read it from, if previous action saved it to file system
  * @param sinkPath   where to write it to in the "sink"
  */
case class Dataset(sourcePath:String, sinkPath:String) extends Blueprint

object Dataset {
  def apply(cfg:Config):Dataset = Dataset(
    sourcePath = cfg.as[String](ConfigNames.Dataset.SourcePath),
    sinkPath = cfg.as[String](ConfigNames.Dataset.SinkPath)
  )
}

