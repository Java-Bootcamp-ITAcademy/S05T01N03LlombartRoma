package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.controllers;

import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.domain.FlowerClient;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.dto.FlowerClientDTO;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.services.FlowerClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/flower/client")
public class FlowerClientController {
    final
    FlowerClientService flowerClientService;
    private final UrlString urlString;

    public FlowerClientController(FlowerClientService flowerClientService, UrlString urlString) {
        this.flowerClientService = flowerClientService;
        this.urlString = urlString;
    }

    @Operation(
            summary = "Add a flower",
            tags = { "Adding flower" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower added successfully"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    @PostMapping(UrlString.addUrl)
    public ResponseEntity<FlowerClientDTO> add( @RequestBody FlowerClient flowerClient) {
        try {
            Optional<FlowerClientDTO> optionalFlower = flowerClientService.add(flowerClient);
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.CREATED);
            } else {
                throw new FlowerClientNotFoundException();
            }
        } catch (Exception e) {
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
    @PutMapping(UrlString.updateUrl)
    public ResponseEntity<FlowerClientDTO> update( @RequestBody FlowerClient flowerClient, @PathVariable int id) {
        try {
            Optional<FlowerClientDTO> optionalFlower = flowerClientService.update(flowerClient, id);
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.OK);
            } else {
                throw new FlowerClientNotFoundException();
            }
        } catch (Exception e) {
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
    @DeleteMapping(UrlString.deleteUrl)
    public ResponseEntity<FlowerClientDTO> delete(@PathVariable int id) {
        Optional<FlowerClientDTO> optionalFlower = flowerClientService.delete(id);
        try {
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.OK);
            } else {
                throw new FlowerClientNotFoundException();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "Retrieve a flower",
            tags = { "Retrieving a single flower" }
    )
    @GetMapping(UrlString.getOneUrl)
    public ResponseEntity<FlowerClientDTO> getOne(@PathVariable int id) {
        Optional<FlowerClientDTO> optionalFlower = flowerClientService.getOne(id);
        try {
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.OK);
            } else {
                throw new FlowerClientNotFoundException();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "Get all flowers",
            tags = { "Retrieving all flowers" }
    )
    @GetMapping(UrlString.getAllUrl)
    public ResponseEntity<FlowerClientDTO[]> getAll() {
        Optional<FlowerClientDTO[]> optionalFlowers = flowerClientService.getAll();
        try {
            if (optionalFlowers.isPresent()) {
                return new ResponseEntity<>(optionalFlowers.get(), HttpStatus.OK);
            } else {
                throw new FlowerClientNotFoundException();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    /* This inner class contains all endpoints strings. Static access */
    @Component
    public static class UrlString {
        public static final String addUrl = "/add";
        public static final String updateUrl = "/update/{id}";
        public static final String deleteUrl = "/delete/{id}";
        public static final String getOneUrl = "/getOne/{id}";
        public static final String getAllUrl = "/getAll";
    }
}
