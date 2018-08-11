package bos_fore;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class JedisTest {

	@Test
	public void test() {
		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.set("pwd", "123456");
		
//		String ret = jedis.get("pwd");
//		System.out.println(ret);
		
		jedis.del("pwd");
		jedis.close();
	}

	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Test
	public void testBySpringDataRedis(){
		redisTemplate.opsForValue().set("username", "admin", 24, TimeUnit.HOURS);
//		String ret = redisTemplate.opsForValue().get("username");
//		redisTemplate.delete("username");
		
		
//		JedisPoolConfig poolConfig = new JedisPoolConfig();
//		poolConfig.setMaxTotal(5);
//		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
//		connectionFactory.setHostName(hostName);
//		connectionFactory.setPort(port);
//		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(connectionFactory);
//		System.out.println(ret);
	}
}
