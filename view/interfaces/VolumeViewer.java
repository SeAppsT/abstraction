package com.example.beck.view.interfaces;

import com.example.beck.view.AnnotationComponentViewer;
import com.example.beck.view.SimpleComponentViewer;

public interface VolumeViewer {
    public void addComponent(SimpleComponentViewer viewer);
    public void addAnnotation(AnnotationComponentViewer viewer);
}