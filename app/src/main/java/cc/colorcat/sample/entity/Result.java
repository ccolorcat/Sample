package cc.colorcat.sample.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cxx on 18-8-23.
 * xx.ch@outlook.com
 */
public class Result<T> {
    public static final int STATUS_OK = 1;

    @JSONField(name = "status")
    @JsonProperty("status")
    @SerializedName("status")
    private int status;
    @JSONField(name = "msg")
    @JsonProperty("msg")
    @SerializedName("msg")
    private String msg;
    @JSONField(name = "data")
    @JsonProperty("data")
    @SerializedName("data")
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
