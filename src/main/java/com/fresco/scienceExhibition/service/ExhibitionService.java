package com.fresco.scienceExhibition.service;

import com.fresco.scienceExhibition.model.ExhibitionModel;
import com.fresco.scienceExhibition.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExhibitionService {

    @Autowired
    private ExhibitionRepository repository;

    //to add the exhibition data using students exhibitionModel object
    //created->201
    //badRequest()->400
    public ResponseEntity<Object> postExhibition(ExhibitionModel exhibitionModel){
       if(exhibitionModel == null || exhibitionModel.getIdCardNumber() == null){
           return ResponseEntity.badRequest().build();
       }
       repository.save(exhibitionModel);
       return ResponseEntity.status(HttpStatus.CREATED).body(exhibitionModel);
    }


    //to get all the Exhibition using pageNo, pageSize and sortBy params
    //ok()->200
    //badRequest()->400
    public Page<ExhibitionModel> getAllExhibition(Integer pageNo, Integer pageSize, String sortBy)
    {
       Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//       Page<ExhibitionModel> pagedResult = repository.findAll(paging);
       return repository.findAll(paging);
    }


    //to update the Exhibition's topic , guideMember by id as PathVariable and Exhibition Object
    //ok->200
    //badRequest->400
    public ResponseEntity<Object> updateExhibition(int id, ExhibitionModel exhibitionModel) {
        Optional<ExhibitionModel> existingExhibitions = repository.findById(id);
        if (existingExhibitions.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        ExhibitionModel updatedExhibition = existingExhibitions.get();
//        updatedExhibition.setStudentName(exhibitionModel.getStudentName());
//        updatedExhibition.setIdCardNumber(exhibitionModel.getIdCardNumber());
        updatedExhibition.setTopic(exhibitionModel.getTopic());
        updatedExhibition.setGuideMember(exhibitionModel.getGuideMember());
        System.out.println(updatedExhibition);
        repository.save(updatedExhibition);
        return ResponseEntity.ok().build();
    }

    // to delete the Exhibition data by using id as PathVariable
    //ok->200
    //badRequest->400
    public ResponseEntity<Object> deleteExhibition(int id) {
      if(!repository.existsById(id)){
          return ResponseEntity.badRequest().build();
      }
      repository.deleteById(id);
      return ResponseEntity.ok().build();
    }



}
