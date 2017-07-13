/*
 * Copyright (c) 2016-2017 Cisco Systems Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ciscospark;

import android.util.Log;

import com.ciscospark.auth.AuthorizeListener;
import com.ciscospark.auth.JWTStrategy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created on 12/06/2017.
 */
public class SparkTest {
    private String auth_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyMiIsIm5hbWUiOiJ1c2VyICMyIiwiaXNzIjoiWTJselkyOXpjR0Z5YXpvdkwzVnpMMDlTUjBGT1NWcEJWRWxQVGk5aU5tSmtNemRtTUMwNU56RXhMVFEzWldVdE9UUTFOUzAxWWpZNE1tUTNNRFV6TURZIn0.5VvjLtuD-jn9hXtLthnGDdhxlIHaoKZbI80y1vK2-bY";
    private static final String TAG = "SparkTest";

    @Test
    public void version() throws Exception {
        Spark spark = new Spark();
        assertEquals(spark.version(), "0.1");
    }

    @Test
    public void authorize() throws Exception {
        Spark spark = new Spark();
        JWTStrategy strategy = new JWTStrategy(auth_token);
        spark.init(strategy);
        spark.authorize(new AuthorizeListener() {
            @Override
            public void onSuccess() {
                assertTrue(spark.isAuthorized());
                Log.i(TAG, "get token: " + strategy.getToken().getAccessToken());
            }

            @Override
            public void onFailed(SparkError<AuthError> E) {
                assertFalse(true);
            }
        });
        Thread.sleep(10 * 1000);
    }
}