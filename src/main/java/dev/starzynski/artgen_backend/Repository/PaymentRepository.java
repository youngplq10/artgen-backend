package dev.starzynski.artgen_backend.Repository;

import dev.starzynski.artgen_backend.Model.Payment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, ObjectId> {
    Optional<Payment> findBySessionId(String sessionId);
}
