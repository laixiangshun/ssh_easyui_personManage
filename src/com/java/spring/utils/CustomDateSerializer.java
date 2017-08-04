package com.java.spring.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(Date arg0, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String formatdate=format.format(arg0);
		jgen.writeString(formatdate);
	}

}
