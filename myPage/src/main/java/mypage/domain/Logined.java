package mypage.domain;

import lombok.Data;

@Data
public class Logined {
    private Long id;

    private String userId;

    private String userName;
    
    private Integer userMoney;

    private boolean userSubscribeStatus;

}
