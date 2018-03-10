package de.ux.commons.function.resource;

import de.ux.commons.function.function.Function;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

import static java.lang.String.format;

public class UriResourceReader implements Function<URI, String> {

    private final Charset charset;

    public UriResourceReader(Charset charset) {
        this.charset = charset;
    }

    @Override
    public String apply(URI resourceUri) throws ResourceException {
        try {
            return IOUtils.toString(resourceUri, charset);
        } catch (IOException e) {
            throw new ResourceException(format("An I/O exception occurred while reading %s.", resourceUri), e);
        }
    }
}