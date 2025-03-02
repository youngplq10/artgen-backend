package dev.starzynski.artgen_backend.Repository;

import dev.starzynski.artgen_backend.Model.Art;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtRepository extends MongoRepository<Art, ObjectId> {
}
