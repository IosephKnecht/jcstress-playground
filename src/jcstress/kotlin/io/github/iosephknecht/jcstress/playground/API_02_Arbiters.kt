package io.github.iosephknecht.jcstress.playground

import org.openjdk.jcstress.annotations.Actor
import org.openjdk.jcstress.annotations.Arbiter
import org.openjdk.jcstress.annotations.JCStressTest
import org.openjdk.jcstress.annotations.Outcome
import org.openjdk.jcstress.annotations.State
import org.openjdk.jcstress.annotations.Expect.*
import org.openjdk.jcstress.infra.results.I_Result

@JCStressTest
@Outcome(id = ["1"], expect = ACCEPTABLE_INTERESTING, desc = "One update lost: atomicity failure.")
@Outcome(id = ["2"], expect = ACCEPTABLE, desc = "Actors updated independently.")
@State
open class API_02_Arbiters {

    private var v: Int = 0

    @Actor
    fun actor1() {
        v++
    }

    @Actor
    fun actor2() {
        v++
    }

    @Arbiter
    fun arbiter(result: I_Result) {
        result.r1 = v
    }
}