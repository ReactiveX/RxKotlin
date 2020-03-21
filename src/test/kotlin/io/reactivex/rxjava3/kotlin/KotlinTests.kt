/**
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.reactivex.rxjava3.kotlin

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class KotlinTests {
    @Mock var a: ScriptAssertion = uninitialized()

    @Before fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Suppress("BASE_WITH_NULLABLE_UPPER_BOUND")
    fun <T> received() = { result: T -> a.received(result) }

    interface ScriptAssertion {
        fun error(e: Throwable?)

        fun received(e: Any?)
    }

    private fun <T> uninitialized() = null as T
}
