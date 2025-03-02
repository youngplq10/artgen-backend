package dev.starzynski.artgen_backend.Service;

import dev.starzynski.artgen_backend.Repository.ArtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtService {
    @Autowired
    private ArtRepository artRepository;
}
