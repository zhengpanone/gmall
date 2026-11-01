package com.zp.gmall.module.ai.service.impl;


import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.zp.gmall.module.ai.service.IAgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgentServiceImpl implements IAgentService {

    private final ReactAgent reactAgent;

    @Override
    public String chat(String input) {
//        return reactAgent.call(input).getContent();
        return "";
    }
}
