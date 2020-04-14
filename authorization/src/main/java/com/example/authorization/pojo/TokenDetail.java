package com.example.authorization.pojo;

/**
 * @Author ltx
 * @Date 22:20 2020/4/13
 *
 * Token 的签发时间有效时间等信息
 */
public class TokenDetail {
    // 签发时间
    private long issue;
    // 有效时间
    private long alive;

    public TokenDetail(long issue, long alive) {
        this.issue = issue;
        this.alive = alive;
    }
}
