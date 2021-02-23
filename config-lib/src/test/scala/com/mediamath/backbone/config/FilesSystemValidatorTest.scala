package com.mediamath.backbone.config

import java.io.File

import org.scalatest.{FreeSpec, Matchers}

class FilesSystemValidatorTest extends FreeSpec with Matchers {

  private def createTempFile(): File = {
    val result = File.createTempFile("foo", "bar")
    result.deleteOnExit()
    result
  }

  private def createTempDir(): File = {
    val result = createTempFile()
    result.delete()
    result.mkdir()
    result
  }

  "File system validator should" - {

    "testing fileExists" - {
      "returns None when file exists" in {
        val file = createTempFile()
        FileSystemValidator.fileExists(file.getAbsolutePath) should be(None)
      }
      "returns Some(x) when file does not exist" in {
        val file = createTempFile()
        file.delete()
        FileSystemValidator.fileExists(file.getAbsolutePath) should not be (None)
      }
    }

    "testing directoryExists" - {
      "returns None when file exists" in {
        val dir = createTempDir()
        FileSystemValidator.directoryExists(dir.getAbsolutePath) should be(None)
      }
      "returns Some(x) when file does not exist" in {
        val dir = createTempDir()
        dir.delete()
        FileSystemValidator.directoryExists(dir.getAbsolutePath) should not be (None)
      }
    }

    "testing directoryIsWritable" - {
      "returns no messages when file is writable" in {
        val dir = createTempDir()
        FileSystemValidator.directoryIsWritable(dir.getAbsolutePath) should be(empty)
      }
    }
  }
}
