package com.fresco.scienceExhibition.controller;

import com.fresco.scienceExhibition.model.ExhibitionModel;
import com.fresco.scienceExhibition.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exhibition")
public class ExhibitionController {

    /*
  This controller would be responsible for the ExhibitionController endpoints
  Add the required annotations and create the endpoints
   */

   @Autowired
    private ExhibitionService exhibitionService;

    //to add the exhibition data using students exhibitionModel object
    @PostMapping("/add")
    public ResponseEntity<Object> postExhibition(@RequestBody ExhibitionModel exhibitionModel){
        return exhibitionService.postExhibition(exhibitionModel);
    }

    //to get all the Exhibition using pageNo, pageSize and sortBy params
    @GetMapping
    public ResponseEntity<List<ExhibitionModel>> getAllExhibition(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        Page<ExhibitionModel> page = exhibitionService.getAllExhibition(pageNo, pageSize, sortBy);
        List<ExhibitionModel> exhibitions = page.getContent();

        if(pageNo == null || pageSize == null || sortBy.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        if(pageNo >= page.getTotalPages()){
            return ResponseEntity.badRequest().build();
        }

        //List<ExhibitionModel> exhibitions = exhibitionService.getAllExhibition(pageNo, pageSize, sortBy);
        return ResponseEntity.ok(exhibitions);
    }


    //to update the Exhibition's topic , guideMember by id as PathVariable and Exhibition Object
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateExhibition(@PathVariable int id, @RequestBody ExhibitionModel exhibitionModel){
        return exhibitionService.updateExhibition(id, exhibitionModel);
    }

    // to delete the Exhibition data by using id as PathVariable
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExhibition(@PathVariable int id){
        return exhibitionService.deleteExhibition(id);
    }



}
