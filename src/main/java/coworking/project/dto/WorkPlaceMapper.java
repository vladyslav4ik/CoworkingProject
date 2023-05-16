package coworking.project.dto;

import coworking.project.models.WorkPlace;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WorkPlaceMapper {
    private final ModelMapper modelMapper;

    public WorkPlaceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public WorkPlace convertToWorkPlace(WorkPlaceDTO workPlaceDTO) {
        return modelMapper.map(workPlaceDTO, WorkPlace.class);
    }

    public WorkPlaceDTO convertToWorkPlaceDTO(WorkPlace workPlace) {
        return modelMapper.map(workPlace, WorkPlaceDTO.class);
    }
}