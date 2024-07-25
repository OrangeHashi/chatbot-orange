package com.orange.ai.zsxq.model.res;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 2024/7/23
 * Time: 19:51
 * Description:
 */

public class AnswerRes {
    private boolean successed;

    public boolean isSuccessed() {
        return successed;
    }

    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }

    public AnswerRes(boolean successed) {
        this.successed = successed;
    }
}
