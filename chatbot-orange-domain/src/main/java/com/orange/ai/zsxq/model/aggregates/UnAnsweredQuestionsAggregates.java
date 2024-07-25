package com.orange.ai.zsxq.model.aggregates;

import com.orange.ai.zsxq.model.res.RespData;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 2024/7/23
 * Time: 17:49
 * Description:
 */

public class UnAnsweredQuestionsAggregates {
    private boolean success;
    private RespData resp_data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }
}
