package io.github.iosephknecht.jcstress.playground

import org.openjdk.jcstress.annotations.Actor
import org.openjdk.jcstress.annotations.Expect.ACCEPTABLE
import org.openjdk.jcstress.annotations.Expect.ACCEPTABLE_INTERESTING
import org.openjdk.jcstress.annotations.JCStressTest
import org.openjdk.jcstress.annotations.Outcome
import org.openjdk.jcstress.annotations.State
import org.openjdk.jcstress.infra.results.II_Result

@State
@JCStressTest
@Outcome(
    id = ["1, 1"],
    expect = ACCEPTABLE_INTERESTING,
    desc = "Both actors came up with the same value: atomicity failure."
)
@Outcome(id = ["1, 2"], expect = ACCEPTABLE, desc = "actor1 incremented, then actor2.")
@Outcome(id = ["2, 1"], expect = ACCEPTABLE, desc = "actor2 incremented, then actor1.")
open class API_01_Simple {

    private var v: Int = 0

    @Actor
    fun actor1(result: II_Result) {
        result.r1 = ++v
    }

    @Actor
    fun actor2(result: II_Result) {
        result.r2 = ++v
    }
}