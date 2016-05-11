package dudeco;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import java.net.*;
import redis.clients.jedis.*;
import java.util.*;

@Controller 
public class TestController {
	JedisPool pool = null;
	
	public TestController() {
		try {
        URI redisUri = new URI(System.getenv("REDISCLOUD_URL"));
        pool = new JedisPool(new JedisPoolConfig(),
                redisUri.getHost(),
                redisUri.getPort(),
                Protocol.DEFAULT_TIMEOUT,
                redisUri.getUserInfo().split(":",2)[1]);
		} catch (URISyntaxException e) {
      throw new RuntimeException(e);  
		}

	}

	@RequestMapping("/")
	public ModelAndView testIndex(
		@RequestParam(value="string", defaultValue="", required = false) String value,
		@RequestParam(value="number", defaultValue="0", required = false) Integer score) {

		Jedis jedis = pool.getResource();
		Map<String,String> map = null;
		Set<String> set = null;
		Set<Tuple> tuples = null;

		try {
			jedis.zadd("key", score, value);
			tuples = jedis.zrangeWithScores("key", 0, -1);
		} finally {
			// return the instance to the pool when you're done
			pool.returnResource(jedis);	
		}

		return new ModelAndView("testIndex", "model", tuples);
	}
}
