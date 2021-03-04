package demo.mapper;

import demo.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleMapper {

    UserRole selectByUserId(int userId);

    void insert(UserRole userRole);

    void deleteUser(int userId);
}
