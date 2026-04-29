package com.zp.module.bpm.controller.admin.task;

import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.module.bpm.controller.admin.task.dto.BpmTaskPageDTO;
import com.zp.module.bpm.controller.admin.task.vo.BpmTaskVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "流程任务管理", description = "流程任务管理")
@RestController
@RequestMapping("/task")
public class BpmTaskController {

    @GetMapping("todo-page")
    @Operation(summary = "获取 Todo 待办任务分页")
    public PageResult<BpmTaskVO> getTaskTodoPage(@Valid BpmTaskPageDTO pageDTO) {
//        PageResult<Task> pageResult = taskService.getTaskTodoPage(getLoginUserId(), pageVO);
//        if (CollUtil.isEmpty(pageResult.getList())) {
//            return success(PageResult.empty());
//        }
        return PageResult.ok(1L, null);
    }
}
