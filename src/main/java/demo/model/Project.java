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

    /**
     * 0:提交
     * 1:提交后未通过
     * 2:提交后通过
     * 3:开题报告未通过
     * 4:开题报告通过/立项
     * 5:中期材料未通过
     * 6:中期材料通过/结项
     * 7:结项未通过
     * 8:结项通过
     */
    private String stage;

    private String firstUrl;

    private String secondUrl;

    private String lastUrl;

}
