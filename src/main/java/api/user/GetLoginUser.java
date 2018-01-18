package api.user;

import static io.restassured.RestAssured.given;

import api.BaseAPI;

/**
 * Created by kohlih on 12-11-2017.
 */
public class GetLoginUser extends BaseAPI {

	String apiPath = "/user/login";
	String contentType;
	String username;
	String password;

	public GetLoginUser(String baseURI) {
		super(baseURI);
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	protected void createRequest() {
		requestSpecBuilder.setBaseUri(baseURI);
		requestSpecBuilder.setBasePath(apiPath);
		requestSpecBuilder.setContentType(contentType);
		requestSpecBuilder.addQueryParam("username", username);
		requestSpecBuilder.addQueryParam("password", password);
		requestSpecification = requestSpecBuilder.build();
	}

	@Override
	protected void executeRequest() {
		apiResponse = given().spec(requestSpecification).post();
	}

	@Override
	protected void validateResponse() {
		responseSpecBuilder.expectStatusCode(getExpectedStatusCode());
		responseSpecification = responseSpecBuilder.build();
		apiResponse.then().spec(responseSpecification);
	}
}
