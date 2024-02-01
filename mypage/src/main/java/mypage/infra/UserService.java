package mypage.infra;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypage.domain.Mypage;
import mypage.domain.MypageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final MypageRepository mypageRepository;

    @Autowired
    public UserService(MypageRepository userRepository) {
        this.mypageRepository = userRepository;
    }

    public Mypage save(Mypage user) {
        return mypageRepository.save(user);
    }

    public List<Mypage> findAll() {
        return (List<Mypage>) mypageRepository.findAll();
    }

    public Mypage findById(Long id) {
        Optional<Mypage> optionalMypage = mypageRepository.findById(id);
        return optionalMypage.orElse(null);
}


    public void deleteById(Long id) {
        mypageRepository.deleteById(id);
    }

}
