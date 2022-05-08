package com.bwagih.automaaticirrigateplot.spec;

import com.bwagih.automaaticirrigateplot.entity.TypeEntity;
import com.bwagih.automaaticirrigateplot.model.FilterModel;
import com.bwagih.automaaticirrigateplot.utils.Defines;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TypeSpec implements Specification<TypeEntity> {
    FilterModel filterModel;

    public TypeSpec(FilterModel filterModel) {
        this.filterModel = filterModel;
    }

    @Override
    public Specification<TypeEntity> and(Specification<TypeEntity> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<TypeEntity> or(Specification<TypeEntity> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<TypeEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (filterModel.getSearchBy() != null){
            predicate.getExpressions().add(criteriaBuilder.equal(root.get(filterModel.getSearchBy()), false));
        }
        if (filterModel.getContainChars() != null){
            predicate.getExpressions()
                    .add(criteriaBuilder
                            .like(root.get(Defines.TYPE_FIELD_TYPE_SPEC), Defines.LIKE_OP + filterModel.getContainChars() + Defines.LIKE_OP));
        }
        return predicate;
    }
}

