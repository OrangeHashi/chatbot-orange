package com.orange.ai.ai.model;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 2024/7/23
 * Time: 22:06
 * Description:
 */

public interface IKimi {
    String doChatGPT(String question) throws IOException, ParseException;
}
