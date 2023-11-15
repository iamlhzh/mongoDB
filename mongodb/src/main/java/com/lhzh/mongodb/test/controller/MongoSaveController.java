package com.lhzh.mongodb.test.controller;

import com.lhzh.mongodb.test.dto.Animals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/save")
public class MongoSaveController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存
     *
     * @param animals
     * @return
     */
    @RequestMapping(value = "saveAnimals", method = RequestMethod.POST)
    public Map saveAnimals(@RequestBody Animals animals) {
        Map<String, Object> map = new HashMap<>();
        animals.setCreateTime(new Date());
        Animals save = mongoTemplate.save(animals);
        map.put("save", save);
        return map;
    }

    /**
     * 保存
     *
     * @param animals
     * @return
     */
    @RequestMapping(value = "insertAnimals", method = RequestMethod.POST)
    public Map insertAnimals(@RequestBody Animals animals) {
        Map<String, Object> map = new HashMap<>();
        animals.setCreateTime(new Date());
        Animals save = mongoTemplate.insert(animals);
        map.put("save", save);
        return map;
    }

    /**
     * 保存
     *
     * @param animalsList
     * @return
     */
    @RequestMapping(value = "insertAnimalsList", method = RequestMethod.POST)
    public Map insertAnimalsList(@RequestBody List<Animals> animalsList) {
        Map<String, Object> map = new HashMap<>();
        Collection<Animals> insert = mongoTemplate.insert(animalsList, Animals.class);
        map.put("insert", insert);
        return map;
    }

}
