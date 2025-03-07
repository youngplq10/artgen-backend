package dev.starzynski.artgen_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.starzynski.artgen_backend.Service.GenerateRandomStringService;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "arts")
public class Art {
    @Id
    private ObjectId id;

    @DBRef
    @JsonIgnoreProperties("arts")
    private User user;

    private String link, prompt, linkTo;

    private Integer cost;

    private Date createdAtDate;

    public Art() {
        this.id = new ObjectId();
        GenerateRandomStringService generateRandomStringService = new GenerateRandomStringService();
        this.linkTo = generateRandomStringService.generateRandom(15);
        this.createdAtDate = new Date();
    }

    public ObjectId getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public Integer getCost() { return cost; }
    public void setCost(Integer cost) { this.cost = cost; }

    public String getLinkTo() { return linkTo; }

    public Date getCreatedAtDate() { return createdAtDate; }
}
