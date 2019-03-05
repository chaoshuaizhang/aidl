package com.example.testcomplier;

import com.example.aptlib.di.InjectActivity;
import com.example.aptlib.di.InjectView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("com.example.aptlib.di.InjectActivity")
public class InjectProcessor extends AbstractProcessor {

    private Elements elementUtils;

    private Types typeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        //获取用来在元素上进行操作的某些实用工具方法的实现
        elementUtils = processingEnvironment.getElementUtils();
        //获取用来在类型上进行操作的某些实用工具方法的实现
        typeUtils = processingEnvironment.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        StringBuilder sb = new StringBuilder();
        sb.append("开始自动生成，");
        //获取带有InjectActivity注解的元素
        Set<? extends Element> annotatedWithInjectActivity = roundEnvironment.getElementsAnnotatedWith(InjectActivity.class);
        sb.append("annotatedWithInjectActivity数量：" + annotatedWithInjectActivity.size() + "，");
        if (annotatedWithInjectActivity != null) {
            //返回已给出其规范名称的类型元素
            TypeElement typeElement = elementUtils.getTypeElement("android.app.Activity");
            //遍历带有InjectActivity注解的元素
            for (Element element : annotatedWithInjectActivity) {
                //返回此元素定义的类型，TypeMirror表示Java编程语言中的
                //类型，toString方法会输出其原始类型的详细信息
                TypeMirror typeMirror = element.asType();
                sb.append("simpleName：" + typeMirror.toString() + "，");
                //返回此元素指定类型的注解，如果没有的话返回null
                InjectActivity injectActivity = element.getAnnotation(InjectActivity.class);
                //判断是否是其子类型
                if (typeUtils.isSubtype(typeMirror, typeElement.asType())) {
                    //TypeElement：表示接口或类元素，提供对类型及其成员的信息的访问
                    TypeElement classElement = (TypeElement) element;
                    //创建参数
                    ParameterSpec param = ParameterSpec
                            .builder(ClassName.get(typeMirror), "activity")//参数名
                            .build();
                    //创建方法
                    MethodSpec.Builder method = MethodSpec.methodBuilder
                            ("findById")
//                                .addAnnotation(Override.class)
                            .addModifiers(PUBLIC, STATIC)
                            .returns(TypeName.VOID)
                            .addParameter(param);
                    //创建函数体
                    //返回类型元素的所有成员，无论是继承还是直接声明
                    List<? extends Element> allMembers = elementUtils.getAllMembers(classElement);
                    for (Element member : allMembers) {
                        //返回此元素指定类型的注解，如果没有的话返回null
                        InjectView injectView = member.getAnnotation(InjectView.class);
                        if (injectView == null) {
                            continue;
                        } else {
                            //构建函数体(自定义自动生成的函数体)
                            method.addStatement(String.format("activity.%s = (%s) activity.findViewById(%s)",
                                    member.getSimpleName(),//注解节点变量的名称
                                    ClassName.get(member.asType()).toString(),//注解节点变量的类型
                                    injectView.value()));//注解的值
                        }
                    }
                    FieldSpec fieldSpec = FieldSpec.builder(String.class, "lonInfo", PUBLIC, FINAL).initializer(String.format("\"%s\"", new String(sb.toString().getBytes(), Charset.forName("UTF-8")))).build();
                    //创建类
                    TypeSpec typeSpec = TypeSpec.classBuilder("MyFindViewBy" + element.getSimpleName())
                            .addModifiers(PUBLIC, FINAL)//添加作用域
                            .addMethod(method.build())//添加方法
                            .addField(fieldSpec)//添加属性
                            .build();
                    JavaFile javaFile = JavaFile.builder("com.example.testcomplier", typeSpec).build();
                    try {
                        javaFile.writeTo(processingEnv.getFiler());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }
}
