package com.epam.services

trait CompareSongsService {
  def compare(artist1: String, artist2: String, x:Int) : List[String]

}
