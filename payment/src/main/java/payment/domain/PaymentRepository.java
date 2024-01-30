package payment.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "payment",
    path = "payment"
)
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>{
    
}
