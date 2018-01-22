package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.monotype.aaram.utils.CSVAnnotation;
import com.monotype.aaram.utils.GenericDataProvider;
import com.monotype.aaram.utils.PropertiesManager;

import api.user.GetLoginUser;

/**
 * Created by kohlih on 12-11-2017.
 */
public class TestLoginUser extends BaseTest {

	@Test(groups = { "Smoke",
			"Regression" }, dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
	@CSVAnnotation.CSVFileParameters(path = "src\\test\\java\\test-data\\LoginCredentials.csv", delimiter = "###")
	public void testLoginUser(int rowNo, HashMap<String, String> inputData) throws IOException {

		// Get Book Shelf based on input shelf Id
		GetLoginUser getLoginUser = new GetLoginUser(PropertiesManager.getProperty("baseURI"));
		getLoginUser.setUsername(inputData.get("username"));
		getLoginUser.setPassword(inputData.get("password"));
		getLoginUser.setExpectedStatusCode(200);
		getLoginUser.setContentType("application/xml");
		getLoginUser.perform();
		String responseStr = getLoginUser.getApiResponseAsString();
		Assert.assertTrue(responseStr.matches("logged in user session:\\d{13}"),
				"response does not match, actual : " + responseStr);
		logger.info("Login successful");
	}
}
