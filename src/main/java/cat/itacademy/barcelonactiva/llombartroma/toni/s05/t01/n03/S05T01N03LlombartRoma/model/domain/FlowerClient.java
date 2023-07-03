package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma.model.domain;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;


@ToString(callSuper = true)
@EqualsAndHashCode
public class FlowerClient {
    @Id
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String country;

    public FlowerClient(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public FlowerClient() {
    }
}
