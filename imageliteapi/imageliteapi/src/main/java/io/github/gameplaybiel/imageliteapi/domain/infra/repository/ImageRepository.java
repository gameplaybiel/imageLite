package io.github.gameplaybiel.imageliteapi.domain.infra.repository;

import io.github.gameplaybiel.imageliteapi.domain.entity.Image;
import io.github.gameplaybiel.imageliteapi.domain.enums.ImageExtension;
import io.github.gameplaybiel.imageliteapi.domain.infra.repository.specs.GenericSpecs;
import io.github.gameplaybiel.imageliteapi.domain.infra.repository.specs.ImageSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;

import static io.github.gameplaybiel.imageliteapi.domain.infra.repository.specs.GenericSpecs.conjunction;
import static io.github.gameplaybiel.imageliteapi.domain.infra.repository.specs.ImageSpecs.*;

public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {

    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query){

        Specification<Image> spec = Specification.where(conjunction());

        if(extension != null){
            spec = spec.and(extensionEqual(extension));
        }

        if(StringUtils.hasText(query)){

            spec = spec.and(Specification.anyOf(nameLike(query), tagsLike(query)));
        }

        return findAll(spec);
    }
}
