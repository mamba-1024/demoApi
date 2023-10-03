package example.demo.service;

import example.demo.User;
import example.demo.UserMapper;
import example.demo.AccountMapper;
import example.demo.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import example.demo.Account;
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserMapper userMapper;

    private final AccountMapper accountMapper;

    @Autowired
    public UserService(UserMapper userMapper, AccountMapper accountMapper) {
        this.userMapper = userMapper;
        this.accountMapper = accountMapper;
    }

    public boolean useLogin(Account login) {
        String username = login.getUsername();
        String storedPassword = accountMapper.getPasswordByUsername(username);
        logger.info("Response username: ", username);
        logger.info("storedPassword: ", storedPassword);
        if (storedPassword != null && storedPassword.equals(login.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public void register(Account account) {
        accountMapper.register(account);
    }

    public void createUser(User user) {
        userMapper.insertUser(user);
    }

    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }

    // 删除
    public void deleteUserById(Long userId) {
        userMapper.deleteUserById(userId);
    }

    // 更新
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
