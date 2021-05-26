package com.epam.services

trait MusicService {

  def topXWithRate(musicianName: String, x: Int): Map[String, Int]
  def topX(musicianName: String, x: Int):List[String]
}
