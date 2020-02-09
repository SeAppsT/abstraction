package com.example.beck.dto;

import com.example.beck.annotations.Dto;
import com.example.beck.domain.BaseEntity;
import com.example.beck.domain.Component;
import com.example.beck.dto.validation.Add;

import javax.validation.constraints.NotNull;

@Dto
public class ComponentDto extends BaseDto {

    @NotNull(groups = Add.class)
    public Long workspace_id;

    @NotNull(groups = Add.class)
    public int num_cell;

    public String type = "block";
    public String color;
    public String title_to;
    public String title_from;

    @Override
    public Component cast(BaseEntity entity) {
        Component component = (Component) entity;
        component.setName(this.name);
        component.setDescription(this.description);
        component.setType(this.type);
        component.setColor(this.color);
        component.setTitle_to(this.title_to);
        component.setTitle_from(this.title_from);
        return component;
    }

    public void setWorkspace_id(Long workspace_id) {
        this.workspace_id = workspace_id;
    }

    public void setNum_cell(int num_cell) {
        this.num_cell = num_cell;
    }
}