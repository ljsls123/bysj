package demo.service.Impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.constant.ErrorCode;
import demo.dto.InsertUserDTO;
import demo.exception.BizException;
import demo.mapper.*;
import demo.model.*;
import demo.model.response.ResponseResult;
import demo.service.UserService;
import demo.util.EncryptUtil;
import demo.util.ValidateUtil;
import demo.vo.GetUsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public ResponseResult<Void> insertUser(InsertUserDTO insertUserDTO) {
        if (ValidateUtil.emailFormatCheck(insertUserDTO.getEmail())) {
            throw new BizException(ErrorCode.EMAIL_FORMAT_ERROR,
                    String.format("input email: %s format is wrong when Register.", insertUserDTO.getEmail()));
        }
        if (ValidateUtil.passwordFormatCheck(insertUserDTO.getPassword())) {
            throw new BizException(ErrorCode.PASSWORD_FORMAT_ERROR,
                    String.format("input password: %s format is wrong when Register.", insertUserDTO.getPassword()));
        }
        User user = new User();
        user.setEmail(insertUserDTO.getEmail());
        String salt = EncryptUtil.generateSalt();
        user.setPassword(EncryptUtil.encryptPassword(insertUserDTO.getPassword(), salt));
        user.setSalt(salt);
        user.setAddress(insertUserDTO.getAddress());
        user.setName(insertUserDTO.getName());
        user.setGender(insertUserDTO.getGender());
        user.setTelephone(insertUserDTO.getTelephone());
        int userId = -1;
        try {
            userId = userMapper.insertUser(user);
        } catch (DuplicateKeyException e) {
            throw new BizException(ErrorCode.EMAIL_EXISTED_ERROR,
                    String.format("email: %s have registered already when Register.", insertUserDTO.getEmail()));
        }
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(Integer.valueOf(insertUserDTO.getType()));
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<User> login(String telephone, String password) {
        if (ValidateUtil.passwordFormatCheck(password)) {
            throw new BizException(ErrorCode.PASSWORD_FORMAT_ERROR,
                    String.format("password: %s format is wrong when login.", password));
        }
        User user = userMapper.selectByTelephone(telephone);
        if (user == null) {
            throw new BizException(ErrorCode.EMAIL_NOT_EXISTED_ERROR,
                    "the user does not existed.");
        }
        if (!EncryptUtil.encryptPassword(password, user.getSalt())
                .equals(user.getPassword())) {
            throw new BizException(ErrorCode.PASSWORD_WRONG_ERROR,
                    String.format("password: %s is wrong when login.", password));
        }
        return ResponseResult.success(user);
    }

    @Override
    public ResponseResult<List<Menu>> getMenu(int userId) {
        UserRole userRole = userRoleMapper.selectByUserId(userId);
        List<RoleMenu> parentMenu = roleMenuMapper.selectParentByRoleId(userRole.getRoleId());
        List<Menu> menuList = new ArrayList<>();
        Map<Integer, Menu> map = new HashMap<>();
        List<Menu> menus = new ArrayList<>();
        for (RoleMenu roleMenu : parentMenu) {
            Menu menu = menuMapper.selectByid(roleMenu.getMenuId());
            menuList.add(menu);
        }
        for (Menu menu : menuList) {
            if (menu.getPid() == 0) {
                map.put(menu.getId(), menu);
            }
        }
        for (Menu child : menuList) {
            if (child.getPid() != 0) {
                map.get(child.getPid()).getChild().add(child);
            }
        }
        menus = new ArrayList<>(map.values());
        return ResponseResult.success(menus);
    }

    @Override
    public ResponseResult<Void> updatePassword(String telephone, String oldPassword, String newPassword) {
        if (ValidateUtil.passwordFormatCheck(oldPassword)) {
            throw new BizException(ErrorCode.OLD_PASSWORD_FORMAT_ERROR, String
                    .format("oldPassword: %s format wrong when updatePassword", oldPassword));
        }
        if (ValidateUtil.passwordFormatCheck(newPassword)) {
            throw new BizException(ErrorCode.NEW_PASSWORD_FORMAT_ERROR, String
                    .format("newPassword: %s format wrong when updatePassword", newPassword));
        }
        User user = userMapper.selectByTelephone(telephone);
        if (!user.getPassword().equals(EncryptUtil
                .encryptPassword(oldPassword, user.getSalt()))) {
            throw new BizException(ErrorCode.OLD_PASSWORD_WRONG_ERROR, String
                    .format("oldPassword: %s is wrong when updatePassword.", oldPassword));
        }
        user.setPassword(EncryptUtil.encryptPassword(newPassword, user.getSalt()));
        userMapper.updateUser(user);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<List<Role>> getRole() {
        List<Role> list = roleMapper.getRole();
        return ResponseResult.success(list);
    }

    @Override
    public ResponseResult<List<GetUsersVO>> getUsers(int page) {
        List<User> userList = userMapper.getAllUsers((page - 1) * 10);
        List<GetUsersVO> getUsersVOList = new ArrayList<>();
        for (User user : userList) {
            UserRole userRole = userRoleMapper.selectByUserId(user.getId());
            GetUsersVO getUsersVO = new GetUsersVO(user, roleMapper.selectById(userRole.getRoleId()));
            getUsersVOList.add(getUsersVO);
        }
        return ResponseResult.success(getUsersVOList);
    }

    @Override
    public ResponseResult<Integer> getUsersTotalPage() {
        int total = userMapper.getAllUsersCount();
        int totalPage = (total / 10) + 1;
        return ResponseResult.success(totalPage);
    }

    @Override
    public ResponseResult<Void> deleteUser(int id) {
        userMapper.deleteUser(id);
        userRoleMapper.deleteUser(id);
        return ResponseResult.success();
    }
}
