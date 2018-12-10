package com.example.Musiclist;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Musiclist.domain.Song;
import com.example.Musiclist.domain.SongRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {

	@Autowired
	private SongRepository songrepo;
	
	 /*
     * artist, songname, length, link
     * string, string, double, string
     */
	
	@Test //testataan toimiiko uuden biisin lisäys listalle
	public void addNewSong() {
		
		Song song = new Song("TestArtist1", "TestSong1", 20, "aewdadawdadaw");
		songrepo.save(song);
		
		assertThat(song.getId()).isNotNull();
		assertThat(song.getArtist()).isEqualTo("TestArtist1");
		
	}
	
	@Test //testataan toimiiko biisin poistaminen listalta
	public void deleteNewSong() {
		
		Song song = new Song("TestArtist2", "TestSong2", 10, "dadadadadad");
		songrepo.save(song);
		
		List<Song> songs = songrepo.findBySongname("TestSong2");
		songrepo.delete(songs.get(0));
		List<Song> newSongList = songrepo.findBySongname("TestSong2");
		
		assertThat(newSongList).hasSize(0);
	}
	
	@Test //testataan pystytäänkö etsimään tiettyä biisiä listalta
	public void searchNewSong() {
		List<Song> songs = songrepo.findBySongname("Without Me");
		
		assertThat(songs).hasSize(1);
		assertThat(songs.get(0).getArtist()).isEqualTo("Eminem");
	}
	
}
