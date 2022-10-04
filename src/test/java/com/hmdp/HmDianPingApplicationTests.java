package com.hmdp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HmDianPingApplicationTests {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void stringRedisTempleateTest(){
        stringRedisTemplate.opsForList().leftPush("list1", String.valueOf(1));
        stringRedisTemplate.opsForList().leftPush("list1", String.valueOf(2));
        String list1 = stringRedisTemplate.opsForList().leftPop("list1");
        String list2 = stringRedisTemplate.opsForList().leftPop("list1");
        System.out.println(list1);
        System.out.println(list2);
    }

}
