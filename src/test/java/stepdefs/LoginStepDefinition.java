package stepdefs;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Steps;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginStepDefinition {

	@Steps
	Login login;

	private Response response;
	private int json;
	private static RequestSpecification request;

	private static String ENDPOINT_LOGIN = "https://r8alxaspi1.execute-api.ap-southeast-2.amazonaws.com/api/ezypay/login";
	private String username;
	private String password;

	@Before
	public static void initSpec(){
		request = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri(ENDPOINT_LOGIN)
				.build();
	}
	@Given("^a user(?:| does not) exists with username (.*)$")
	public void aUserExistsWithUsernameUsername(String username) throws Throwable {
		given().param("username", username);
		this.username=username;
	}

	@And("^password (.*)$")
	public void password(String password) throws Throwable {
		given().param("password", password);
		this.password=password;
	}

	@When("^the user logs in to the system$")
	public void theUserLogsInToTheSystem() throws Throwable {
		String myjson="{\"username\": \"" + username + "\",\"password\": \""+password + "\"}";
		response=given()
				.spec(request)
				.body(myjson)
				.when()
				.post(ENDPOINT_LOGIN);
	}

	@Then("^the (?:user should be logged in successfully|user should be notified of error)$")
	public void theUserShouldBeLoggedInSuccessfully() throws Throwable {
		json=response.getStatusCode();
		if(username.equals("test-user"))
			assertThat(json).isEqualTo(200);
		else
			assertThat(json).isNotIn("200","201","202");
	}

	@And("^the user should be welcomed with name and password$")
	public void theUserShouldBeWelcomedWithHisName() throws Throwable {
	login =response.as(Login.class);
		assertThat(login.getUsername().equals(username));
		assertThat(login.getPassword().equals(password));
	}

	@And("^the user should be notified as (.*) and (.*)$")
	public void theUserShouldBeNotifiedAsCodeAndMessage(String code, String message) throws Throwable {
		login =response.as(Login.class);
		assertThat(login.getCode().equals(code));
		assertThat(login.getMessage().equals(message));
	}
}


