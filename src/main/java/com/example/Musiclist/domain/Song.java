package com.example.Musiclist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Song {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String artist;
	private String songname;
	private double length;
	//any song link
	private String link;
	
	public Song() {
		
	}

	public Song(String artist, String songname, double length, String link) {
		super();
		this.artist = artist;
		this.songname = songname;
		this.length = length;
		this.link = link;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", artist=" + artist + ", songname="
				+ songname + ", length=" + length + ", link=" + link + "]";
	}
	
	
}
