package com.example.workflow.services;

import com.example.workflow.models.User;
import com.example.workflow.models.gupshup.WebhookMessagePayload;

public interface WorkflowService {
    public void handleWorkflow(String processInstanceId, User user, String messageType, WebhookMessagePayload webhookMessagePayload) throws Exception;
    public void initiateWorkflow(User user, String flowType);
}
