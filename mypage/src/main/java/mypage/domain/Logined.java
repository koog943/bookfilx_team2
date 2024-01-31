package mypage.domain;



import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Embeddable
public class Logined {
    @Id
    private Long id;

    private String userId;

    private String userName;
    
    private Integer userMoney;

    private boolean userSubscribeStatus;

}
