package login.domain;

import login.infra.AbstractEvent;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Logined extends AbstractEvent{
    private Long id;
    private String userId;
    private String userName;
    private Integer userMoney;
    private boolean userSubscribeStatus;

    public Logined(Login aggregate) {
        super(aggregate);
    }

    public Logined() {
        super();
    }
}

