package com.map;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class MapTest {
	private static Logger logs = Logger.getLogger(MapTest.class);

	@Test
	public void query() throws Exception {
		PlaceApi pa = new PlaceApi();
		String result = pa.query();
		logs.debug(result);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", "2");
		MapInfo mi = new MapInfo();
		mi.setAddress("x1");
		mi.setLocation(map);
		mi.setName("x3");
		mi.setStreet_id("x4");
		mi.setUid("x5");
		List<MapInfo> list = new ArrayList<MapInfo>();
		list.add(mi);
		MapEntity me = new MapEntity();
		me.setMessage("m1");
		me.setResults(list);
		me.setStatus("m3");
		ObjectMapper mapper2 = new ObjectMapper();    
		StringWriter sw = new StringWriter();    
		JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);    
		mapper2.writeValue(gen, me);    
		gen.close();
		String json = sw.toString();
		logs.debug(json);
		ObjectMapper mapper = new ObjectMapper();  
		MapEntity mapEntity = mapper.readValue(result, MapEntity.class);
		for (MapInfo mapInfo : mapEntity.getResults()) {
			logs.debug("--->"+mapInfo.getName());
		}
	}
}