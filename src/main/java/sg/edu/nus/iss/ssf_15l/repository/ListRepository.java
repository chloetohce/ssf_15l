package sg.edu.nus.iss.ssf_15l.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.ssf_15l.utilities.Utility;

@Repository
public class ListRepository {
    @Qualifier(Utility.REDIS_OBJECT_TEMPLATE)
    private final RedisTemplate<String, Object> template;

    public ListRepository(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    public void leftPush(String key, String value) {
        template.opsForList().leftPush(key, value);
    }

    public void rightPush(String key, String value) {
        template.opsForList().rightPush(key, value);
    }

    public Object rightPop(String key) {
        return template.opsForList().rightPop(key);
    }

    public Object leftPop(String key) {
        return template.opsForList().leftPop(key);
    }

    public String get(String key, long index) {
        System.out.println(template.opsForList().index(key, index));
        System.out.println(key);
        System.out.println(index);
        return template.opsForList().index(key, index).toString();
    }

    public Long size(String key) {
        return template.opsForList().size(key);
    }

    public List<Object> getList(String key) {
        return template.opsForList().range(key, 0, -1);
    }

    /**
     * Searches for `value` to delete, and deletes it from the specified list at `key`.
     * @param key 
     * @param value
     * @return
     */
    public boolean deleteFirst(String key, String value) {
        Long index = template.opsForList().indexOf(key, (Object) value);
        if (index > 0) {
            template.opsForList().remove(key, 1, value);
            return true;
        }
        return false;
    }

    public void delete2(String key, String id) {
        List<Object> list = template.opsForList().range("persons", 0, -1);
        for (Object o : list) {
            System.out.println(o.toString());
            System.out.println(id);
            if (o.toString().startsWith(id)){
                template.opsForList().remove("persons", 1, o);
            }

        }

    }
}
