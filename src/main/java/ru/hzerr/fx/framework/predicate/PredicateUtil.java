package ru.hzerr.fx.framework.predicate;

import javax.annotation.CheckForNull;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.function.Predicate;

public class PredicateUtil {

    private PredicateUtil() {
    }

    public static Predicate<Class<?>> isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return new AnnotationPresentPredicate(annotationClass);
    }

    static class AnnotationPresentPredicate implements java.util.function.Predicate<Class<?>>, Serializable {

        private final Class<? extends Annotation> clazz;

        public AnnotationPresentPredicate(Class<? extends Annotation> clazz) {
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return "PredicateUtil.isAnnotationPresent(" + clazz.getName() + ")";
        }

        @Override
        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof AnnotationPresentPredicate that) {
                return clazz == that.clazz;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return clazz.hashCode();
        }

        @Override
        public boolean test(Class<?> input) {
            return input.isAnnotationPresent(clazz);
        }
    }
}
