package com.hzm.freestyle.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * redis监听者
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月20日
 */
@Component
public class RedisMessageLister implements MessageListener {

    public static final String TITLE = "消费redis消息:";

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        try {
            final String json = new String(message.getBody(), "UTF-8");
            System.out.println(TITLE + "message" + JSONObject.toJSONString(json));
            System.out.println(TITLE + "bytes" + new String(bytes));

            redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    final Expiration seconds = Expiration.seconds(1000L);

                    redisConnection.set("".getBytes(), "".getBytes(), seconds, RedisStringCommands.SetOption.SET_IF_PRESENT);
                    return null;
                }
            });

        } catch (UnsupportedEncodingException e) {
        }
        System.out.println(TITLE + JSONObject.toJSONString(message));
    }

}
