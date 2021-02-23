package com.mediamath.backbone.config

/**
 * Validator for walking though an object hierarchy and validating all child components,
 * where possible
 */
object ConfigValidator {

  /**
   * Get the children for a given object
   *
   * - for case classes, return a list of their products (i.e. constructor arguments)
   * - for Maps, return a list of their values (ignoring the keys)
   * - for Traversables, return themselves
   * - for Arrays, return a list of the contained values
   * - everything is assumed to have no kids
   *
   * @param obj  configuration object
   * @return     configuration-like children of the main configuration object
   */
  private def childrenFor(obj: Any): Seq[Any] = {
    obj match {

      // Supported types with kids
      // NOTE: order is very important, e.g. Seq is a Product as well as a Traversable
      case m: Map[_, _] => m.values.toSeq
      case t: Traversable[_] => t.toSeq
      case p: Product => p.productIterator.toSeq
      case a: Array[_] => a.toSeq

      // Assume all others have no kids
      case _ => Seq.empty
    }
  }

  /**
   * Flatten an object hierarchy into a list of objects + their children.
   *
   * The children for a given node are returned by `childrenFor`: see that
   * function for details of the traversal.
   *
   * @param root   root of hierarchy
   * @return       collection of objects to validate
   */
  private def flattenHierarchy(root: Any): Seq[Any] = {
    root +: childrenFor(root).flatMap(flattenHierarchy)
  }

  /**
   * Get the validation errors for a given config root
   *
   * @param root root of configuration hierarchy
   * @return     collection of validation errors
   */
  def validateConfig(root: Any): Seq[String] = {
    flattenHierarchy(root)
      .filter(_.isInstanceOf[Blueprint])
      .asInstanceOf[Seq[Blueprint]]
      .flatMap(_.validate)
  }
}
