package br.com.savepass.savepass.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Component
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public <T> T map(Object object, Class<T> type) {
        return modelMapper.map(object, type);
    }

    public void map(Object source, Object destination) {
        modelMapper.map(source, destination);
    }

    public <T> List<T> mapList(List<?> list, Class<T> type) {
        List<T> result = new ArrayList<>();
        for (Object object : list) {
            result.add(modelMapper.map(object, type));
        }
        return result;
    }

    public <T, D> List<T> mapList(List<D> list, Class<T> type, BiConsumer<D, T> e) {
        List<T> result = new ArrayList<>();
        for (D object : list) {
            T map = modelMapper.map(object, type);
            result.add(map);

            if (e != null) {
                e.accept(object, map);
            }
        }
        return result;
    }

}