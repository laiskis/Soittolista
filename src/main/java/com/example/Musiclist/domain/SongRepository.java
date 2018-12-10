package com.example.Musiclist.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//SongRepo for finding all songs
public interface SongRepository extends CrudRepository<Song, Long> {

	List<Song> findBySongname(String song);
}
