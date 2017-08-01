package me.giacoppo.examples.kotlin.mvp.repository.interactor.executor

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by Peppe on 17/06/2017.
 */
class JobExecutor : ThreadExecutor {
    private val INITIAL_POOL_SIZE = 3
    private val MAX_POOL_SIZE = 5
    private val KEEP_ALIVE_TIME = 10
    private val KEEP_ALIVE_TIME_UNIT: TimeUnit = TimeUnit.SECONDS
    private val threadPoolExecutor: ThreadPoolExecutor

    private var instance: JobExecutor? = null

    constructor() {
        val workQueue : BlockingQueue<Runnable> = LinkedBlockingQueue()
        threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE,MAX_POOL_SIZE,KEEP_ALIVE_TIME.toLong(),KEEP_ALIVE_TIME_UNIT,workQueue)
    }


    override fun execute(runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    fun getInstance(): ThreadExecutor {
        if (instance == null)
            instance = JobExecutor()

        return instance as ThreadExecutor
    }
}