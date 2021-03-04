package demo.model;

import lombok.Data;

@Data
public class ProjectScore {
    private Integer id;

    private String firstScore;

    private Integer firstUserId;

    private String secondScore;

    private Integer secondUserId;

    private String thirdScore;

    private Integer thirdUserId;

    private String lastScore;

    private Integer lastUserId;
}
