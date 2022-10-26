package ru.hzerr.fx.framework.predicate;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import javax.annotation.CheckForNull;
import java.io.Serializable;
import java.lang.annotation.Annotation;

public class PredicateUtil {

    private PredicateUtil() {
    }

    public static Predicate<Class<?>> isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return new AnnotationPresentPredicate(annotationClass);
    }

    static class AnnotationPresentPredicate implements Predicate<Class<?>>, Serializable {

        private final Class<? extends Annotation> clazz;

        public AnnotationPresentPredicate(Class<? extends Annotation> clazz) {
            this.clazz = clazz;
        }

        @Override
        public boolean apply(Class<?> input) {
            return input.isAnnotationPresent(clazz);
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
    }
}
