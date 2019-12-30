package com.example.beck.dto;

import com.example.beck.domain.BaseEntity;
import com.example.beck.domain.Workspace;

public class WorkspaceDto extends BaseDto {

    @Override
    public Workspace cast(BaseEntity entity) {
        Workspace workspace = (Workspace) entity;
        workspace.setName(this.name);
        workspace.setDescription(this.description);
        return workspace;
    }
}