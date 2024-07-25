package com.orange.ai.zsxq.model;

import com.orange.ai.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 2024/7/23
 * Time: 17:44
 * Description:
 */

public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException, ParseException;

    boolean answer(String groupId,String cookies,String topicId,String text) throws IOException, ParseException;

    void comment() throws IOException, ParseException;
}
