package com.mediamath.backbone.config

import java.time.LocalDate

import scala.util.{Failure, Success, Try}


object GenericValidator extends Validator {

  def dateValidator(date:String):Option[String] = {
    def isValidDate:Boolean = Try {
      LocalDate.parse(date)
    }
    match {
      case Success(_) => true
      case Failure(_) => false
    }

    validate(isValidDate,s"Date $date failed to parse")
  }
}
