package demo.mapper;

import java.util.List;

import demo.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMenuMapper {

    List<RoleMenu> selectParentByRoleId(int roleId);

}
