package com.nnk.springboot.ut.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RuleNameServiceTest {

	@Autowired
	MockMvc mockMvc;
	@Mock
	RuleNameService ruleNameService;

	RuleName ruleNameTest1 = new RuleName("Test1", "Test1", "Test1", "Test1", "Test1", "Test1");
	RuleName ruleNameTest2 = new RuleName("Test2", "Test2", "Test2", "Test2", "Test2", "Test2");

	List<RuleName> rulesNamesListTest = new ArrayList<RuleName>();

	@Test
	public void testFindAllRules() {
		rulesNamesListTest.add(ruleNameTest1);
		rulesNamesListTest.add(ruleNameTest2);
		Mockito.when(ruleNameService.findAllRuleNames()).thenReturn(rulesNamesListTest);
		assertEquals(2, rulesNamesListTest.size());
	}

	@Test
	public void testFindRuleById() {
		Optional<RuleName> ruleNameTest3 = Optional.of(new RuleName());
		Mockito.when(ruleNameService.findById(1)).thenReturn(ruleNameTest3);
		assertNotNull(ruleNameTest3);
	}

	@Test
	public void testSaveRule() {
		String ref = "Test1";
		Mockito.when(ruleNameService.save(ruleNameTest1)).thenReturn(ruleNameTest1);
		assertEquals(ref, ruleNameTest1.getDescription());
	}

	@Test
	public void testDeleteRule() {
		Mockito.doNothing().when(ruleNameService).delete(ruleNameTest2);
		verify(ruleNameService, times(1));

	}
}
