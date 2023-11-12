package com.vrglab.foolfactory.Core;


import com.ibm.icu.impl.Assert;
import com.vrglab.foolfactory.Core.Handling.Items.FoolFactoryItemMarker;
import org.apache.commons.lang3.AnnotationUtils;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReader;
import java.lang.module.ModuleReference;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Loader<F extends Annotation> {

    protected List<Class<?>> FindAnnotatedClasses(String package_, Class<? extends F> targetAnnotation) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(package_.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Set<Class> classes = reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, package_))
                .collect(Collectors.toSet());
        List<Class<?>> classes_annotated = new ArrayList<>();
        for (Class class_:classes) {
            Annotation a = class_.getAnnotation(targetAnnotation);
            if(a != null){
                classes_annotated.add(class_);
            }
        }
        return classes_annotated;
    }
    protected List<Class<?>> FindAnnotatedClasses(String[] packages, Class<? extends F> targetAnnotation) {
        List<Class<?>> classes = new ArrayList<>();

        for (String package_: packages) {
            for (Class<?> found_class: FindAnnotatedClasses(package_, targetAnnotation)) {
                classes.add(found_class);
            }
        }
        return classes;
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
