package demo.service.Impl;

import demo.mapper.ProjectMapper;
import demo.model.Project;
import demo.model.response.ResponseResult;
import demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public ResponseResult<Void> createProject(Project project) {
        projectMapper.insert(project);
        return ResponseResult.success();
    }
    
}
