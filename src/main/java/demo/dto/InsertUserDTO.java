package demo.dto;

import lombok.Data;

@Data
public class InsertUserDTO {
    private String telephone;

    private String name;

    private String email;

    private String address;

    private String password;

    private String gender;

    private String type;

}
