package com.bwagih.automaaticirrigateplot.spec;

import com.bwagih.automaaticirrigateplot.entity.PlotEntity;
import com.bwagih.automaaticirrigateplot.enums.TypeOfOperator;
import com.bwagih.automaaticirrigateplot.model.FilterModel;
import com.bwagih.automaaticirrigateplot.utils.Defines;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PlotSpec implements Specification<PlotEntity> {
    FilterModel filterModel;

    public PlotSpec(FilterModel filterModel) {
        this.filterModel = filterModel;
    }

    @Override
    public Specification<PlotEntity> and(Specification<PlotEntity> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<PlotEntity> or(Specification<PlotEntity> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<PlotEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        //area field only
        if (filterModel.getTypeOfOperator() != null){
            if (filterModel.getTypeOfOperator().equalsIgnoreCase(String.valueOf(TypeOfOperator.GREATER_THAN))){
                predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get(Defines.AREA_FIELD_PLOT_SPEC),
                        (filterModel.getArea())));
            } else if (filterModel.getTypeOfOperator().equalsIgnoreCase(String.valueOf(TypeOfOperator.LESS_THAN))){
                predicate.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.get(Defines.AREA_FIELD_PLOT_SPEC),
                        (filterModel.getArea())));
            } else if (filterModel.getTypeOfOperator().equalsIgnoreCase(String.valueOf(TypeOfOperator.EQUALS))){
                predicate.getExpressions().add(criteriaBuilder.equal(root.get(Defines.AREA_FIELD_PLOT_SPEC),
                        (filterModel.getArea())));
            } else if (filterModel.getTypeOfOperator().equalsIgnoreCase(String.valueOf(TypeOfOperator.NOT_EQUALS))){
                predicate.getExpressions().add(criteriaBuilder.notEqual(root.get(Defines.AREA_FIELD_PLOT_SPEC),
                        (filterModel.getArea())));
            } else {
                predicate.getExpressions().add(criteriaBuilder.equal(root.get(Defines.AREA_FIELD_PLOT_SPEC),
                        (Defines.ZERO)));
            }
        }
        if (filterModel.getSearchBy() != null) {
            predicate.getExpressions().add(criteriaBuilder.equal(root.get(filterModel.getSearchBy()), false));
        }

        return predicate;
    }
}

