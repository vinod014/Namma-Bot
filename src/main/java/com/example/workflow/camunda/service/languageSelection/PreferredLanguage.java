package com.example.workflow.camunda.service.languageSelection;

import com.example.workflow.config.MessageTemplate;
import com.example.workflow.config.PostBackText;
import com.example.workflow.dto.SendQuickReplyMessageDto;
import com.example.workflow.models.User;
import com.example.workflow.models.gupshup.MessageContent;
import com.example.workflow.services.MessageService;
import com.example.workflow.services.TemplateService;
import com.example.workflow.services.UserService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

import static com.example.workflow.utils.Constants.MESSAGE_TYPE_QUICK_REPLY;

@Service
public class PreferredLanguage implements JavaDelegate {

    private final Logger log = Logger.getLogger(PreferredLanguage.class.getName());
    @Autowired
    MessageService messageService;
    @Autowired
    TemplateService templateService;
    @Autowired
    UserService userService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Executing Service Task " + this.getClass().getName() + " For Business Key: " + execution.getBusinessKey());

        try {
            User user = userService.findUserByPhoneNumber(execution.getBusinessKey()).orElse(null);

            SendQuickReplyMessageDto rideSelectionMessage = new SendQuickReplyMessageDto();
            rideSelectionMessage.setReceiverContactNumber(user.getPhoneNumber());
            rideSelectionMessage.setType(MESSAGE_TYPE_QUICK_REPLY);

            List<Map<String, String>> options = new ArrayList<>(new ArrayList<>(Arrays.asList(
                    new HashMap<>() {{
                        put("type", "text");
                        put("title", "ಕನ್ನಡ");
                        put("postbackText", PostBackText.LANG_PREF_KANNADA.getPostBackText());
                    }},
                    new HashMap<>() {{
                        put("type", "text");
                        put("title", "हिंदी");
                        put("postbackText", PostBackText.LANG_PREF_HINDI.getPostBackText());
                    }},
                    new HashMap<>() {{
                        put("type", "text");
                        put("title", "English");
                        put("postbackText", PostBackText.LANG_PREF_ENGLISH.getPostBackText());
                    }}
            )));

            rideSelectionMessage.setQuickReplyMessage(messageService.generateQuickReplyMessage(
                    new MessageContent(
                            templateService.format(MessageTemplate.LANGUAGE_UPDATE_PREFERENCE_HEADER, user.getPreferredLanguage()),
                            templateService.format(MessageTemplate.LANGUAGE_UPDATE_PREFERENCE_BODY,
                                    user.getPreferredLanguage())
                    ), options, UUID.randomUUID().toString()));

            messageService.sendQuickReplyMessage(rideSelectionMessage);
        } catch (Exception e) {
            log.warning("Exception occurred in Service Activity : " + this.getClass().getName() + " " + e.getMessage());
            throw new BpmnError("language_flow_error", "Error sending message.....");
        }
    }
}