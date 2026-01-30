package com.example.springtemplate.Controller;

import com.example.springtemplate.Dto.GetVerificationCodeDto;
import com.example.springtemplate.Dto.LoginDto;
import com.example.springtemplate.Dto.RegisterDto;
import com.example.springtemplate.Pojo.Result;
import com.example.springtemplate.Pojo.User;
import com.example.springtemplate.Service.EmailService;
import com.example.springtemplate.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/api/user/register")
    public Result register(@RequestBody RegisterDto registerDto) {
        // 邮箱格式校验
        if (!registerDto.getEmail().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {
            return Result.error("邮箱格式错误");
        }

        // 用户名：支持中文、英文、数字、下划线等，总长度不超过30个字符（注意：一个中文字符算1个）
        if (registerDto.getUsername() == null || registerDto.getUsername().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (!registerDto.getUsername().matches("^[\\u4e00-\\u9fa5a-zA-Z0-9_-]{1,30}$")) {
            return Result.error("用户名格式错误，仅支持中文、英文、数字、下划线和短横线，且不超过30个字符");
        }

        // 性别：只能是 "男"、"女" 或 "other"
        String gender = registerDto.getGender();
        if (gender == null || !(gender.equals("男") || gender.equals("女") || gender.equals("other"))) {
            return Result.error("性别只能为“男”、“女”或“other”");
        }


        // 判断验证码是否正确
        if (!emailService.verifyCode(registerDto.getEmail(), registerDto.getVerificationCode())) {
            return Result.error("验证码错误");
        }

        User user = new User(
                registerDto.getUsername(),
                registerDto.getEmail(),
                registerDto.getGender(),
                LocalDateTime.now()
        );

        if (!userService.addUser(user)) {
            return Result.error("注册失败");
        }


        return Result.success("注册成功");
    }

    @PostMapping("/api/user/getVerificationCode")
    public Result getVerificationCode(@RequestBody GetVerificationCodeDto getVerificationCodeDto){

//        System.out.println("发送邮箱验证码");

        //判断邮箱格式
        if (!getVerificationCodeDto.getEmail().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")){
            return Result.error("邮箱格式错误");
        }
        String result = emailService.addCode(getVerificationCodeDto.getEmail(),"注册服务");
        if( result == null)
            return Result.error("发送验证码失败");

        return Result.success(result);
    }

    @PostMapping("/api/user/login")
    public Result login(@RequestBody LoginDto loginDto){

        if (!loginDto.getEmail().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")){
            return Result.error("邮箱格式错误");
        }
        if (!emailService.verifyCode(loginDto.getEmail(), loginDto.getVerificationCode())){
            return Result.error("验证码错误");
        }
        String token = userService.login(loginDto.getEmail());
        if (token == null)
            return Result.error("用户不存在");
        return Result.success(token);
    }
}
