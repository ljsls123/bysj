package demo.mapper;

import java.util.List;

import demo.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MenuMapper {

    Menu selectByid(int id);

    List<Menu> selectByPid(int pid);
}
