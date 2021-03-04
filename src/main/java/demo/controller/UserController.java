package demo.controller;


import java.util.List;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import demo.dto.InsertUserDTO;
import demo.model.Menu;
import demo.model.Role;
import demo.model.User;
import demo.model.response.ResponseResult;
import demo.service.UserService;
import demo.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String toLogin() {
        return "login";
    }

    @GetMapping(value = "/insertUser")
    public String toInsertUser(Model model) {
        List<Role> list = userService.getRole().getResult();
        model.addAttribute("role", list);
        return "insertuser";
    }

    @PostMapping(value = "/insertUser")
    @ResponseBody
    public ResponseResult<Void> insertUser(InsertUserDTO insertUserDTO) {
        return userService.insertUser(insertUserDTO);
    }

    @GetMapping(value = "/main")
    public String toMain() {
        return "main";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseResult<Void> login(String telephone, String password, HttpSession session) {
        User user = userService.login(telephone, password).getResult();
        List<Menu> menuList = userService.getMenu(user.getId()).getResult();
        session.setAttribute("menuList", new Gson().toJson(Utils.getMenuJson(menuList)));
        session.setAttribute("user", user);
        return ResponseResult.success();
    }

    @GetMapping(value = "/getUserInfo")
    public String getUserInfo(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "userinfo";
    }

    @GetMapping(value = "/updatePassword")
    public String toUpdatePassword() {
        return "updatepassword";
    }

    @PostMapping(value = "/updatePassword")
    public ResponseResult<Void> updatePassword(String oldPassword, String newPassword, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userService.updatePassword(user.getTelephone(), oldPassword, newPassword);
    }

    @GetMapping(value = "/getUsers")
    public String getUsers(@RequestParam("page") int page, Model model) {
        model.addAttribute("userList", userService.getUsers(page).getResult());
        model.addAttribute("totalPage", userService.getUsersTotalPage().getResult());
        model.addAttribute("page", page);
        return "getusers";
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseResult<Void> deleteUser(int id) {
        userService.deleteUser(id);
        return ResponseResult.success();
    }
}
