package com.mediamath.backbone.config

import java.io.File
import java.nio.file.Path

/** Validator for file-based assertions, e.g. existence, writability, type */
object FileSystemValidator extends Validator {

  /**
   * Validate that file exists
   *
   * @param name file name
   * @return     validation errors, if any
   */
  def fileExists(name: String): Option[String] =
    validate(new File(name).exists(), s"Required file ${name} is missing")

  /**
   * Validate that file exists
   *
   * @param name path to file
   * @return     validation errors, if any
   */
  def fileExists(name: Path): Option[String] =
    validate(name.toFile.exists(), s"Required file ${name} is missing")

  /**
   * Validate that directory exists
   *
   * @param name directory name
   * @return     validation errors, if any
   */
  def directoryExists(name: String): Option[String] =
    validate(new File(name).isDirectory, s"Required directory ${name} is missing")

  /**
   * Validate that directory is writable
   *
   * @param name directory name
   * @return     validation errors, if any
   */
  def directoryIsWritable(name: String): Seq[String] = combine(
    directoryExists(name),
    validate(new File(name).canWrite, s"Required directory ${name} is not writable"))
}
