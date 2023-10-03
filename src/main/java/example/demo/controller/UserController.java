package example.demo.controller;

import example.demo.User;
import example.demo.AccountMapper;
import example.demo.UserMapper;
import example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import example.demo.Account;


@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final AccountMapper accountMapper;

    @Autowired
    public UserController(UserService userService, AccountMapper accountMapper) {
        this.userService = userService;
        this.accountMapper = accountMapper;
    }


    @PostMapping("/login")
    public User userLogin(@RequestBody Account account) {
        logger.info("account: ", account);
        boolean result = userService.useLogin(account);
        if(result) {
            String username = account.getUsername();
            return accountMapper.getUserByName(username);
        } else {
            return null;
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody Account account) {
        userService.register(account);
        return "register success";
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("张三");
        user.setPhone("18790671234");
        user.setAge(18);

        return user;
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user) {

        logger.info("Response user: {}", user.getUsername());
        /**
         * TODO： 将数据插入到数据库中
         */
        userService.createUser(user);
        return "create success";
    }

    @GetMapping("/getUser/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return "delete success";
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody User user) {
        System.out.println("update*****");
        userService.updateUser(user);
        return "update success";
    }
}
