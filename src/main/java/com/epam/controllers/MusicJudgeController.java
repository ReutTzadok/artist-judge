package com.epam.controllers;

import com.epam.services.CompareSongsImpl;
import com.epam.services.CompareSongsService;
import com.epam.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MusicJudgeController {
    @Autowired
    private MusicService musicService;

    @Autowired
    private CompareSongsService compareSongs;

    @GetMapping("/music/topx/{artist}/{x}")
    public Map<String, Integer> topX(@PathVariable String artist, @PathVariable int x) {
        scala.collection.immutable.Map<String, Object> scalaMap = musicService.topXWithRate(artist, x);
        Map<String, Integer> map = new HashMap<>();

        scalaMap.foreach(v1 -> map.put(v1._1, (Integer) v1._2));
        return map;
    }

    @GetMapping("/music/compare/{artist1}/{artist2}/{x}")
    public List<String> compareSongs(@PathVariable String artist1, @PathVariable String artist2, @PathVariable int x) {
        List<String> list = new ArrayList<>();
        scala.collection.immutable.List<String> words = compareSongs.compare(artist1, artist2, x);

        words.foreach(list::add);
        return list;
    }
}












