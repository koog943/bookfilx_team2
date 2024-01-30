package login.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "login", path = "login")
public interface LoginRepostitory 
    extends PagingAndSortingRepository<Login, Long>{
        Login findByUserId(String userId);
    }
