package mypage.infra;

import lombok.Data;

@Data
public class UserChangeEvent extends AbstractEvent {

    private Integer userMoney;
    private boolean userStatus;

    public UserChangeEvent(Object aggregate, Integer userMoney, boolean userStatus) {
        super(aggregate);
        this.userMoney = userMoney;
        this.userStatus = userStatus;
    }
}
