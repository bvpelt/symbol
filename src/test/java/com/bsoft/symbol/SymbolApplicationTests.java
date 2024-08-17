package com.bsoft.symbol;

import com.bsoft.symbol.services.SLDService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SymbolApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void genSLD() {
		SLDService sldService = new SLDService();

		try {
			JSONObject json = sldService.convertXML("Lijnsymbolen_v1.0.1.sld");
			log.info(json.toString(4));
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
}
