package de.ux.commons.function.resource;

import de.ux.commons.function.function.Function;

import java.net.URI;
import java.net.URL;

import static java.lang.String.format;

public class UrlResourceLocator implements Function<String, URL> {

    private final ClassLoader classLoader;
    private final URI rootPath;

    public UrlResourceLocator() {
        this("");
    }

    public UrlResourceLocator(ClassLoader classLoader) {
        this(classLoader, "");
    }

    public UrlResourceLocator(String rootPath) {
        this(UrlResourceLocator.class.getClassLoader(), rootPath);
    }

    public UrlResourceLocator(ClassLoader classLoader, String rootPath) {
        this.classLoader = classLoader;
        this.rootPath = URI.create(rootPath);
    }

    @Override
    public URL apply(String resourcePath) throws ResourceException {
        String resolvedPath;
        try {
            resolvedPath = rootPath.resolve(resourcePath).toString();
        } catch (IllegalArgumentException e) {
            throw new ResourceException(
                    format("The resource path %s violates RFC 2396.", resourcePath));
        }
        URL resourceUrl = classLoader.getResource(resolvedPath);
        if(resourceUrl == null) {
            throw new ResourceException(
                    format("The resource %s could not be found or the invoker doesn't have adequate privileges to get the resource.", resolvedPath));
        }
        return resourceUrl;
    }
}