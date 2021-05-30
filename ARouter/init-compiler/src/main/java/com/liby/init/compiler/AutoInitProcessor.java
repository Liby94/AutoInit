package com.liby.init.compiler;

import com.google.auto.service.AutoService;
import com.liby.init.annotation.AutoInit;
import com.liby.init.compiler.util.Logger;
import com.liby.init.compiler.util.TypeUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.liby.init.compiler.util.Consts.AUTO_INIT_SERVICE;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.liby.init.annotation.AutoInit"})
public class AutoInitProcessor extends AbstractProcessor {

    Filer mFiler;
    Logger logger;
    Types types;
    Elements elementUtils;
    TypeUtils typeUtils;
    // Module name, maybe its 'app' or others
    String moduleName = null;
    // If need generate router doc
    boolean generateDoc;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        types = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        typeUtils = new TypeUtils(types, elementUtils);
        logger = new Logger(processingEnv.getMessager());
        logger.info(">>> AutoInitProcessor init. <<<");
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (CollectionUtils.isNotEmpty(set)) {
            Set<? extends Element> autoInitElements = roundEnvironment.getElementsAnnotatedWith(AutoInit.class);
            try {
                logger.info(">>> Found initServer, start... <<<");
                parseInit(autoInitElements);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            return true;
        }
        return false;
    }



    private void parseInit(Set<? extends Element> autoInitElements) throws IOException {
        if (CollectionUtils.isNotEmpty(autoInitElements)) {
            logger.info(">>> Found init, size is " + autoInitElements.size() + " <<<");
            TypeMirror autoInitService = elementUtils.getTypeElement(AUTO_INIT_SERVICE).asType();

            for (Element element : autoInitElements) {
                TypeMirror tm = element.asType();
                AutoInit route = element.getAnnotation(AutoInit.class);
                if(types.isSubtype(tm,autoInitService)) {
                    if(element instanceof TypeElement) {
                        ClassName className = ClassName.get((TypeElement) element);
                        String simpleName = className.simpleName();
                        String packageName = className.packageName();
                        logger.info("simpleName: " + simpleName + " packageName: " + packageName);
                    }
                } else {
                    logger.info("unknow class: ");
                    //throw new RuntimeException("The @AutoInitService is marked on unsupported class, look at [" + tm.toString() + "].");
                }
            }
        }
    }

    public void generatorInitClass() {

    }

    public MethodSpec generator() {
        ClassName autoServiceClass = ClassName.get("com.liby.initapi", "AutoInitService");
        ClassName list = ClassName.get("java.util", "List");
        ClassName arrayList = ClassName.get("java.util", "ArrayList");
        TypeName autoServiceList = ParameterizedTypeName.get(list, autoServiceClass);
        MethodSpec getAutoServiceList = MethodSpec.methodBuilder("getAutoServiceList")
                .returns(autoServiceList)
                .addStatement("$T result = new $T<>()",autoServiceList, arrayList)

                .addStatement("return result")
                .build();
        return getAutoServiceList;
    }

}