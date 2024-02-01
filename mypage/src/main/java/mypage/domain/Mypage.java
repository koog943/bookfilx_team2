package mypage.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mypage.MyPageApplication;

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
    private Logined logined = new Logined();


    public static MypageRepository repository() {
        MypageRepository bookRepository = MyPageApplication.applicationContext.getBean(
            MypageRepository.class
        );
        return bookRepository;
    }

    public String getUserId() {
        return logined.getUserId();
    }
    public void setUserId(String userId) {
        logined.setUserId(userId);
    }

    public String getUserName() {
        return logined.getUserName();
    }

    public void setUserName(String userName) {
        logined.setUserName(userName);
    }

    public Integer getUserMoney() {
        return logined.getUserMoney();
    }

    public void setUserMoney(Integer userMoney) {
        logined.setUserMoney(userMoney);
    }

    public boolean isUserSubscribeStatus() {
        return logined.isUserSubscribeStatus();
    }

    public void setUserSubscribeStatus(boolean userSubscribeStatus) {
        logined.setUserSubscribeStatus(userSubscribeStatus);
    }


}
