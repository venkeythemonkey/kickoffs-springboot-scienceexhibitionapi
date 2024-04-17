package com.fresco.scienceExhibition;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class ScienceExhibitionApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	@Order(1)
	public void getnulluser() throws Exception {

		mvc.perform(get("/exhibition").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	@Order(2)
	public void postuser() throws Exception {
		mvc.perform(post("/exhibition/add")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(getPostExhibition("studentOne",130079L,"topicOne","guideOne").toJSONString()))
				.andExpect(MockMvcResultMatchers.status().is(201));

		mvc.perform(post("/exhibition/add")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(getPostExhibition("studentTwo",130129L,"topicTwo","guideTwo").toJSONString()))
				.andExpect(MockMvcResultMatchers.status().is(201));

		mvc.perform(post("/exhibition/add")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(getPostExhibition("studentThree",130034L,"topicThree","guideThree").toJSONString()))
				.andExpect(MockMvcResultMatchers.status().is(201));

		mvc.perform(post("/exhibition/add")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(getPostExhibition("studentFour",130026L,"topicFour","guideFour").toJSONString()))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}


	@Test
	@Order(3)
	public void getfullExhibitionListWithPageNo() throws Exception {

		mvc.perform(get("/exhibition" + "?pageNo=0&pageSize=2&sortBy=id")
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$.[0].studentName", containsStringIgnoringCase("studentOne")))
				.andExpect(jsonPath("$.[0].idCardNumber", Matchers.is(130079)))
				.andExpect(jsonPath("$.[0].topic",containsStringIgnoringCase("topicOne")))
				.andExpect(jsonPath("$.[0].guideMember",containsStringIgnoringCase("guideOne")))
				.andExpect(jsonPath("$.[1].id", Matchers.is(2)))
				.andExpect(jsonPath("$.[1].studentName", containsStringIgnoringCase("studentTwo")))
				.andExpect(jsonPath("$.[1].idCardNumber", Matchers.is(130129)))
				.andExpect(jsonPath("$.[1].topic",containsStringIgnoringCase("topicTwo")))
				.andExpect(jsonPath("$.[1].guideMember",containsStringIgnoringCase("guideTwo")));


		mvc.perform(get("/exhibition" + "?pageNo=2&pageSize=1&sortBy=id")
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				//"studentThree",130034L,"topicThree","guideThree"
				.andExpect(jsonPath("$.[0].id", Matchers.is(3)))
				.andExpect(jsonPath("$.[0].studentName", containsStringIgnoringCase("studentThree")))
				.andExpect(jsonPath("$.[0].idCardNumber", Matchers.is(130034)))
				.andExpect(jsonPath("$.[0].topic",containsStringIgnoringCase("topicThree")))
				.andExpect(jsonPath("$.[0].guideMember",containsStringIgnoringCase("guideThree")));

	}

	@Test
	@Order(4)
	public void getfullExhibitionListWithBadPageNo() throws Exception {
		mvc.perform(get("/exhibition" + "?pageNo=2&pageSize=3&sortBy=id")
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(400));
	}



	@Test
	@Order(5)
	public void deleteAndPatchExhibitionbyBadId() throws Exception{
		mvc.perform(delete("/exhibition/6")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400))
				.andReturn();

		mvc.perform(delete("/exhibition/7")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400))
				.andReturn();

		mvc.perform(patch("/exhibition/9")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(UpdateExhibitionData("newtopic","newguide")
						.toJSONString())).andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	@Order(6)
	public void patchExhibitionwithId() throws Exception{
		mvc.perform(patch("/exhibition/2")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(UpdateExhibitionData("newTopicTwo","newGuideTwo")
						.toJSONString())).andExpect(MockMvcResultMatchers.status().isOk());


	}

	@Test
	@Order(7)
	public void deleteExhibitionbyId() throws Exception{

		mvc.perform(delete("/exhibition/4")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

	}
















	//studentName,idCardNumber,topic,guideMember

	public JSONObject getPostExhibition(String studentName, Long idCardNumber, String topic, String guideMember){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentName", studentName);
		map.put("idCardNumber", idCardNumber);
		map.put("topic", topic);
		map.put("guideMember", guideMember);
		return new JSONObject(map);
	}

	public JSONObject UpdateExhibitionData(String topic, String guideMember){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("topic", topic);
		map.put("guideMember", guideMember);
		return new JSONObject(map);
	}

}
