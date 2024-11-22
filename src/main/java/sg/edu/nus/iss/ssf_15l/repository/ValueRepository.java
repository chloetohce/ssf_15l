package sg.edu.nus.iss.ssf_15l.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.ssf_15l.utilities.Utility;

@Repository
public class ValueRepository {
    private final RedisTemplate<String, String> template;

    public ValueRepository(@Qualifier(Utility.REDIS_STRING_TEMPLATE) RedisTemplate<String, String> template) {
        this.template = template;
    }

    public void createValue(String key, String value) {
        template.opsForValue().set(key, value);
    }
    
    public String getValue(String key) {
        return template.opsForValue().get(key);
    }

    public boolean deleteValue(String key) {
        // NOTE: to delete a value, you do not need opsForValue(). But there is a getAndDelete() method for that.
        return template.delete(key);
    }

    /**
     * Increases value by one. 
     * 
     * Only works for Values with an integer value
     */
    public void incrementValue(String key) {
        template.opsForValue().increment(key);
    }

    /**
     * Decreases value by one. 
     * 
     * Only works for Values with an integer value
     */
    public void decrementValue(String key) {
        template.opsForValue().decrement(key);
    }

    /**
     * Increases value by given number. 
     * 
     * Only works for Values with an integer value.
     */
    public void incrementBy(String key, int num) {
        template.opsForValue().increment(key, num);
    }

    /**
     * Increases value by given number. 
     * 
     * Only works for Values with an integer value.
     */
    public void decrementBy(String key, int num) {
        template.opsForValue().decrement(key, num);
    }

    public boolean isExisting(String key) {
        return template.hasKey(key);
    }
}
