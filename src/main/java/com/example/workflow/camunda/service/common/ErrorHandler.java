package com.example.workflow.camunda.service.common;

import com.example.workflow.services.MessageService;
import com.example.workflow.services.UserService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ErrorHandler implements JavaDelegate {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    private final Logger log = Logger.getLogger(ErrorHandler.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try {
            //call gupshup to send message
            log.info("ErrorHandler: execute method is called......");
            messageService.sendErrorMessage(execution.getBusinessKey());
            //set relevant variables for future ref
            execution.setVariable("ErrorHandler", true);
        } catch (Exception e) {
            log.warning("ErrorHandler: Exception occurred......");
            throw new BpmnError("booking_flow_error", "Error sending message.....");
        }
    }
}