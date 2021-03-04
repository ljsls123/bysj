package demo.mapper;

import java.util.List;

import demo.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper {

    List<Role> getRole();

    String selectById(int id);
}
