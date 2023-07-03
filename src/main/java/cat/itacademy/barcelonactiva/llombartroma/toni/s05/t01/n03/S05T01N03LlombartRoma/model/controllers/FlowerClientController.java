package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.controllers;

import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.domain.FlowerClient;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.dto.FlowerClientDTO;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.services.FlowerClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/flower/client")
public class FlowerClientController {
    final FlowerClientService flowerClientService;

    public FlowerClientController(FlowerClientService flowerClientService) {
        this.flowerClientService = flowerClientService;
    }

    @Operation(
            summary = "Add a flower",
            tags = { "Adding flower" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower added successfully"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    @PostMapping(UrlString.ADD_URL)
    public ResponseEntity<FlowerClientDTO> add( @RequestBody FlowerClient flowerClient) {
        try {
            Optional<FlowerClientDTO> optionalFlower = flowerClientService.add(flowerClient);
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.CREATED);
            } else {
                throw new FlowerClientNotFoundException();
            }
        } catch (FlowerClientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "Update a flower",
            tags = { "Updating flowers" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower updated successfully"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    @PutMapping(UrlString.UPDATE_URL)
    public ResponseEntity<FlowerClientDTO> update( @RequestBody FlowerClient flowerClient, @PathVariable int id) {
        try {
            Optional<FlowerClientDTO> optionalFlower = flowerClientService.update(flowerClient, id);
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.OK);
            } else {
                throw new FlowerClientNotFoundException();
            }
        } catch (FlowerClientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "Delete a flower",
            tags = { "Removing flowers" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower deleted successfully"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    @DeleteMapping(UrlString.DELETE_URL)
    public ResponseEntity<FlowerClientDTO> delete(@PathVariable int id) {
        Optional<FlowerClientDTO> optionalFlower = flowerClientService.delete(id);
        try {
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.OK);
            } else {
                throw new FlowerClientNotFoundException();
            }
        } catch (FlowerClientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "Retrieve a flower",
            tags = { "Retrieving a single flower" }
    )
    @GetMapping(UrlString.GET_ONE_URL)
    public ResponseEntity<FlowerClientDTO> getOne(@PathVariable int id) {
        Optional<FlowerClientDTO> optionalFlower = flowerClientService.getOne(id);
        try {
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.OK);
            } else {
                throw new FlowerClientNotFoundException();
            }
        } catch (FlowerClientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "Get all flowers",
            tags = { "Retrieving all flowers" }
    )
    @GetMapping(UrlString.GET_ALL_URL)
    public ResponseEntity<FlowerClientDTO[]> getAll() {
        Optional<FlowerClientDTO[]> optionalFlowers = flowerClientService.getAll();
        try {
            if (optionalFlowers.isPresent()) {
                return new ResponseEntity<>(optionalFlowers.get(), HttpStatus.OK);
            } else {
                throw new FlowerClientNotFoundException();
            }
        } catch (FlowerClientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    /* This inner class contains all endpoints strings. Static access */

    private static class UrlString {
        public static final String ADD_URL = "/add";
        public static final String UPDATE_URL = "/update/{id}";
        public static final String DELETE_URL = "/delete/{id}";
        public static final String GET_ONE_URL = "/getone/{id}";
        public static final String GET_ALL_URL = "/getall";
    }
}
