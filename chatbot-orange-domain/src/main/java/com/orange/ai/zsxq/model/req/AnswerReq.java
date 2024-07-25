package com.orange.ai.zsxq.model.req;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 2024/7/23
 * Time: 19:50
 * Description:
 */

public class AnswerReq{
    private ReqData reqData;

    public ReqData getReqData() {
        return reqData;
    }

    public void setReqData(ReqData reqData) {
        this.reqData = reqData;
    }

    public AnswerReq(ReqData reqData) {
        this.reqData = reqData;
    }
}
