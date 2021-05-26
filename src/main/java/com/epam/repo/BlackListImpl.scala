package com.epam.repo

import com.epam.utils.WordsUtil
import org.apache.spark.SparkContext
import org.springframework.stereotype.Component

@Component
class BlackListImpl(sc:SparkContext) extends BlackList {
  override def ignore(): List[String] = {
    sc.textFile("data/blackWords.txt")
      .map(_.toLowerCase())
      .flatMap(line => line.split(" "))
      .collect()
      .toList
  }
}
