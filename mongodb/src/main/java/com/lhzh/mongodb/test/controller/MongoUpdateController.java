package com.lhzh.mongodb.test.controller;

import com.lhzh.mongodb.test.dto.Animals;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/update")
public class MongoUpdateController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @param animals
     * @return
     */
    @RequestMapping(value = "updateAnimalsById", method = RequestMethod.POST)
    public Map updateAnimalsById(@RequestBody Animals animals) {
        Map<String, Object> map = new HashMap<>();
        Query query = Query.query(Criteria.where("id").is(animals.getId()));
        Update update = new Update();
        update.set("name", animals.getName());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Animals.class);
        map.put("update", updateResult);
        return map;
    }

    /**
     * @param animals
     * @return
     */
    @RequestMapping(value = "updateFirstAnimalsByName", method = RequestMethod.POST)
    public Map updateFirstAnimalsByName(@RequestBody Animals animals) {
        Map<String, Object> map = new HashMap<>();
        Query query = Query.query(Criteria.where("name").is(animals.getName()));
        Update update = new Update();
        update.set("color", animals.getColor());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Animals.class);
        map.put("update", updateResult);
        return map;
    }

    /**
     * @param animals
     * @return
     */
    @RequestMapping(value = "updateMultiAnimalsByName", method = RequestMethod.POST)
    public Map updateMultiAnimalsByName(@RequestBody Animals animals) {
        Map<String, Object> map = new HashMap<>();
        Query query = Query.query(Criteria.where("name").is(animals.getName()));
        Update update = new Update();
        update.set("color", animals.getColor());
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Animals.class);
        map.put("update", updateResult);
        return map;
    }

    /**
     * @param animals
     * @return
     */
    @RequestMapping(value = "upsertAnimalsByName", method = RequestMethod.POST)
    public Map upsertAnimalsByName(@RequestBody Animals animals) {
        Map<String, Object> map = new HashMap<>();
        Query query = Query.query(Criteria.where("name").is(animals.getName()));
        Update update = new Update();
        update.set("color", animals.getColor());
        UpdateResult updateResult = mongoTemplate.upsert(query, update, Animals.class);
        map.put("update", updateResult);
        return map;
    }

}
