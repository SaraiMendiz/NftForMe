package cliente;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


public interface carritoRepository extends JpaRepository <carritoClientes,Long>{

	List<carritoClientes> findBynombre(String nombre);
}
