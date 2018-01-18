package test;

import java.io.IOException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import api.user.PostUser;
import pojo.PostUserRequest;
import utils.PropertiesManager;

/**
 * Created by kohlih on 12-11-2017.
 */
public class TestCreateUser extends BaseTest {

	public static final Logger logger = Logger.getLogger(TestCreateUser.class);

	@Test(groups = { "Regression" })
	public void createUser() throws IOException {
		
		PostUserRequest postUserRequest = new PostUserRequest();
		postUserRequest.setId(new Random().nextInt(Integer.MAX_VALUE));
		postUserRequest.setUsername(PropertiesManager.getProperty("username"));
		postUserRequest.setFirstName(PropertiesManager.getProperty("firstname"));
		postUserRequest.setLastName(PropertiesManager.getProperty("lastname"));
		postUserRequest.setEmail(PropertiesManager.getProperty("email"));
		postUserRequest.setPassword(PropertiesManager.getProperty("password"));
		postUserRequest.setPhone(PropertiesManager.getProperty("phone"));
		postUserRequest.setUserStatus(new Random().nextInt(Integer.MAX_VALUE));

		PostUser postUser = new PostUser(PropertiesManager.getProperty("baseURI"));
		postUser.setPostUserRequestBody(postUserRequest);
		postUser.setExpectedStatusCode(200);

		postUser.perform();
		logger.info("User created successfully");
	}
}