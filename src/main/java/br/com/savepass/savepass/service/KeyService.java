package br.com.savepass.savepass.service;

import br.com.savepass.savepass.model.entity.KeyEntity;
import br.com.savepass.savepass.model.mapper.Mapper;
import br.com.savepass.savepass.model.vo.KeyVO;
import br.com.savepass.savepass.model.vo.UserVO;
import br.com.savepass.savepass.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        return mapper.map(keyRepository.findById(id).orElseThrow(), KeyVO.class);
    }

    public KeyVO saveKey(KeyVO keyVO) {
        var user = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserVO userVO = userService.findUserByUserName(user.getUsername());

        KeyEntity keyEntity = mapper.map(keyVO, KeyEntity.class);
        keyEntity.setIdUser(userVO.getId());
        return mapper.map(keyRepository.save(keyEntity), KeyVO.class);
    }

    public void deleteKey(String id) {
        keyRepository.deleteById(id);
    }

    public void deleteKey() {
        keyRepository.deleteAll();
    }

}
