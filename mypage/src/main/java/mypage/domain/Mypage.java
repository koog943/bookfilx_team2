package mypage.domain;


import javax.persistence.*;

import org.apache.kafka.common.security.auth.Login;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mypage.MyPageApplication;
import mypage.infra.UserChangeEvent;

//<<< EDA / CQRS
@Entity
@Data
public class Mypage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Embedded
    @Getter
    @Setter
    private Logined logined;

    public void changeUserStatus() {
        UserChangeEvent event = new UserChangeEvent(this, this.getUserMoney(), this.isUserSubscribeStatus());
        event.publishAfterCommit(); 
    }

    public static MypageRepository repository() {
        MypageRepository bookRepository = MyPageApplication.applicationContext.getBean(
            MypageRepository.class
        );
        return bookRepository;
    }

    public String getUserId() {
        return this.logined.getUserId();
    }

    public void setUserId(String userId) {
        this.logined.setUserId(userId);
    }

    public String getUserName() {
        return this.logined.getUserName();
    }

    public void setUserName(String userName) {
        this.logined.setUserName(userName);
    }

    public Integer getUserMoney() {
        return this.logined.getUserMoney();
    }

    public void setUserMoney(Integer userMoney) {
        this.logined.setUserMoney(userMoney);
    }

    public boolean isUserSubscribeStatus() {
        return this.logined.isUserSubscribeStatus();
    }

    public void setUserSubscribeStatus(boolean userSubscribeStatus) {
        this.logined.setUserSubscribeStatus(userSubscribeStatus);
    }


}
