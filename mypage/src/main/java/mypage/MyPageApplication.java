package mypage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import mypage.domain.Mypage;
import mypage.domain.MypageRepository;
import mypage.infra.UserService;

@SpringBootApplication
@EnableFeignClients
public class MyPageApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =
            SpringApplication.run(MyPageApplication.class, args);
            addUser();
            // test();
    }

    // private static void test() {
    //     MypageRepository mypageRepository = mypage.domain.Mypage.repository();
    //     UserService userService = new UserService(mypageRepository);

    //     try {

    //         for (int i = 1; i <= 5; i++) {
    //             Mypage mypage = new Mypage();
    //             mypage.setUserId("userId" + i);
    //             mypage.setUserName("test" + i);
    //             mypage.setUserMoney(10000);
    //             mypage.setUserSubscribeStatus(i % 2 == 1); // 홀수면 true, 짝수면 false
    //             // 새로운 사용자를 추가
    //             mypageRepository.save(mypage);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace(); // 예외를 콘솔에 출력
    //     }

    // }


    private static void addUser(){
        MypageRepository mypageRepository = mypage.domain.Mypage.repository();
        UserService userService = new UserService(mypageRepository);

        try {

            for(int i = 1; i <= 5; i++){
                Mypage mypage = new Mypage();
                mypage.setUserId("userId" + i);
                mypage.setUserName("test" + i);
                mypage.setUserMoney(i * 10000);
                mypage.setUserSubscribeStatus(false);
                userService.save(mypage);
            }

        } catch (Exception e) {

        

        }

        

    }
}

