package mypage.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mypage.domain.Mypage;
import mypage.domain.MypageRepository;

import java.util.List;

import javax.transaction.Transactional;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final MypageRepository mypageRepository;

    @Autowired
    public UserController(UserService userService, MypageRepository mypageRepository) {
        this.userService = userService;
        this.mypageRepository = mypageRepository;
    }

    @PostMapping
    public Mypage create(@RequestBody Mypage user) {
        return userService.save(user);
    }

    @GetMapping
    public List<Mypage> readAll() {
        List<Mypage> users = userService.findAll();

        return users;
    }

    @GetMapping("/list")
    @ResponseBody
    public Iterable<Mypage> bookList() {
        MypageRepository Repository = mypage.domain.Mypage.repository();

        Iterable<Mypage> mypage = null;
        try {
            mypage = Repository.findAll();
        } catch(NullPointerException e) {
            
        }
        return mypage;
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

    @GetMapping("/{id}/checkSubscribe")
    public ResponseEntity<Object> checkSubscription(@PathVariable Long id) {
        Mypage user = userService.findById(id);

        if (user != null) {
            if (user.isUserSubscribeStatus()) {
                return ResponseEntity.ok("사용자는 현재 구독 중입니다.");
            } else {
                return ResponseEntity.ok("사용자는 현재 비구독 중입니다. 결제창으로 이동?");
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/subscribe")
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
        return ResponseEntity.notFound().build();
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

    @PutMapping("/{id}/editName")
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

