package io.github.iosephknecht.jcstress.playground

import java.io.Serializable


class Handler(private val looper: Looper) : Serializable {
    override fun toString(): String {
        return "Handler"
    }
}

class Looper : Serializable {

    companion object {

        private val localLooper = ThreadLocal<Looper>()

        fun prepare() {
            if (localLooper.get() != null) error("looper already created")
            localLooper.set(Looper())
        }

        fun myLooper() = localLooper.get()!!
    }
}

class FixedBufferHandlerThreadHolder {

    private var handlerThread: FixedBufferHandlerThread? = null

    fun getHandlerThread(): FixedBufferHandlerThread {
        return synchronized(this) {
            val handlerThread = handlerThread
            if (handlerThread != null) return@synchronized handlerThread
            val newHandlerThread = FixedBufferHandlerThread()
//            newHandlerThread.start()
            this.handlerThread = newHandlerThread
            return@synchronized newHandlerThread
        }
    }
}

class FixedBufferHandlerThread /*: Thread() */ {

    private var looper: Looper? = null
    private var handler: Handler? = null

//    override fun run() {
//        super.run()
//        Looper.prepare()
//        synchronized(this as java.lang.Object) {
//            looper = Looper.myLooper()
//            notifyAll()
//        }
//        while (true) {
//
//        }
//    }

    fun getLooper(): Looper = synchronized(this as java.lang.Object) {
        if (looper == null) {
            looper = Looper()
//            wait()
        }
        return@synchronized looper!!
    }

    fun getThreadHandler(): Handler? {
        if (handler == null) {
            handler = Handler(getLooper())
        }
        return handler
    }
}