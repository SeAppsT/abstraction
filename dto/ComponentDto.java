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
    public int x;

    @NotNull(groups = Add.class)
    public int y;

    public String type;
    public String color;
    public String title_to;
    public String title_from;

    @Override
    public Component cast(BaseEntity entity) {
        Component component = (Component) entity;
        component.setName(this.name);
        component.setDescription(this.description);
        if (this.type == null)
            component.setType("block");
        else
            component.setType(this.type);
        component.setColor(this.color);
        component.setTitle_to(this.title_to);

        component.setTitle_from(this.title_from);
        return component;
    }

    public void setWorkspace_id(Long workspace_id) {
        this.workspace_id = workspace_id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }
}