package example.demo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
    @Insert("INSERT INTO account (username, password) VALUES (#{username}, #{password})")
    void register(Account account);

    @Select("SELECT password FROM account WHERE username = #{username}")
    String getPasswordByUsername(@Param("username") String username);

    @Select("SELECT id, username, age, phone FROM user WHERE username = #{username}")
    User getUserByName(@Param("username") String username);
}
