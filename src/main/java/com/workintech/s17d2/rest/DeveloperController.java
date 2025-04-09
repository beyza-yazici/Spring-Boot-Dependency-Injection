package com.workintech.s17d2.rest;

import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.JuniorDeveloper;
import com.workintech.s17d2.model.MidDeveloper;
import com.workintech.s17d2.model.SeniorDeveloper;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    public Map<Integer, Developer> developers;
    private Taxable taxable;

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
    }

    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @GetMapping
    public List<Developer> listAllDevelopers (){
        return new ArrayList<>(developers.values());
    }

    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable Integer id){
        return developers.get(id);
    }

    @PostMapping
    public ResponseEntity<Developer> createDeveloper (@RequestBody Developer developer) {
        Developer newDeveloper = null;
        double calculatedSalary;

        switch (developer.getExperience()) {
            case JUNIOR:
                calculatedSalary = developer.getSalary() * (100 - taxable.getSimpleTaxRate()) / 100;
                newDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(), calculatedSalary);
                break;

            case MID:
                calculatedSalary = developer.getSalary() * (100 - taxable.getSimpleTaxRate()) / 100;
                newDeveloper = new MidDeveloper(developer.getId(), developer.getName(), calculatedSalary);
                break;

            case SENIOR:
                calculatedSalary = developer.getSalary() * (100 - taxable.getSimpleTaxRate()) / 100;
                newDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(), calculatedSalary);
                break;
            default:
        }
        developers.put(developer.getId().intValue(), newDeveloper);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDeveloper);
    }

    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable Integer id, @RequestBody Developer developer){
        if(!developers.containsKey(id)){
            throw new RuntimeException("Developer not found with id: " + id);
        }
        developers.put(id, developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public void deleteDeveloper(@PathVariable Integer id){
        if (!developers.containsKey(id)) {
            throw new RuntimeException("Developer not found with id: " + id);
        }
        developers.remove(id);
    }

}
