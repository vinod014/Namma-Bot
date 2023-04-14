package com.example.workflow.camunda.userTasks.rideBooking;

import com.example.workflow.camunda.core.CamundaCoreService;
import com.example.workflow.camunda.userTasks.UserTask;
import com.example.workflow.config.MessageTemplate;
import com.example.workflow.dto.SendMessageRequestDto;
import com.example.workflow.models.User;
import com.example.workflow.models.gupshup.WebhookMessagePayload;
import com.example.workflow.services.MessageService;
import com.example.workflow.services.TemplateService;
import com.example.workflow.utils.Constants;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.workflow.utils.Constants.GLOBAL_CANCELLATION_MESSAGE_EVENT_NAME;

@Service
public class CancelBookingByCx implements UserTask {

    @Autowired
    CamundaCoreService camundaCoreService;
    @Autowired
    MessageService messageService;
    @Autowired
    TemplateService templateService;

    @Override
    public void complete(Task task, User user, String messageType, WebhookMessagePayload webhookMessagePayload) throws Exception {
        if (Objects.equals(messageType, Constants.MESSAGE_TYPE_BUTTON_REPLY)) {
            Map<String, Object> variables = new HashMap<>();
            variables.put("cancel_ride_request", true);
            variables.put("global_cancellation",true);
            //TODO: Get the Process definition name from config.
            String cancelMessageEventName = GLOBAL_CANCELLATION_MESSAGE_EVENT_NAME.get("Ride_Update_Flow");
            camundaCoreService.createMessageCorrelation(user.getPhoneNumber(), cancelMessageEventName, variables);
            camundaCoreService.completeUserTaskByTaskId(task, variables);
        } else {
            messageService.sendTextMessage(new SendMessageRequestDto(user.getPhoneNumber(), templateService.format(MessageTemplate.RIDE_INVALID_MESSAGE, user.getPreferredLanguage())));
        }
    }
}
