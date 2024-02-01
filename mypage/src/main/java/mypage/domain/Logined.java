package mypage.domain;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@MappedSuperclass
public class Logined {
    private Long id = 1L;

    private String userId = "testUser1";

    private String userName ="testName";
    
    private Integer userMoney = 0;

    private boolean userSubscribeStatus = false;


}
