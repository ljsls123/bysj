package demo.mapper;

import java.util.List;

import demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int insertUser(User user);

    User selectByTelephone(String telephone);

    void updateUser(User user);

    List<User> getAllUsers(int num);

    int getAllUsersCount();

    void deleteUser(int id);
}
