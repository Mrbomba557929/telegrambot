package com.app.telegrambot.aop;

import com.app.telegrambot.domain.bot.response.Update;
import com.app.telegrambot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UserAspect {

    private final UserService userService;

    @Before("execution(* *.execute(com.app.telegrambot.domain.bot.response.Update))")
    public void saveUserBefore(JoinPoint joinPoint) {
        Update update = (Update) joinPoint.getArgs()[0];

        if (!userService.existsById(update.message().from().id())) {
            userService.save(update.message().from());
        }
    }
}
