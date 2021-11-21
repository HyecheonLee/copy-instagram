package com.hyecheon.copyinstagram.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/11/21
 */

@Profile("!prod")
@Component
@Aspect
class ExecutionAdvice {
	private val log = LoggerFactory.getLogger(this::class.java)

	@Around("@annotation(LogExecutionTime)")
	fun annotationExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
		return execution(joinPoint)
	}

	@Around("execution(* com.hyecheon.copyinstagram.service..*(..))")
	fun serviceExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
		return execution(joinPoint)
	}

	private fun execution(joinPoint: ProceedingJoinPoint): Any? {
		val start = System.currentTimeMillis()
		val signature = joinPoint.signature.toShortString()
		val result = try {
			with(StringBuilder("start -> Executing $signature, ")) {
				appendParameters(joinPoint.args)
				log.info(toString())
			}
			joinPoint.proceed()
		} catch (throwable: Throwable) {
			log.error("*** Exception during executing $signature,", throwable)
			throw throwable
		}
		val duration = System.currentTimeMillis() - start
		log.info("end -> Finished executing: $signature, returned: '$result', duration: $duration ms")
		return result
	}

	private fun StringBuilder.appendParameters(args: Array<Any>) {
		append("parameters: [")
		args.forEachIndexed { i, p ->
			append(p.javaClass.simpleName).append("(").append(p.toString()).append(")")
			if (i < args.size - 1) append(", ")
		}
		append("]")
	}

}