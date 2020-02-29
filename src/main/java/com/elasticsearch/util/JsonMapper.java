package com.elasticsearch.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.TimeZone;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/2/29 17:47
 * @content
 */
public class JsonMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);
    private static JsonMapper mapper;

    public JsonMapper() {
        this(JsonInclude.Include.NON_EMPTY);
    }

    public JsonMapper(JsonInclude.Include include) {
        if (include != null) {
            this.setSerializationInclusion(include);
        }

        this.enableSimple();
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
                jgen.writeString("");
            }
        });

        this.setTimeZone(TimeZone.getDefault());
    }

    public static JsonMapper getInstance() {
        if (mapper == null) {
            mapper = (new JsonMapper()).enableSimple();
        }

        return mapper;
    }



    public String toJson(Object object) {
        try {
            return this.writeValueAsString(object);
        } catch (IOException var3) {
            logger.warn("write to json string error:" + object, var3);
            return null;
        }
    }




    public JsonMapper enableSimple() {
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return this;
    }

}
