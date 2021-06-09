package com.tlink.im.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tlink.common.core.domain.R;
import com.tlink.im.BaseController;
import com.tlink.im.from.MessageFrom;
import com.tlink.service.IMessageService;
import org.springframework.web.bind.annotation.*;

/**
 * @author destiny
 * @date 2021/6/3 13:19
 */

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    private final IMessageService messageService;

    public TestController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{msg}")
    public String test(@PathVariable(value = "msg") String msg) {
        return msg;
    }

    @GetMapping("/selectMessage")
    public String selectMessage() {
        return response(R.ok(messageService.queryAll()));
    }

    @GetMapping("/selectMessagePage/{current}/{size}")
    public String selectMessagePage(@PathVariable(value = "current") Integer current, @PathVariable(value = "size") Integer size) {
        return response(R.ok(messageService.queryMessage(new Page<>(current, size))));
    }

    @PostMapping("/selectMessagePage")
    public String selectMessagePage(@RequestBody MessageFrom messageFrom) {
        return response(R.ok(messageService.queryMessage(getPage(messageFrom))));
    }
}
