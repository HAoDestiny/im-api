package com.tlink.im.controller;

import com.tlink.common.core.domain.ApiResponse;
import com.tlink.common.core.domain.R;
import com.tlink.dao.domain.User;
import com.tlink.im.BaseController;
import com.tlink.im.from.UserFrom;
import com.tlink.service.IUserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author destiny
 * @date 2021/6/9 13:53
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid UserFrom userFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return response(R.fail(ApiResponse.PARAM_FAIL, bindingResult.getFieldErrors().get(0).getDefaultMessage()));
        }

        return response(userService.register(
                new User().setAccount(userFrom.getMobile())
                .setTelephone(userFrom.getMobile())
                .setEmail(userFrom.getEmail())
                .setGender(userFrom.getGender())
                .setMotto(userFrom.getMotto())
                .setName(userFrom.getName())
                .setFeature(userFrom.getFeature())
                .setGrade(userFrom.getGrade())
        ));
    }

}
