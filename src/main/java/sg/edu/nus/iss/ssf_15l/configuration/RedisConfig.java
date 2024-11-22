package sg.edu.nus.iss.ssf_15l.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import sg.edu.nus.iss.ssf_15l.utilities.Utility;


@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.username}")
    private String redisUsername;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration();
        rsc.setHostName(redisHost);
        rsc.setPort(redisPort);

        if (!redisPassword.trim().isEmpty()) {
            rsc.setUsername(redisUsername);
            rsc.setPassword(redisPassword);
        }

        JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        JedisConnectionFactory jedisFactory = new JedisConnectionFactory(rsc, jedisClient);
        jedisFactory.afterPropertiesSet();

        return jedisFactory;
        
    }

    @Bean(Utility.REDIS_STRING_TEMPLATE)
    public RedisTemplate<String, String> redisStringTemplate() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }

    @Bean(Utility.REDIS_OBJECT_TEMPLATE)
    public RedisTemplate<String, Object> redisObjectTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer()); //Can customise the serialisation to different format here
        return template;
    }
}
