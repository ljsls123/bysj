package demo.model;

import lombok.Data;

@Data
public class Project {
    private Integer id;

    private Integer userId;

    private String title;

    private String type;

    private String keyWords;

    private String detail;

    private String money;

    private String stage;

    private String firstUrl;

    private String secondUrl;

    private String lastUrl;

}
