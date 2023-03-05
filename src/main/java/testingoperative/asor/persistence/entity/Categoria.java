package testingoperative.asor.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;
    private String descripcion;
    private Boolean estado;

    //relacion entre categoria y producto
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productosPruebaLista;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Producto> getProductosPruebaLista() {
        return productosPruebaLista;
    }

    public void setProductosPruebaLista(List<Producto> productosPruebaLista) {
        this.productosPruebaLista = productosPruebaLista;
    }
}
