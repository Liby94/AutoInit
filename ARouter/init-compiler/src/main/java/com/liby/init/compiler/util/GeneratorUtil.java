package com.liby.init.compiler.util;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;

/**
 * @author: LiBing
 * @date: 2021_05_30
 * @desc:
 */
public class GeneratorUtil {

    public static void generatorInit() {
        try {
            MethodSpec main = MethodSpec.methodBuilder("main")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(void.class)
                    .addParameter(String[].class, "args")
                    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                    .build();

            FieldSpec fieldsped = FieldSpec.builder(ClassName.get("java.lang", "Object"),"context",Modifier.PRIVATE).build();
            TypeSpec helloWorld = TypeSpec.classBuilder("Init")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addField(fieldsped)
                    .addField(TypeName.OBJECT, "context",Modifier.PRIVATE)
                    .addMethod(main)
                    .build();

            JavaFile javaFile = JavaFile.builder("com.liby.autoinit", helloWorld)
                    .build();

            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
