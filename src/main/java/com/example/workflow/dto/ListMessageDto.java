package com.example.workflow.dto;

import com.example.workflow.models.gupshup.GlobalButtons;
import com.example.workflow.models.gupshup.ListMessageItem;

import java.util.List;

public class ListMessageDto {
    private String type;

    private String msgid;
    private String title;
    private String body;
    private List<GlobalButtons> globalButtons;
    private List<ListMessageItem> items;

    public ListMessageDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<GlobalButtons> getGlobalButtons() {
        return globalButtons;
    }

    public void setGlobalButtons(List<GlobalButtons> globalButtons) {
        this.globalButtons = globalButtons;
    }

    public List<ListMessageItem> getItems() {
        return items;
    }

    public void setItems(List<ListMessageItem> items) {
        this.items = items;
    }
}
