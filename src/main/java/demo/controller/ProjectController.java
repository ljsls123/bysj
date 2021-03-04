package demo.controller;

import javax.servlet.http.HttpSession;

import demo.model.Project;
import demo.model.User;
import demo.model.response.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    @GetMapping(value = "/create")
    public String toCreateProject() {
        return "createProject";
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseResult<Void> createProject(Project project, HttpSession session) {
        User user = (User) session.getAttribute("user");
        project.setUserId(user.getId());
        project.setStage("0");
        return ResponseResult.success();
    }
}
