package api.user;

import static io.restassured.RestAssured.given;

import api.BaseAPI;
import pojo.PostUserRequest;

/**
 * Created by kohlih on 12-11-2017.
 */
public class PostUser extends BaseAPI {

	String apiPath = "/user";
	String contentType;
	private PostUserRequest postUserRequestBody;

	public PostUser(String baseURI) {
		super(baseURI);
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setPostUserRequestBody(PostUserRequest postUserRequestBody) {
		this.postUserRequestBody = postUserRequestBody;
	}

	@Override
	protected void createRequest() {
		requestSpecBuilder.setBaseUri(baseURI);
		requestSpecBuilder.setBasePath(apiPath);
		requestSpecBuilder.setContentType(contentType);
		requestSpecBuilder.setBody(postUserRequestBody);
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
