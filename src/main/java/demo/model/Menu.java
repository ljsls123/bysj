package demo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Menu {
    private Integer id;

    private Integer pid;

    private String url;

    private String name;

    private List<Menu> child = new ArrayList<>();

}
