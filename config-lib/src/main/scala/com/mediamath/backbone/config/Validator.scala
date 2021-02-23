package com.mediamath.backbone.config

trait Validator {

  /**
   * Convenience method that, given a predicate and a message, returns a ValidationError
   * if the predicate was not satisfied
   *
   * @param p    predicate to validate
   * @param msg  error message
   * @return     optional validation error
   */
  def validate(p: Boolean, msg: String): Option[String] = if (p) None else Some(msg)

  /**
   * Convenience method that combines a number of optional validation errors into a flattened
   * list of validation errors
   *
   * @param errors optional validation errors
   * @return       collection of errors
   */
  def combine(errors: Option[String]*): Seq[String] = errors.toSeq.flatten
}
