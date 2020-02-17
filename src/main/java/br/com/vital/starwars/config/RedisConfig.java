package br.com.vital.starwars.config;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vital.starwars.exception.ConvertFromJsonException;
import br.com.vital.starwars.exception.ConvertToJsonException;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisException;
import io.lettuce.core.RedisURI;
import io.lettuce.core.RedisURI.Builder;
import io.lettuce.core.api.StatefulRedisConnection;

@Configuration
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private Integer port;
	
	@Value("${spring.redis.timeout}")
	private Long timeoutMinutes = 20L;
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	private StatefulRedisConnection<String, String> connection;
	
	@PostConstruct
	public void init() {
		try {
			connect();
		} catch(RedisException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() {
		RedisClient redisClient = RedisClient.create(buildUrl());
		redisClient.setDefaultTimeout(Duration.ofMinutes(timeoutMinutes));
		
		connection = redisClient.connect();
	}
	
	private RedisURI buildUrl() {
		Builder builder = RedisURI.Builder.redis(host, port);
		return builder.withTimeout(Duration.ofMinutes(20L)).build();
	}
	
	public void save(String key, long seconds, String value) {
		connection.async().setex(key, seconds, value);
	}
	
	public void save(String key, Object object) {
		connection.async().setex(key, Duration.ofMinutes(timeoutMinutes).getSeconds(), object instanceof String ? (String) object: toJson(object));
	}
	
	private String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new ConvertToJsonException();
		}
	}
	
	private <T> T objectFromJson(String json, Class<T>classType) {
		try {
			if(json != null) {
				return objectMapper.readValue(json, classType);
			}
			
			return null;
			
		} catch (JsonProcessingException e) {
			throw new ConvertFromJsonException();
		}
	}
	
	public <T> T get(String redisKey,Class<T> classType) {
		String response = connection.sync().get(redisKey);
		
		return objectFromJson(response, classType);
		
		
	}

}
