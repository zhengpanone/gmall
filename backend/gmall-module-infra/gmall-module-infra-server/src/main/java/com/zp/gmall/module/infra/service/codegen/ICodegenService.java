package com.zp.gmall.module.infra.service.codegen;

import java.util.Map;

public interface ICodegenService {
    Map<String, String> generationCodes(String tableId);
}
