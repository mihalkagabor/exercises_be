    package org.mihalka.exercises_be.controller;

    import jakarta.validation.Valid;
    import lombok.AllArgsConstructor;
    import org.mihalka.exercises_be.model.dto.AccessoryCreationDto;
    import org.mihalka.exercises_be.service.AccessoryService;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/accessory")
    @AllArgsConstructor
    public class AccessoryController {
        private final AccessoryService accessoryService;

        @PostMapping("/create_Accessory")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<Void> createAccessory(@RequestBody @Valid AccessoryCreationDto accessoryCreationDto){
            accessoryService.createAccessory(accessoryCreationDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        @GetMapping("/accessory_lister")
        public ResponseEntity<List<AccessoryCreationDto>> listAccessory(){
            return ResponseEntity.ok(accessoryService.accessoryLister());
        }


    }
