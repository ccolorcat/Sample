package cc.colorcat.sample.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */

public class License {
    /**
     * key : apache-2.0
     * name : Apache License 2.0
     * spdx_id : Apache-2.0
     * url : https://api.github.com/licenses/apache-2.0
     * node_id : MDc6TGljZW5zZTI=
     */

    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("spdx_id")
    private String spdxId;
    @SerializedName("url")
    private String url;
    @SerializedName("node_id")
    private String nodeId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpdxId() {
        return spdxId;
    }

    public void setSpdxId(String spdxId) {
        this.spdxId = spdxId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String toString() {
        return "License{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", spdxId='" + spdxId + '\'' +
                ", url='" + url + '\'' +
                ", nodeId='" + nodeId + '\'' +
                '}';
    }
}