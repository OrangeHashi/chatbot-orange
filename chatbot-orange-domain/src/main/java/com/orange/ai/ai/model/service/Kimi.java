package com.orange.ai.ai.model.service;

import com.alibaba.fastjson.JSON;
import com.orange.ai.ai.model.IKimi;
import com.orange.ai.ai.model.aggregates.Message;
import com.orange.ai.ai.model.vo.Choices;
import com.orange.ai.ai.model.vo.Root;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 2024/7/23
 * Time: 22:07
 * Description:
 */


@Service
public class Kimi implements IKimi {


    @Value("${chatbot.key}")
    private String key;
    @Override
    public String doChatGPT(String question) throws IOException, ParseException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.moonshot.cn/v1/chat/completions");
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("Authorization","Bearer " + key);
        String paramJson = "{\"model\": \"moonshot-v1-8k\"," +
                "\"messages\": [{" +
                "\"role\": \"system\"," +
                "\"content\": \"你是 Kimi，由 Moonshot AI 提供的人工智能助手，你更擅长中文和英文的对话。" +
                "你会为用户提供安全，有帮助，准确的回答。" +
                "同时，你会拒绝一切涉及恐怖主义，种族歧视，黄色暴力等问题的回答。" +
                "Moonshot AI 为专有名词，不可翻译成其他语言。\"" +
                "}," +
                "{\"role\": \"user\", \"content\": \"" + question + "\"}]," +
                "\"temperature\": 0.3}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("tex/json","UTF-8"));
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            Root aiAnswer = JSON.parseObject(res, Root.class);
            StringBuilder answer = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for(Choices choice : choices){
                answer.append(choice.getMessage().getContent());
                System.out.println("choice = " + choice.getMessage().getContent());
            }
            return answer.toString();
        } else {
            throw new RuntimeException("queryAnsweredQuestionsTopicId Err Codes is" + response.getCode());
        }
    }
}
