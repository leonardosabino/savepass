package br.com.savepass.savepass.controller;

import br.com.savepass.savepass.model.vo.KeyVO;
import br.com.savepass.savepass.service.KeyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "v1/key", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "KeyController", tags = "KeyController")
public class KeyController {

    @Autowired
    private KeyService keyService;

    @GetMapping
    @ApiOperation(value = "Find all keys")
    public List<KeyVO> findKey() {
        return keyService.findKey();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Find key by ID")
    public KeyVO findKeyById(@PathVariable String id) {
        return keyService.findKeyById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a new key")
    public KeyVO saveKey(@Validated @RequestBody KeyVO keyEntity) {
        return keyService.saveKey(keyEntity);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete a existent key")
    public void deleteKey(@PathVariable String id) {
        keyService.deleteKey(id);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete all existent keys")
    public void deleteKey() {
        keyService.deleteKey();
    }


}
