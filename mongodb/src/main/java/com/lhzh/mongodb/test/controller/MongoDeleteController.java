package com.lhzh.mongodb.test.controller;

import com.lhzh.mongodb.test.dto.Animals;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/delete")
public class MongoDeleteController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @param animals
     * @return
     */
    @RequestMapping(value = "deleteAnimalsById", method = RequestMethod.POST)
    public Map deleteAnimalsById(@RequestBody Animals animals) {
        Map<String, Object> map = new HashMap<>();
        Query query = Query.query(Criteria.where("id").is(animals.getId()));
        DeleteResult remove = mongoTemplate.remove(query, "animals");
        map.put("delete", remove);
        return map;
    }

    /**
     * @param animals
     * @return
     */
    @RequestMapping(value = "deleteAnimalsById2", method = RequestMethod.POST)
    public Map deleteAnimalsById2(@RequestBody Animals animals) {
        Map<String, Object> map = new HashMap<>();
        DeleteResult remove = mongoTemplate.remove(animals);
        map.put("delete", remove);
        return map;
    }

    /**
     * @return
     */
    @RequestMapping(value = "deleteAnimals", method = RequestMethod.POST)
    public Map deleteAnimals() {
        Map<String, Object> map = new HashMap<>();
        Query query = Query.query(Criteria.where("name").is("cat"));
        DeleteResult remove = mongoTemplate.remove(query, Animals.class);
        map.put("delete", remove);
        return map;
    }


}
