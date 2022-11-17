package ru.hzerr.fx.framework.util;

import com.google.common.base.Objects;
import org.apache.commons.lang3.ClassLoaderUtils;
import org.apache.commons.lang3.ClassPathUtils;
import org.apache.commons.lang3.ClassUtils;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;

public class ClassTools {

    /** The package separator character: {@code '.'}. */
    private static final char PACKAGE_SEPARATOR = '.';

    /** The path separator character: {@code '/'}. */
    private static final char PATH_SEPARATOR = '/';

    private ClassTools() {
    }

    public static boolean equalAny(Class<?> clazz, Class<?>... classes) {
        for (Class<?> clazz0 : classes) {
            if (Objects.equal(clazz0, clazz)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Given an input class object, return a string which consists of the
     * class's package name as a pathname, i.e., all dots ('.') are replaced by
     * slashes ('/'). Neither a leading nor trailing slash is added. The result
     * could be concatenated with a slash and the name of a resource and fed
     * directly to {@code ClassLoader.getResource()}. For it to be fed to
     * {@code Class.getResource} instead, a leading slash would also have
     * to be prepended to the returned value.
     * @param clazz the input class. A {@code null} value or the default
     * (empty) package will result in an empty string ("") being returned.
     * @return a path which represents the package name
     * @see ClassLoader#getResource
     * @see Class#getResource
     */
    public static String classPackageAsResourcePath(@Nullable Class<?> clazz) {
        if (clazz == null) {
            return "";
        }
        String className = clazz.getName();
        int packageEndIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
        if (packageEndIndex == -1) {
            return "";
        }
        String packageName = className.substring(0, packageEndIndex);
        return packageName.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;

        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ignored) {
            // Cannot access thread context ClassLoader - falling back...
        }

        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassTools.class.getClassLoader();
            if (cl == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ignored) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }
}
