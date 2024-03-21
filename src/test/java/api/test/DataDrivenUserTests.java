package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenUserTests {
	
	@Test(priority = 1 ,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUserDD(String userID,String userName,String fname,String lname,String userEmail,String pwd,String ph) {
		
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserEndPoints.createUser(userPayload);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	

	@Test(priority = 2 ,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserDD(String userName) {
		
		Response response = UserEndPoints.deleteUser(userName);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
	}

}
