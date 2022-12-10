package be.ecam.ms_studenthelp.ThreadUnitTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UnitTestThreadsDeleteContent {
	@Value("${server.port}")
	private int port;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void unitTestThreadsDeleteContent() throws Exception {
		String id = CreateThreadTestForThreads.createThreadTestForThreads(mockMvc, port);

		this.mockMvc.perform(delete("http://localhost:" + port + "/threads/" + id))
				.andExpect(status().isOk());

		this.mockMvc.perform(get("http://localhost:" + port + "/threads/" + id))
				.andExpect(status().isNotFound());
	}

}


