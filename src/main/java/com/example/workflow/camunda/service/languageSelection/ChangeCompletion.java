package com.example.workflow.camunda.service.languageSelection;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class ChangeCompletion implements JavaDelegate {

    private final Logger log = Logger.getLogger(ChangeCompletion.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try{
            //call gupshup to send message
            log.info("ChangeCompletion: execute method is called......");
            //set relevant variables for future ref
            execution.setVariable("ChangeCompletion", true);
        } catch (Exception e){
            log.warning("ChangeCompletion: Exception occured......");
            throw new BpmnError("booking_flow_error","Error sending message.....");
        }
    }
}