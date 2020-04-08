package com.example.beck.dto;

import com.example.beck.annotations.Dto;

@Dto
public class DataBlockDto {
    public Long id;
    public String name;
    public int contents_count;
    public int blocks_count;

    public DataBlockDto (){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContents_count(int contents_count) {
        this.contents_count = contents_count;
    }

    public void setBlocks_count(int blocks_count) {
        this.blocks_count = blocks_count;
    }
}