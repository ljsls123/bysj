package demo.vo;

import demo.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUsersVO {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private String telephone;

    private String gender;

    private String address;

    private String salt;

    private String role;

    public GetUsersVO(User user, String role) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.telephone = user.getTelephone();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.salt = user.getSalt();
        this.role = role;
    }
}
