package cn.cestc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {

    @GetMapping("/login")
    public AjaxResult Login(@RequestParam("username") String username,
                            @RequestParam("password") String password){
        return null;

    }
}

