package example.demo;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (username, age, phone) VALUES (#{username}, #{age}, #{phone})")
    void insertUser(User user);

    @Select("SELECT id, username, age, phone FROM user WHERE id = #{userId}")
    User getUserById(@Param("userId") Long userId);

    @Delete("DELETE FROM user WHERE id = #{userId}")
    void deleteUserById(@Param("userId") Long userId);

    @Update("UPDATE user SET username = #{username}, age = #{age}, phone = #{phone} WHERE id = #{id}")
    void updateUser(User user);

}