package com.smyhvae.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonPrimitive;

public class JSONUtility {

	
	public String getStringValueFromJSON(Map<String, Object> map, String key){
		
		Object object = map.get(key);
		String objectString = object == null ? null : ((JsonPrimitive)object).getAsString();
		
		return objectString;
	}
	
	public Integer getIntegerValueFromJSON(Map<String, Object> map, String key){
		
		Object object = map.get(key);
		Integer objectInteger = object == null ? null : ((JsonPrimitive)object).getAsInt();

		return objectInteger;
	}
	
	public BigDecimal getBigDecimalValueFromJSON(Map<String, Object> map, String key){
		
		Object object = map.get(key);
		BigDecimal objectBigDecimal = object == null ? null : ((JsonPrimitive)object).getAsBigDecimal();
		return objectBigDecimal;
	}
	
	public Date getDateValueFromJSON(Map<String, Object> map, String key) throws ParseException{
		
		Object object = map.get(key);
		String objectString = object == null ? null : ((JsonPrimitive)object).getAsString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date objectDate = (objectString == null || ("").equals(objectString)) ? null : sdf.parse(objectString);
		return objectDate;
	}

	public Date getDateValueFromJSONForSync(Map<String, Object> map, String key) throws ParseException{
		
		Object object = map.get(key);
		String objectString = object == null ? null : ((JsonPrimitive)object).getAsString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date objectDate = (objectString == null || ("").equals(objectString)) ? null : sdf.parse(objectString);
		return objectDate;
	}

	public Map<String, Object> getObjectValueFromJSON(Map<String, Object> map, String key){

		Map<String, Object> object = (Map<String, Object>)map.get(key);

		return object;
	}

	public List<Map<String, Object>> getListValueFromJSON(Map<String, Object> map, String key){
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> objectList = (List<Map<String, Object>>)map.get(key);
	
		return objectList;
	}
}
