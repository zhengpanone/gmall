package com.zp.gmall.module.infra.service.codegen.impl;

import com.zp.gmall.module.infra.service.codegen.ICodegenService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CodegenServiceImpl implements ICodegenService {

    @Override
    public Map<String, String> generationCodes(String tableId) {
        return Map.of();
    }
}
