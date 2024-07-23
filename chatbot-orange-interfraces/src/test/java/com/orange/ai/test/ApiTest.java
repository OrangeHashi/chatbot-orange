package com.orange.ai.test;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User:orange
 * Date:2024/7/23
 * Time:11:11
 * Description:
 */


@SpringBootTest
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException, ParseException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");
        get.addHeader("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22190da83df55810-02c95bcb772f8c4-4c657b58-1327104-190da83df56e3a%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkwZGE4M2RmNTU4MTAtMDJjOTViY2I3NzJmOGM0LTRjNjU3YjU4LTEzMjcxMDQtMTkwZGE4M2RmNTZlM2EifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%22190da83df55810-02c95bcb772f8c4-4c657b58-1327104-190da83df56e3a%22%7D; zsxq_access_token=2D2D662C-F2A0-8DB9-AC2F-9214E00F440E_6EB5CDCE87185F9D; zsxqsessionid=2835f6514c64da62687ad7b2dd9230d9; abtest_env=beta");
        get.addHeader("Content-type", "application/json; charset=UTF-8");
        CloseableHttpResponse response = httpclient.execute(get);
        if (response.getCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getCode());
        }
    }

    @Test
    public void comment() throws IOException, ParseException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/2855414112814241/comments");
        post.addHeader("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22190da83df55810-02c95bcb772f8c4-4c657b58-1327104-190da83df56e3a%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkwZGE4M2RmNTU4MTAtMDJjOTViY2I3NzJmOGM0LTRjNjU3YjU4LTEzMjcxMDQtMTkwZGE4M2RmNTZlM2EifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%22190da83df55810-02c95bcb772f8c4-4c657b58-1327104-190da83df56e3a%22%7D; zsxq_access_token=2D2D662C-F2A0-8DB9-AC2F-9214E00F440E_6EB5CDCE87185F9D; zsxqsessionid=2835f6514c64da62687ad7b2dd9230d9; abtest_env=beta");
        post.addHeader("Content-type", "application/json; charset=UTF-8");
        String paramJason = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"123456\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";

        StringEntity entity = new StringEntity(paramJason);
        post.setEntity(entity);
        CloseableHttpResponse response = httpclient.execute(post);
        if (response.getCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getCode());
        }
    }
}
