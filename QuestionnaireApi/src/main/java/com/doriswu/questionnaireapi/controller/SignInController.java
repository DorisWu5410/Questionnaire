package com.doriswu.questionnaireapi.controller;

import com.doriswu.questionnaireapi.entity.User;
import com.doriswu.questionnaireapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
public class SignInController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @RequestMapping("/login")
    public void login(@RequestParam(value = "error") String error, HttpServletResponse response) throws Exception{
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("message", "bad credentials");
        response.getOutputStream()
                .println(objectMapper.writeValueAsString(data));
    }

}
