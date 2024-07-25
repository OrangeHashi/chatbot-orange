package com.orange.ai.test;

import com.alibaba.fastjson.JSON;
import com.orange.ai.ai.model.IKimi;
import com.orange.ai.zsxq.model.IZsxqApi;
import com.orange.ai.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.orange.ai.zsxq.model.res.AnswerRes;
import com.orange.ai.zsxq.model.vo.Topics;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 2024/7/23
 * Time: 20:23
 * Description:
 */


@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot.groupId}")
    private String groupId;

    @Value("${chatbot.cookie}")
    private String cookie;

    @Autowired
    private IZsxqApi iZsxqApi;

    @Autowired
    private IKimi iKimi;


    @Test
    public void test_zsxqApi() throws IOException, ParseException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = iZsxqApi.queryAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("查询的问题为：{}", JSON.toJSON(unAnsweredQuestionsAggregates));
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = String.valueOf(topic.getTopic_id());
            String text = topic.getTalk().getText();
            logger.info("topicId:{} text:{}",topicId,text);
        }
    }


    @Test
    public void test_kimi() throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.moonshot.cn/v1/chat/completions");
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("Authorization","Bearer sk-0en35FPzwAw16B2LNeYzzGjtrmakmCsKU7CfcvJj9NA3PqmI");
        String paramJson = "{\"model\": \"moonshot-v1-8k\",\"messages\": [{\"role\": \"system\", \"content\": \"你是 Kimi，由 Moonshot AI 提供的人工智能助手，你更擅长中文和英文的对话。你会为用户提供安全，有帮助，准确的回答。同时，你会拒绝一切涉及恐怖主义，种族歧视，黄色暴力等问题的回答。Moonshot AI 为专有名词，不可翻译成其他语言。\"}], \"temperature\": 0.3}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("tex/json","UTF-8"));
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println("res = " + res);
        } else {
            throw new RuntimeException("queryAnsweredQuestionsTopicId Err Codes is" + response.getCode());
        }
    }


    @Test
    public void test_kimi_api() throws IOException, ParseException {
        String s = iKimi.doChatGPT("写一个冒泡排序");
        logger.info("回答为:{}",s);
    }

    @Test
    public void test_comment_api() throws IOException, ParseException {
        boolean answer = iZsxqApi.answer("28885518425541",cookie,"2855414112814241","我是测试s");
        logger.info("回答为:{}",answer);
//        iZsxqApi.comment();
    }


}
