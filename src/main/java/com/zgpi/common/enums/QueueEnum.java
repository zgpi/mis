package com.zgpi.common.enums;

import lombok.Getter;

@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    MESSAGE_QUEUE("message.center.direct", "message.center.create", "message.center.create"),
    /**
     * 消息通知ttl队列
     */
    MESSAGE_TTL_QUEUE("message.center.topic.ttl", "message.center.create.ttl", "message.center.create.ttl");
    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
