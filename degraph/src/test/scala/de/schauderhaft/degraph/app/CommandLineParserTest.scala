package de.schauderhaft.degraph.app

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CommandLineParserTest extends FunSuite {
  import org.scalatest.Matchers._

  test("default outputfile is 'output.graphml'") {
    val config = CommandLineParser.parse(Array[String]())
    config.initialize { case e => }
    config.output() should be("output.graphml")
  }

  test("the string after -o is considered the output file name") {
    val config = CommandLineParser.parse(Array("-o", "ExampleFile"))
    config.initialize { case e => }
    config.output() should be("ExampleFile")
  }

  test("default input is empty") {
    val config = CommandLineParser.parse(Array[String]())
    config.initialize { case e => }
    config.classpath() should be(".")
  }

  test("the string after -c is considered the input classpath") {
    val config = CommandLineParser.parse(Array("-c", "input;blah.jar"))
    config.initialize { case e => }
    config.classpath() should be("input;blah.jar")
  }

  test("default exclude filter is empty") {
    val config = CommandLineParser.parse(Array[String]())
    config.initialize { case e => }
    config.excludeFilter() should be(List())
  }
  test("the strings after -e s are considered the exclude filter") {
    val config = CommandLineParser.parse(Array("-e", "filter"))
    config.initialize { case e => }
    config.excludeFilter() should be(List("filter"))
  }

  test("default include filter is empty") {
    val config = CommandLineParser.parse(Array[String]())
    config.initialize { case e => }
    config.includeFilter() should be(List())
  }
  test("the strings after -i s are considered the exclude filter") {
    val config = CommandLineParser.parse(Array[String]("-i", "filter"))
    config.initialize { case e => }
    config.includeFilter() should be(List("filter"))
  }

  test("the strings after -f  are considered the configurationFile") {
    val config = CommandLineParser.parse(Array[String]("-f", "file"))
    config.initialize { case e => }
    config.file.get should be(Some("file"))
  }

}