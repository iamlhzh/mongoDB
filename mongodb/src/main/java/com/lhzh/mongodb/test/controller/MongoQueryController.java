package com.lhzh.mongodb.test.controller;

import com.lhzh.mongodb.test.dto.Animals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/query")
public class MongoQueryController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据ID获取Map
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getMapById", method = RequestMethod.GET)
    public Map getMapById(String id) {
        Map map = mongoTemplate.findById(id, Map.class, "animals");
        return map;
    }

    /**
     * 根据ID获取Animal
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getAnimalById", method = RequestMethod.GET)
    public Animals getAnimalById(String id) {
        Animals map = mongoTemplate.findById(id, Animals.class, "animals");
        return map;
    }

    /**
     * 根据name查询
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "getMapByName", method = RequestMethod.GET)
    public Map<String, Object> getMapByName(String name) {
        Map<String, Object> map = new HashMap<>();
        Query query = Query.query(Criteria.where("name").is(name));
        List<Map> mapList = mongoTemplate.find(query, Map.class, "animals");
        map.put("mapList", mapList);
        return map;
    }

    /**
     * 根据name查询
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "getAnimalByName", method = RequestMethod.GET)
    public Map<String, Object> getAnimalByName(String name) {
        Map<String, Object> map = new HashMap<>();
        Query query = Query.query(Criteria.where("name").is(name));
        List<Animals> animalsList = mongoTemplate.find(query, Animals.class, "animals");
        map.put("animalsList", animalsList);
        return map;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "getAnimalAll", method = RequestMethod.GET)
    public Map<String, Object> getAnimalAll() {
        Map<String, Object> map = new HashMap<>();
        List<Animals> animalsList = mongoTemplate.findAll(Animals.class);
        map.put("animalsList", animalsList);
        return map;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "getAnimalMapAll", method = RequestMethod.GET)
    public Map<String, Object> getAnimalMapAll() {
        Map<String, Object> map = new HashMap<>();
        List<Map> animalsList = mongoTemplate.findAll(Map.class, "animals");
        map.put("animalsList", animalsList);
        return map;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "getAnimalMapPage", method = RequestMethod.GET)
    public Map<String, Object> getAnimalMapPage(int page, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        Pageable born = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("percent")));
        Query query = new Query().with(born);
        List<Map> animalsList = mongoTemplate.find(query, Map.class, "animals");
        map.put("animalsListPage", animalsList);
        return map;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "getAnimalPage", method = RequestMethod.GET)
    public Map<String, Object> getAnimalPage(int page, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        Pageable born = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("percent")));
        Query query = new Query().with(born);
        List<Animals> animalsList = mongoTemplate.find(query, Animals.class, "animals");
        map.put("animalsListPage", animalsList);
        return map;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "getAnimalPageOuery", method = RequestMethod.GET)
    public Map<String, Object> getAnimalPageOuery(int page, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        Pageable born = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("percent")));
        Query query = Query.query(Criteria.where("name").is("dog"));
        query.with(born);
        List<Animals> animalsList = mongoTemplate.find(query, Animals.class, "animals");
        map.put("animalsListPage", animalsList);
        return map;
    }

    @RequestMapping(value = "getAnimalMany", method = RequestMethod.GET)
    public Map<String, Object> getAnimalMany() {
        Map<String, Object> map = new HashMap<>();
        Query query = Query.query(Criteria.where("name").in("dog", "pig", "cat"));
        query.with(Sort.by(Sort.Order.desc("percent")));
        List<Animals> animalsList = mongoTemplate.find(query, Animals.class, "animals");
        map.put("animalsListPage", animalsList);
        return map;
    }


}
