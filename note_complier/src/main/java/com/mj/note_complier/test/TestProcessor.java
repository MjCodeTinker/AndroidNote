package com.mj.note_complier.test;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

public class TestProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("TestProcessor_process() : set = " + set.toString() + "roundEnvironment = " + roundEnvironment);

        // 生成java 文件
        TypeSpec typeSpec = TypeSpec.classBuilder("TestTypeSpec")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .build();
        JavaFile javaFile = JavaFile.builder("com.mj.test",typeSpec).build();
        try {
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
