package com.example.Musiclist.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Musiclist.domain.SignupForm;
import com.example.Musiclist.domain.User;
import com.example.Musiclist.domain.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userepo;
	
	//luodaan uusi käyttäjä
	@RequestMapping(value = "/signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}
	
	//tallennetaan uusi käyttäjä userrepositoryyn, kun se täyttää tietyt ehdot
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult br) {
		if(!br.hasErrors()) { //tarkistetaan ettei ole erroreita, ennen kuin voidaan jatkaa
			if(signupForm.getPassword().equals(signupForm.getPasswordCheck())) { //tarkistetaan, onko salasana kirjoitettu oikein
				String pswd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPswd = bc.encode(pswd);
				
				User newUser = new User();
				newUser.setUsername(signupForm.getUsername());
				newUser.setPasswordHash(hashPswd);
				newUser.setEmail(signupForm.getEmail());
				newUser.setRole("USER");
				if (userepo.findByUsername(signupForm.getUsername()) == null && userepo.findByEmail(signupForm.getEmail()) == null) {
					userepo.save(newUser); //varmistamisten jälkeen uusi käyttäjä luodaan ja tallennetaan userrepositoryyn
				}
				else {
					br.rejectValue("username", "err.username", "Username already exists!");
					br.rejectValue("email", "err.email", "Email already exists!");
					return "signup"; //palaa takaisin tunnuksen luomiseen virheilmoitusten kera
					}
				}
				else {
					br.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match!");
					return "signup"; //palaa takaisin tunnuksen luomiseen, jos salasanat eivät mätsää
				}
			}
			else  {
				return "signup"; //palaa takaisin tunnuksen luomiseen, jos alussa tuli virheitä(salasana/käyttäjä liaan lyhyt jne.)
			}
			return "redirect:/login"; //onnistumisen merkiksi luo uuden käyttäjän ja palauttaa käyttäjän login välilehdelle
		
		}
	}

