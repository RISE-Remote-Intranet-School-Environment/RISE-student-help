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
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
		System.out.println((response));
		assertNotEquals(response,"[]"); //Acceder a response et trouver si error existe

	}


	@Test
	public void UnitTestThreadsCreateContent() throws Exception {
		String jos = "{\"title\":\"Test thread\",\"category\":\"Math\",\"tags\":[],\"firstPost\":{\"authorId\":\"d66b3f8c-2271-4afb-a348-e370effff\",\"content\":\"Fisrt postman\"}}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jos.toString(),headers);
		this.restTemplate.postForObject("http://localhost:" + 8080 + "/threads",entity, String.class);
		assertEquals(restTemplate.getForObject("http://localhost:" + 8080 + "/threads/3086ac10-4dae-4b38-a793-c462577ed200",String.class),entity); //Problème de récupération de trop => utiliser fonction dispo code ?
	}

}

