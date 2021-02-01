package br.com.savepass.savepass.service;

import br.com.savepass.savepass.model.entity.UserEntity;
import br.com.savepass.savepass.model.mapper.Mapper;
import br.com.savepass.savepass.model.vo.UserVO;
import br.com.savepass.savepass.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Mapper mapper;

    public List<UserVO> findUser() {
        return mapper.mapList(userRepository.findAll(), UserVO.class);
    }

    public UserVO findUserById(String id) {
        return mapper.map(userRepository.findById(id).orElseThrow(), UserVO.class);
    }

    public UserVO findUserByUserName(String username) {
        return mapper.map(userRepository.findByUsername(username).orElseThrow(), UserVO.class);
    }

    public UserVO saveUser(UserVO userVO) {
        log.info("registering user {}", userVO.getUsername());

        if (userRepository.existsByUsername(userVO.getUsername())) {
            log.warn("username {} already exists.", userVO.getUsername());
            // throw new UsernameAlreadyExistsException(String.format("username %s already exists", user.getUsername()));
            throw new IllegalArgumentException();
        }

        var userEntity = mapper.map(userVO, UserEntity.class);
        userEntity.setActive(true);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        return mapper.map(userRepository.save(userEntity), UserVO.class);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public void deleteUser() {
        userRepository.deleteAll();
    }

}
