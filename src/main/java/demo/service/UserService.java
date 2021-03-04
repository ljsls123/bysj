package demo.service;


import java.util.List;

import demo.dto.InsertUserDTO;
import demo.model.Menu;
import demo.model.Role;
import demo.model.User;
import demo.model.response.ResponseResult;
import demo.vo.GetUsersVO;

public interface UserService {
    ResponseResult<Void> insertUser(InsertUserDTO insertUserDTO);

    ResponseResult<User> login(String telephone, String password);

    ResponseResult<List<Menu>> getMenu(int userId);

    ResponseResult<Void> updatePassword(String telephone, String oldPassword, String newPassword);

    ResponseResult<List<Role>> getRole();

    ResponseResult<List<GetUsersVO>> getUsers(int page);

    ResponseResult<Integer> getUsersTotalPage();

    ResponseResult<Void> deleteUser(int id);
}
