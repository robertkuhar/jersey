/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2010-2014 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.glassfish.jersey.server.internal.inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.server.RequestContextBuilder;
import org.glassfish.jersey.server.ResourceConfig;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Taken from Jersey-1: jersey-tests: com.sun.jersey.impl.methodparams.PathParamAsPrimitiveTest
 *
 * @author Paul.Sandoz at Sun.Com
 */
public class PathParamAsPrimitiveTest {

    ApplicationHandler app;

    private ApplicationHandler createApplication(Class<?>... classes) {
        return new ApplicationHandler(new ResourceConfig(classes));
    }

    public PathParamAsPrimitiveTest() {
        app = createApplication(
                ResourceUriBoolean.class,
                ResourceUriByte.class,
                ResourceUriShort.class,
                ResourceUriInt.class,
                ResourceUriLong.class,
                ResourceUriFloat.class,
                ResourceUriDouble.class,
                ResourceUriBooleanWrapper.class,
                ResourceUriByteWrapper.class,
                ResourceUriShortWrapper.class,
                ResourceUriIntWrapper.class,
                ResourceUriLongWrapper.class,
                ResourceUriFloatWrapper.class,
                ResourceUriDoubleWrapper.class);
    }

    @Path("/boolean/{arg}")
    public static class ResourceUriBoolean {

        @GET
        public String doGet(@PathParam("arg") boolean v) {
            assertEquals(true, v);
            return "content";
        }
    }

    @Path("/byte/{arg}")
    public static class ResourceUriByte {

        @GET
        public String doGet(@PathParam("arg") byte v) {
            assertEquals(127, v);
            return "content";
        }
    }

    @Path("/short/{arg}")
    public static class ResourceUriShort {

        @GET
        public String doGet(@PathParam("arg") short v) {
            assertEquals(32767, v);
            return "content";
        }
    }

    @Path("/int/{arg}")
    public static class ResourceUriInt {

        @GET
        public String doGet(@PathParam("arg") int v) {
            assertEquals(2147483647, v);
            return "content";
        }
    }

    @Path("/long/{arg}")
    public static class ResourceUriLong {

        @GET
        public String doGet(@PathParam("arg") long v) {
            assertEquals(9223372036854775807L, v);
            return "content";
        }
    }

    @Path("/float/{arg}")
    public static class ResourceUriFloat {

        @GET
        public String doGet(@PathParam("arg") float v) {
            assertEquals(3.14159265f, v, 0f);
            return "content";
        }
    }

    @Path("/double/{arg}")
    public static class ResourceUriDouble {

        @GET
        public String doGet(@PathParam("arg") double v) {
            assertEquals(3.14159265358979d, v, 0d);
            return "content";
        }
    }

    @Path("/boolean/wrapper/{arg}")
    public static class ResourceUriBooleanWrapper {

        @GET
        public String doGet(@PathParam("arg") Boolean v) {
            assertEquals(true, v);
            return "content";
        }
    }

    @Path("/byte/wrapper/{arg}")
    public static class ResourceUriByteWrapper {

        @GET
        public String doGet(@PathParam("arg") Byte v) {
            assertEquals(127, v.byteValue());
            return "content";
        }
    }

    @Path("/short/wrapper/{arg}")
    public static class ResourceUriShortWrapper {

        @GET
        public String doGet(@PathParam("arg") Short v) {
            assertEquals(32767, v.shortValue());
            return "content";
        }
    }

    @Path("/int/wrapper/{arg}")
    public static class ResourceUriIntWrapper {

        @GET
        public String doGet(@PathParam("arg") Integer v) {
            assertEquals(2147483647, v.intValue());
            return "content";
        }
    }

    @Path("/long/wrapper/{arg}")
    public static class ResourceUriLongWrapper {

        @GET
        public String doGet(@PathParam("arg") Long v) {
            assertEquals(9223372036854775807L, v.longValue());
            return "content";
        }
    }

    @Path("/float/wrapper/{arg}")
    public static class ResourceUriFloatWrapper {

        @GET
        public String doGet(@PathParam("arg") Float v) {
            assertEquals(3.14159265f, v, 0f);
            return "content";
        }
    }

    @Path("/double/wrapper/{arg}")
    public static class ResourceUriDoubleWrapper {

        @GET
        public String doGet(@PathParam("arg") Double v) {
            assertEquals(3.14159265358979d, v, 0d);
            return "content";
        }
    }

    void _test(String type, String value) throws Exception {
        app.apply(RequestContextBuilder.from("/" + type + "/" + value, "GET").build()).get().getEntity();
        app.apply(RequestContextBuilder.from("/" + type + "/wrapper/" + value, "GET").build()).get().getEntity();
    }

    @Test
    public void testGetBoolean() throws Exception {
        _test("boolean", "true");
    }

    @Test
    public void testGetByte() throws Exception {
        _test("byte", "127");
    }

    @Test
    public void testGetShort() throws Exception {
        _test("short", "32767");
    }

    @Test
    public void testGetInt() throws Exception {
        _test("int", "2147483647");
    }

    @Test
    public void testGetLong() throws Exception {
        _test("long", "9223372036854775807");
    }

    @Test
    public void testGetFloat() throws Exception {
        _test("float", "3.14159265");
    }

    @Test
    public void testGetDouble() throws Exception {
        _test("double", "3.14159265358979");
    }

    @Test
    public void testBadPrimitiveValue() throws Exception {
        ContainerResponse responseContext = app.apply(RequestContextBuilder.from("/int/abcdef", "GET").build()).get();
        assertEquals(404, responseContext.getStatus());
    }

    @Test
    public void testBadPrimitiveWrapperValue() throws Exception {
        ContainerResponse responseContext = app.apply(RequestContextBuilder.from("/int/wrapper/abcdef", "GET").build()).get();
        assertEquals(404, responseContext.getStatus());
    }
}
