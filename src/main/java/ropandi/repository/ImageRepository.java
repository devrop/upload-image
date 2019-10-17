package ropandi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ropandi.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    @Query("SELECT t FROM Image t WHERE t.nameImage = :nameImage")
	Image getImageByName(@Param("nameImage") String nameImage);
	
}
