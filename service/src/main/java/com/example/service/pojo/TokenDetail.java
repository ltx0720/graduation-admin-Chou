package com.example.service.pojo;

import lombok.Data;

/**
 * @Author ltx
 * @Date 22:20 2020/4/13
 *
 * Token 的签发时间有效时间等信息
 */
@Data
public class TokenDetail {
    // 签发时间
    private long issue;
    // 有效时间
    private long alive;

    public TokenDetail(long issue, long alive) {
        this.issue = issue;
        this.alive = alive;
    }

    public long getAlive() {
        return alive;
    }

    public void setAlive(long alive) {
        this.alive = alive;
    }

    public long getIssue() {
        return issue;
    }

    public void setIssue(long issue) {
        this.issue = issue;
    }
}
