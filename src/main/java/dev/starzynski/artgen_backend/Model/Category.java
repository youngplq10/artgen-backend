package dev.starzynski.artgen_backend.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category {
    @Id
    private ObjectId id;

    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
