package adrixus.com.services;


import adrixus.com.repository.CustomerApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerApiService {

    @Autowired
    private CustomerApiRepository customerApiRepository;



}
