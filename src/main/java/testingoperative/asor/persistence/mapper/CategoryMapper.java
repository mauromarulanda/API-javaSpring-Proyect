package testingoperative.asor.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import testingoperative.asor.domain.Category;
import testingoperative.asor.persistence.entity.Categoria;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    //esta anotacion indica que es un mappeo contrario a la configuracion ya establecida anteriormente.
    @InheritInverseConfiguration
    @Mapping(target = "productosPruebaLista", ignore = true)
    Categoria toCategoria(Category category);
}
