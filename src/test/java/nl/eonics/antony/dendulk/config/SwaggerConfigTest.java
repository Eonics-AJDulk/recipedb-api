package nl.eonics.antony.dendulk.config;

import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SwaggerConfigTest {

    @Test
    void testSwaggerConfig() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        Docket docket = swaggerConfig.api();

        assertNotNull(docket);
    }
}
