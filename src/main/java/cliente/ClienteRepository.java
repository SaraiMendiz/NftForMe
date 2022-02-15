package cliente;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

			Page<Cliente> findBynombre(String user);
			Page<Cliente> findById(int id) ;
			
}
