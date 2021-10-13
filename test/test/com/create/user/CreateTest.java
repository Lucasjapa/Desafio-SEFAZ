package test.com.create.user;

import org.junit.jupiter.api.Test;
import com.sefaz.controller.UserServlet;
import com.sefaz.dao.UserDao;
import com.sefaz.model.User;

public class CreateTest {

	private UserDao	userDao;
	
	@Test
	public void create() {
		try {
			User user = new User();
			user.setName("");
			user.setEmail("");
			user.setPassword("");
			
			userDao.save(user);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
