package be.ecam.ms_studenthelp;


import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.*;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MsStudenthelpApplicationTests{
	@Autowired
	private TestRestTemplate restTemplate;
	/*
	localhost:8080/posts/2e7e65f5-8374-4c1f-902f-66516f631f44
	*/
	@Test
	public void UnitTestCategoriesContent() throws Exception {
		String response = this.restTemplate.getForObject("http://localhost:" + 8080 + "/categories", String.class);
		List listname = new ArrayList(Collections.singleton("Math")); //Réussir à créer ["Math"]/ Fonctionnel
		assertEquals(response,"['Math']");
	}


	@Test
	public void UnitTestThreadsCreateContent() throws Exception {
		JSONObject jo = new JSONObject();
		JSONObject ja = new JSONObject();
		jo.put("title", "Test thread");
		jo.put("category", "Math");
		jo.put("firstPost", ja);
		ja.put("authorId","d66b3f8c-2271-4afb-a348-e370effff");
		ja.put("content", "first post");
		System.out.println(jo);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jo.toString(),headers);
		this.restTemplate.postForObject("http://localhost:" + 8080 + "/threads",entity, String.class); 		//Error 404 sur postman à revoir ?
		assertEquals(this.restTemplate.getForObject("http://localhost:" + 8080 + "/threads",String.class),jo);
	}
	@Test
	public void UnitTestThreadsCheckContent() throws Exception {
		assertEquals(this.restTemplate.getForObject("http://localhost:" + 8080 + "/threads",String.class),"[]");
	}
}

