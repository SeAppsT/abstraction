package com.example.beck.dto;

import com.example.beck.annotations.Dto;
import com.example.beck.domain.BaseEntity;
import com.example.beck.domain.Relation;
import com.example.beck.dto.validation.Add;

import javax.validation.constraints.NotNull;

@Dto
public class RelationDto extends BaseDto {

    @NotNull(groups = Add.class)
    public Long component_to_id;

    @NotNull(groups = Add.class)
    public Long component_from_id;

    @NotNull(groups = Add.class)
    public String type;

    @Override
    public Relation cast(BaseEntity entity) {
        Relation relation = (Relation) entity;
        relation.setName(this.name);
        relation.setDescription(this.description);
        relation.setType(this.type);
        return relation;
    }
}