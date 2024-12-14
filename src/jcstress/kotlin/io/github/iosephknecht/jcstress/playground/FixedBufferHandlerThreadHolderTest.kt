package io.github.iosephknecht.jcstress.playground

import org.openjdk.jcstress.annotations.Actor
import org.openjdk.jcstress.annotations.JCStressTest
import org.openjdk.jcstress.annotations.Outcome
import org.openjdk.jcstress.annotations.State
import org.openjdk.jcstress.annotations.Expect.*
import org.openjdk.jcstress.infra.results.LL_Result
import org.openjdk.jcstress.infra.results.L_Result


@JCStressTest
@Outcome(
    id = ["Handler, Handler"],
    expect = ACCEPTABLE
)
@Outcome(expect = FORBIDDEN, desc = "Other cases are illegal")
@State
open class FixedBufferHandlerThreadHolderTest {

    private val fixedBufferHandlerThreadHolder = FixedBufferHandlerThreadHolder()

    @Actor
    fun actor1(result: LL_Result) {
        result.r1 = fixedBufferHandlerThreadHolder
            .getHandlerThread()
            .getThreadHandler()
    }

    @Actor
    fun actor2(result: LL_Result) {
        result.r2 = fixedBufferHandlerThreadHolder
            .getHandlerThread()
            .getThreadHandler()
    }
}