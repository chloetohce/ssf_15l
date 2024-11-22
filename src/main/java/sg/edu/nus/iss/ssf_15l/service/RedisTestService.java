package sg.edu.nus.iss.ssf_15l.service;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ssf_15l.repository.ListRepository;
import sg.edu.nus.iss.ssf_15l.repository.ValueRepository;

@Service
public class RedisTestService {
    private final ListRepository listRepository;
    private final ValueRepository valueRepository;

    public RedisTestService(ListRepository listRepository, ValueRepository valueRepository) {
        this.listRepository = listRepository;
        this.valueRepository = valueRepository;
    }

    // Write service functions that perform functions on the two repositories.
}
