package com.example.Musiclist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Musiclist.domain.Song;
import com.example.Musiclist.domain.SongRepository;

@Controller
public class SongController {

	@Autowired
	private SongRepository songrepo;
	
	public Song findSongById(long id) {
		return songrepo.findById(id).get();
	}
	
	//sisäänkirjautuminen soittolistalle
	@RequestMapping(value="/login")
	 public String login() {
		return "login";
	  }
	
	//tämän hetkinen soittolista
	@RequestMapping(value="/songlist")
	 public String songlist(Model model) {
		model.addAttribute("songs", songrepo.findAll());
		return "songlist";
	}
	
	//päästään täyttämään kaavake, joka voidaan toisella methodilla tallentaa
	@RequestMapping(value="/add")
	 public String addSong(Model model) {
		model.addAttribute("song", new Song());
		return "addsong";
	}
	
	//tallennetaan uusi biisi, toimii keskenään "/add" kanssa 
	@RequestMapping(value="/save", method = RequestMethod.POST)
	 public String saveSong(Song song) {
		songrepo.save(song);
		return "redirect:songlist";
	}
	
	 //poistetaan jo valmiiksi listalla oleva biisi
	 @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	  public String deleteSong(@PathVariable("id") Long songId, Model model) {
		 songrepo.deleteById(songId);
		 return "redirect:../songlist";
	  }
	  
	 /* editoidaan jo valmista listalla olevaa biisiä (ongelmia saada sama ulkoasu "addsong",
	 * joten jouduin tekemään oman templaten "editsong". mistä lie johtui?
	 */
	 @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	  public String editSong(@PathVariable("id") Long songId, Model model) {
		 model.addAttribute("song", songrepo.findById(songId));
		 return "editsong";
	  }
	  
	  // RESTful service to get all books
	  @RequestMapping(value="/songs", method = RequestMethod.GET)
	    public @ResponseBody List<Song> songListRest() {	
	        return (List<Song>) songrepo.findAll();
	  }   
	  
	  //RESTful service to get book by id
	  @RequestMapping(value="/song/{id}", method = RequestMethod.GET)
	  	public @ResponseBody Optional<Song> findSong(@PathVariable("id") Long songId) {
		  return songrepo.findById(songId);
	  }
}
