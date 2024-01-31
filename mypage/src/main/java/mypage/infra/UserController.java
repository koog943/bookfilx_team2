package mypage.infra;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

// import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mypage.domain.Mypage;


import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Mypage create(@RequestBody Mypage user) {
        return userService.save(user);
    }

    // @Operation(summary = "전체 사용자 조회", description = "사용자 목록 조회입니다")
    // @ApiResponses(value = {
    //     @ApiResponse(responseCode = "200", description = "성공", content = {
    //         @Content(
    //           mediaType = "application/json",
    //           array = @ArraySchema(schema = @Schema(implementation = User.class)))
    //       })
    // })

    @GetMapping
    public List<Mypage> readAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Mypage readOne(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public Mypage update(@PathVariable Long id, @RequestBody Mypage newUser) {
        Mypage user = userService.findById(id);
        if (user != null) {
            user.setUserName(newUser.getUserName());
            return userService.save(user);
        }
        return null;
    }

   
    @GetMapping("/{id}/checkSubscrib")
    public ResponseEntity<Object> checkSubscription(@PathVariable Long id) {
        Mypage user = userService.findById(id);

        if (user != null) {
            if (user.isUserSubscribeStatus()== true) {
                return ResponseEntity.ok("사용자는 현재 구독 중입니다.");
            } else {
                return ResponseEntity.ok("사용자는 현재 비구독 중입니다. 결제창으로 이동?");
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/subscribe")
    public ResponseEntity<Object> subscribe(@PathVariable Long id) {
        Mypage user = userService.findById(id);

        if(user != null){

            if (user.getUserMoney() >= 10000) {
                user.setUserMoney(user.getUserMoney() - 10000);
                user.setUserSubscribeStatus(true);
                userService.save(user);
                return ResponseEntity.ok("구독 완료. 현재 금액 : "+user.getUserMoney());
            } else{
                return ResponseEntity.ok("구독 실패. 현재 금액 : "+user.getUserMoney());
            }
            
        }
        return null;

    }


    @PutMapping("/{id}/addMoney")
    public ResponseEntity<Object> addMoney(@PathVariable Long id , @RequestBody Integer addMoney) {
        Mypage user = userService.findById(id);


        if(user != null){

            user.setUserMoney(user.getUserMoney() + addMoney);
            userService.save(user);


            return ResponseEntity.ok("금액 추가 되었습니다.");
            
        }
        return ResponseEntity.notFound().build();

    }

    // @GetMapping("/{id}/money")
    // public ResponseEntity<Object> getUserMoney(@PathVariable Long id) {

    //     Mypage user = userService.findById(id);

    //     if (user != null) {

    //         int userMoney = user.getUserMoney();
    //         return ResponseEntity.ok(userMoney);
    //     }
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    // }

    @PutMapping("/{id}/editname")
    public ResponseEntity<Object> editName(@PathVariable Long id , @RequestBody String userName) {
        Mypage user = userService.findById(id);


        if(user != null){

            user.setUserName(userName);
            userService.save(user);

            return ResponseEntity.ok(user.getUserName()+"으로 변경 되었습니다.");
            
        }
        return ResponseEntity.notFound().build();

    }
}

