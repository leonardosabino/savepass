package br.com.savepass.savepass.service;

import br.com.savepass.savepass.model.entity.KeyEntity;
import br.com.savepass.savepass.model.mapper.Mapper;
import br.com.savepass.savepass.model.vo.KeyVO;
import br.com.savepass.savepass.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyService {

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private Mapper mapper;

    public List<KeyVO> findKey() {
        return mapper.mapList(keyRepository.findAll(), KeyVO.class);
    }

    public KeyVO findKeyById(String id) {
        return mapper.map(keyRepository.findById(id).orElseThrow(), KeyVO.class);
    }

    public KeyVO saveKey(KeyVO keyVO) {
        KeyEntity keyEntity = mapper.map(keyVO, KeyEntity.class);
        return mapper.map(keyRepository.save(keyEntity), KeyVO.class);
    }

    public void deleteKey(String id) {
        keyRepository.deleteById(id);
    }

    public void deleteKey() {
        keyRepository.deleteAll();
    }

}
