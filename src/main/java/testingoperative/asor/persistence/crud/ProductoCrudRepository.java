package testingoperative.asor.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import testingoperative.asor.persistence.entity.Producto;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
