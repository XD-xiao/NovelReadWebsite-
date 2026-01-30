package com.example.springtemplate;

import com.example.springtemplate.Pojo.User;
import com.example.springtemplate.Service.EmailService;
import com.example.springtemplate.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class SpringTemplateApplicationTests {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        String result = emailService.addCode("2673861359@qq.com","注册服务");
        System.out.println(result);
    }

    @Test
    void testAddUser() {
        List<User> users = new ArrayList<>();

        String[] names = {"张三", "李四", "王五", "赵六", "钱七", "孙八", "周九", "吴十",
                "郑一", "冯二", "陈三", "褚四", "卫五", "蒋六", "沈七"};
        String[] domains = {"@qq.com", "@163.com", "@gmail.com", "@outlook.com", "@yahoo.com"};
        String[] genders = {"男", "女", "other"};

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            // 名字可以重复，但加点变化
            String name = names[random.nextInt(names.length)] + (random.nextBoolean() ? "_" + (i + 1) : "");

            // ✅ 关键修改：邮箱 = 随机10位数字 + 随机域名
            long randomEmailNum = 1000000000L + random.nextLong() % 9000000000L; // 10位数字（1,000,000,000 到 9,999,999,999）
            // 为避免负数，更安全的做法：
            String emailNum = String.format("%010d", Math.abs(random.nextLong() % 10000000000L));
            String email = emailNum + domains[random.nextInt(domains.length)];

            String gender = genders[random.nextInt(genders.length)];
            LocalDateTime createTime = LocalDateTime.now().minusMinutes(random.nextInt(60 * 24 * 30)); // 最近30天内

            users.add(new User(name, email, gender, createTime));
        }

        for (User user : users) {
            if (!userService.addUser(user)) {
                System.out.println("添加用户失败 => " + user);
            }
        }
        System.out.println("批量添加用户over");
    }

}
