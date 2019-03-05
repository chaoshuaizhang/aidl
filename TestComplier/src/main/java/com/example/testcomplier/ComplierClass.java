package com.example.testcomplier;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("com.example.aptlib.anno.FunPath")
public class ComplierClass extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //构建方法
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello My APT")
                .build();
        //构建类
        TypeSpec mainClass = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();
        JavaFile javaFile = JavaFile.builder("com.example.testcomplier", mainClass).build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
