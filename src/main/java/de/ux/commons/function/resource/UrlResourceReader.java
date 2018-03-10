package de.ux.commons.function.resource;

import de.ux.commons.function.function.Function;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static java.lang.String.format;

public class UrlResourceReader implements Function<URL, String> {

    private final Charset charset;

    public UrlResourceReader(Charset charset) {
        this.charset = charset;
    }

    @Override
    public String apply(URL resourceUrl) throws ResourceException {
        try {
            return IOUtils.toString(resourceUrl, charset);
        } catch (IOException e) {
            throw new ResourceException(format("An I/O exception occurred while reading %s.", resourceUrl), e);
        }
    }
}
