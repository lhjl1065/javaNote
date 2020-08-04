package cn.lhjl.domain;

import java.io.Serializable;

public class Id_SessionId implements Serializable {
    private int account_id;
    private String sessionId;

    @Override
    public String toString() {
        return "Id_SessionId{" +
                "account_id=" + account_id +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
}
