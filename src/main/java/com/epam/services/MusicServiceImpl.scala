package com.epam.services

import com.epam.repo.{BlackList, WordsRepo}
import com.epam.utils.WordsUtil
import org.springframework.stereotype.Component

import scala.collection.JavaConverters.asScalaBufferConverter


@Component
class MusicServiceImpl(wordsRepo: WordsRepo, blackWords: BlackList) extends MusicService {

  override def topXWithRate(musicianName: String, x: Int): Map[String, Int] = {
    val blackList = blackWords.ignore()
    wordsRepo.allLines(musicianName)
      .map(_.toLowerCase())
      .flatMap(line => WordsUtil.getWords(line).asScala)
      .map((_, 1))
      .reduceByKey(_ + _)
      .filter(word => !blackList.contains(word._1))
      .map(_.swap)
      .sortByKey(ascending = false)
      .map(_.swap)
      .take(x)
      .toMap
  }

  override def topX(musicianName: String, x: Int): List[String] = {
    topXWithRate(musicianName, x).map(_._1).toList
  }
}
