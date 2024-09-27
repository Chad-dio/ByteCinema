package org.chad.bytecinema.controller;

import lombok.RequiredArgsConstructor;
import org.chad.bytecinema.domain.entity.Result;
import org.chad.bytecinema.service.user.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bytecinema/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 关注/取关
     * @param followId 被关注用户的email
     * @return Boolean 关注成功与否
     */
    @PostMapping("/follow")
    public Result follow(@RequestParam String followId){
        return Result.success(userService.followOrUnfollow(followId));
    }

    @GetMapping("/getFans")
    public Result getFans(@RequestParam String userId, @RequestParam Integer pageNo, @RequestParam Integer pageSize){
        return Result.success(userService.getFans(userId, pageNo, pageSize));
    }
}
