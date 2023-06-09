package com.example.workflow.config;

import java.util.HashMap;
import java.util.Map;

public enum ConversationWorkflow {

    RIDE_BOOKING("Boooking_Flow", "BOOK_RIDE"),
    RIDE_UPDATE("Ride_Update_Flow", ""),
    UPDATE_LANGUAGE("language_change_flow", "UPDATE_LANGUAGE"),
    MANAGE_PLACES("Starred_Places_Flow", "MANAGE_PLACES"),
    PREVIOUS_RIDE("", "PREVIOUS_RIDE"),
    SUPPORT("", "SUPPORT"),
    OTHER("", "OTHER"),
    KNOW_MORE("", "KNOW_MORE"),
    OPEN_DATA("", "OPEN_DATA"),
    FEEDBACK("", "FEEDBACK"),
    MAIN_MENU("", "MAIN_MENU"),
    CANCEL_BOOKING("", "CANCEL_BOOKING"),
    RETRY_SEARCH("", "RETRY_SEARCH"),
    NEED_HELP("", "NEED_HELP"),
    FAVOURITE_PLACES("", "FAVOURITE_PLACES");

    private static final Map<String, ConversationWorkflow> intToEnumMapProcessDefinition = new HashMap<>();
    private static final Map<String, ConversationWorkflow> intToEnumMapPostBack = new HashMap<>();

    static {
        // Default methods for enum reverse lookup fby definition id

        for (ConversationWorkflow enumVal : ConversationWorkflow.values()) {
            intToEnumMapProcessDefinition.put(enumVal.getProcessDefinitionName(), enumVal);
        }

        // Default methods for enum reverse lookup fby postback text

        for (ConversationWorkflow enumVal : ConversationWorkflow.values()) {
            intToEnumMapPostBack.put(enumVal.getPostbackText(), enumVal);
        }
    }

    private final String processDefinitionName;
    private final String postbackText;

    ConversationWorkflow(String processDefinitionName, String postbackText) {
        this.processDefinitionName = processDefinitionName;
        this.postbackText = postbackText;
    }

    public static ConversationWorkflow fromProcessDefinitionName(String processDefinitionKey) {
        return intToEnumMapProcessDefinition.get(processDefinitionKey);
    }

    public static ConversationWorkflow fromPostBackText(String postbackText) {
        return intToEnumMapPostBack.get(postbackText);
    }

    public String getProcessDefinitionName() {
        return processDefinitionName;
    }

    public String getPostbackText() {
        return postbackText;
    }
}
