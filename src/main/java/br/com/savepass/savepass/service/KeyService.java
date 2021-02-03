package br.com.savepass.savepass.service;

import br.com.savepass.savepass.exception.NotFoundException;
import br.com.savepass.savepass.model.entity.KeyEntity;
import br.com.savepass.savepass.model.mapper.Mapper;
import br.com.savepass.savepass.model.vo.KeyVO;
import br.com.savepass.savepass.model.vo.UserVO;
import br.com.savepass.savepass.repository.KeyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KeyService {

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    public List<KeyVO> findKey() {
        var user = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return mapper.mapList(keyRepository.findByIdUser(user.getId()), KeyVO.class);
    }

    public KeyVO findKeyById(String id) {
        var user = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return mapper.map(keyRepository.findByIdAndIdUser(id, user.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Key %s not found", id))), KeyVO.class);
    }

    public KeyVO saveKey(KeyVO keyVO) {
        log.info("registering key {}", keyVO.getLocal());

        var user = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserVO userVO = userService.findUserByUserName(user.getUsername());

        KeyEntity keyEntity = mapper.map(keyVO, KeyEntity.class);
        keyEntity.setIdUser(userVO.getId());
        return mapper.map(keyRepository.save(keyEntity), KeyVO.class);
    }

    public void deleteKey(String id) {
        log.info("deleting key {}", id);
        keyRepository.deleteById(id);
    }

    public void deleteKey() {
        log.info("deleting all keys");
        keyRepository.deleteAll();
    }

}
