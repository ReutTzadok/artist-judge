package com.epam.services

import org.springframework.stereotype.Component

@Component
class CompareSongsImpl(musicService :MusicService) extends CompareSongsService {

  override def compare(artist1: String, artist2: String, x: Int): List[String] = {
    val artist1topx: Map[String, Int] = musicService.topXWithRate(artist1, x)
    val artist2topx: Map[String, Int] = musicService.topXWithRate(artist2, x)

    artist1topx.keys
      .filter(word => artist2topx.contains(word))
      .toList
  }
}
