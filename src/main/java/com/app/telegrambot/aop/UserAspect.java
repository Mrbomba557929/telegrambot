package com.app.telegrambot.aop;

import com.app.telegrambot.meta.objects.Update;
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

    /**
     * The method is needed to add a telegram user to the database
     *
     * @param joinPoint is the {@link Update} object.
     */
    @Before("execution(* *.execute(com.app.telegrambot.meta.objects.Update))")
    public void saveUserBefore(JoinPoint joinPoint) {
        Update update = (Update) joinPoint.getArgs()[0];

        if (!userService.existsById(update.message().from().idLong())) {
            userService.save(update.message().from());
        }
    }
}
