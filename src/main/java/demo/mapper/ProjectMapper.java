package demo.mapper;

import demo.model.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProjectMapper {

    void insert(Project project);
}
