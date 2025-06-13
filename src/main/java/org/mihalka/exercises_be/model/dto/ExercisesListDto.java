package org.mihalka.exercises_be.model.dto;

import lombok.Data;
import org.mihalka.exercises_be.model.entity.ExercisesEntity;

@Data
public class ExercisesListDto {
    private Long exercises_id;
    private String exercise_name;
    private Boolean is_free_weight;
    private String bodyPartName;
    private Integer difficultyLevel;
    private String difficultyDescription;
    private String accessoryName;

    public ExercisesListDto(ExercisesEntity exercisesEntity){
        this.exercises_id=exercisesEntity.getExercisesId();
        this.exercise_name=exercisesEntity.getExercisesName();
        this.is_free_weight=exercisesEntity.getIsFreeWeight();
        this.bodyPartName=exercisesEntity.getBodyPart().getBodyPartName();
        this.difficultyLevel=exercisesEntity.getDifficultyLevel().getDifficultyLevelLevel();
        this.difficultyDescription =exercisesEntity.getDifficultyLevel().getDifficultyLevelDescription();
        this.accessoryName=exercisesEntity.getAccessory().getAccessoryName();


    }

}
