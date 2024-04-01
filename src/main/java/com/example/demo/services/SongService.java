package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Song;

@Service
public interface SongService {

	public void addSong(Song song);
	
	public List<Song> fetchAllSongs();

	public boolean songExists(String name);
	

	public void updateSong(Song song);

}
