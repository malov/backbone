package com.mediamath.backbone.examples.config.session

import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

import com.mediamath.backbone.config.Blueprint
import com.mediamath.backbone.examples.config.ConfigNames

case class Session(master: String, kryoClasses:Seq[String]) extends Blueprint

object Session {
  def apply(cfg: Config): Session = Session(
    master = cfg.as[String](ConfigNames.Session.Master),
    kryoClasses = cfg.as[Seq[String]](ConfigNames.Session.Kryo)
  )
}
