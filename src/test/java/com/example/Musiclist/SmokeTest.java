package com.example.Musiclist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Musiclist.web.SongController;
import com.example.Musiclist.web.UserController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
	
	@Autowired
	private SongController songC;
	
	@Autowired
	private UserController userC;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(songC).isNotNull();
		assertThat(userC).isNotNull();

	}

}
