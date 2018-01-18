package test;

import java.io.IOException;

import org.testng.annotations.Test;

import api.user.GetLoginUser;
import utils.CSVAnnotation;
import utils.GenericDataProvider;
import utils.PropertiesManager;

/**
 * Created by kohlih on 12-11-2017.
 */
public class TestLoginUser extends BaseTest {

	@Test(groups = { "Smoke",
			"Regression" }, dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
	@CSVAnnotation.CSVFileParameters(path = "src\\test\\java\\test-data\\LoginCredentials.csv", delimiter = "###")
	public void testLoginUser(String username, String password) throws IOException {

		// Get Book Shelf based on input shelf Id
		GetLoginUser getLoginUser = new GetLoginUser(PropertiesManager.getProperty("baseURI"));
		getLoginUser.setUsername(username);
		getLoginUser.setPassword(password);
		getLoginUser.setExpectedStatusCode(200);
		getLoginUser.perform();
		logger.info("Login successful");
	}
}
