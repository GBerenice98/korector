package miage.korector.login;

import com.projet.korector.entity.User;
import com.projet.korector.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoginApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	UserRepository repository;
	private User user;
	@BeforeEach
	void setUp() {
		user= new User("adiagne","Awa","diagne",
				"adiagne@live.fr","adiagne97");
	}

	@AfterEach
	void tearDown() {
		user=null;
	}

	@Test
	void save()
	{
		User user1 =this.repository.save(this.user);
		System.out.println("Test BDD  : "+this.repository.findAll());
		assertEquals(user1.getUserName(),1);
	}

}
