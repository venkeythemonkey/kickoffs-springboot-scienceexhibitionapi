package com.fresco.scienceExhibition.repository;

import com.fresco.scienceExhibition.model.ExhibitionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionRepository extends JpaRepository<ExhibitionModel,Integer> {

    //Add the required annotations to make the ExhibitionRepository

}
