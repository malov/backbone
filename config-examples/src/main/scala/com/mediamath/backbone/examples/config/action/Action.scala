package com.mediamath.backbone.examples.config.action

import com.mediamath.backbone.config.Blueprint

/** Common trait for all action("blade") configuration entities */
trait Action extends Blueprint {
  def dataset:Dataset

  def services:Seq[Service] = Seq.empty[Service]
}
