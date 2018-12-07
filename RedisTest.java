import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zt.Application;
import com.zt.bean.User;
import com.zt.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/*
**  redisUtil测试类
*/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class redisTest {
    @Resource
    private RedisUtil redisUtil;

    @Test
    public void testSet(){
//        User u = new User("小明",12);
//        String text = JSONObject.toJSONString(u);
//        redisUtil.set("user",text);
        List<User> list = new LinkedList<>();
        list.add(new User("aa",1));
        list.add(new User("bb",2));
        list.add(new User("cc",3));
        String text = JSONArray.toJSONString(list);
        redisUtil.set("array",text);
    }

    @Test
    public void testGet(){
//        User u = JSON.parseObject(redisUtil.get("user"),User.class);
//        System.out.println(u);
        List<User> list = JSONArray.parseArray(redisUtil.get("array"),User.class);
        for(User e : list){
            System.out.print(e+" ");
        }
    }

    @Test
    public void testHmset(){
        User u = new User("小红",14);
        redisUtil.hmset("info","user",JSONObject.toJSONString(u));
    }

    @Test
    public void testHmget(){
        User u = JSON.parseObject(redisUtil.hmget("info","user"),User.class);
        System.out.println(u);
    }

    @Test
    public void testList(){
        redisUtil.rpush("list","one");
        redisUtil.rpush("list","two");
        redisUtil.rpush("list","three");
        redisUtil.rpush("list","four");
        List<String> list = redisUtil.lrange("list");
        for(String e : list){
            System.out.print(e+" ");
        }
    }
}
