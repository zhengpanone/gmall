package com.zp.gmall.module.ai.controller;

import com.alibaba.cloud.ai.graph.agent.ReactAgent;

import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhengpanone
 * Date : 2026/3/18 00:21
 * Version : v1.0.0
 * Description:
 */
@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor
public class AgentController {

    private final ReactAgent agent;

    @GetMapping("/chat")
    public AssistantMessage chat(@RequestParam(defaultValue = "你是谁") String prompt) throws GraphRunnerException {
        return agent.call(prompt);
    }
}
