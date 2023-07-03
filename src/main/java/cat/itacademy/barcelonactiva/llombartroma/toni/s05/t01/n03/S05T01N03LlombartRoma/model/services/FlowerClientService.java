package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.services;

import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.domain.FlowerClient;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.dto.FlowerClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class FlowerClientService {
    final RestTemplate restTemplate;

    public FlowerClientService() {
        this.restTemplate = new RestTemplate();
    }

    /* Implements how client adds a FlowerClient */
    public Optional<FlowerClientDTO> add(FlowerClient flowerClient) {
       ResponseEntity<FlowerClient> responseEntity = restTemplate.postForEntity(UrlString.addUrl, flowerClient, FlowerClient.class);
       return Optional.of(fromFlowerClientToFlowerDTO(responseEntity.getBody()));
    }
    /* Implements how client updates a FlowerClient */
    public Optional<FlowerClientDTO> update(FlowerClient flowerClient, int id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<FlowerClient> httpEntity = new HttpEntity<>(flowerClient,httpHeaders);
        String url = UrlString.updateUrl + id;
        return Optional.of(fromFlowerClientToFlowerDTO(restTemplate.exchange(url,HttpMethod.PUT,httpEntity,FlowerClient.class).getBody()));
    }
    /* Implements how client deletes a FlowerClient */
    public Optional<FlowerClientDTO> delete(int id) {
        Optional<FlowerClientDTO> optionalFlowerClientDTO = Optional.ofNullable(restTemplate.getForObject(UrlString.getOneUrl + id, FlowerClientDTO.class));
        String url = UrlString.deleteUrl + id;
        restTemplate.delete(url);
        return optionalFlowerClientDTO;
    }
    /* Implements how client retrieves a FlowerClient */
    public Optional<FlowerClientDTO> getOne(int id) {
        return Optional.ofNullable(restTemplate.getForObject(UrlString.getOneUrl + id, FlowerClientDTO.class));
    }
    /* Implements how client retrieves all FlowerClient */
    public Optional<FlowerClientDTO[]> getAll() {
        return Optional.ofNullable(restTemplate.getForObject(UrlString.getAllUrl, FlowerClientDTO[].class));
    }
    /* Converts from FlowerClient to FlowerClientDTO */
    private FlowerClientDTO fromFlowerClientToFlowerDTO(FlowerClient flowerClient) {
        ModelMapper modelMapper = new ModelMapper();
        FlowerClientDTO flowerDTO = modelMapper.map(flowerClient, FlowerClientDTO.class);
        flowerDTO.setFlowerClientType();
        return flowerDTO;
    }
    /* Converts from FlowerClientDTO to FlowerClient */
    private FlowerClient fromFlowerClientDTOToFlower(FlowerClientDTO flowerClientDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(flowerClientDTO, FlowerClient.class);
    }
    /* This inner class contains server access urls. Static access */
    public static class UrlString {
        public static final String addUrl = "http://localhost:9001/flower/add";
        public static final String updateUrl = "http://localhost:9001/flower/update/";
        public static final String deleteUrl = "http://localhost:9001/flower/delete/";
        public static final String getOneUrl = "http://localhost:9001/flower/getone/";
        public static final String getAllUrl = "http://localhost:9001/flower/getall";
    }
}
