package io.hevinxx.rational

import java.math.BigInteger

fun Int.toRational(): Rational = Rational(this, 1)
fun Long.toRational(): Rational = Rational(this, 1)
fun BigInteger.toRational(): Rational = Rational(this, 1)

operator fun Int.plus(other: Rational) = other.plus(this)
operator fun Long.plus(other: Rational) = other.plus(this)
operator fun BigInteger.plus(other: Rational) = other.plus(this)

operator fun Int.minus(other: Rational) = other.minus(this)
operator fun Long.minus(other: Rational) = other.minus(this)
operator fun BigInteger.minus(other: Rational) = other.minus(this)

operator fun Int.times(other: Rational) = other.times(this)
operator fun Long.times(other: Rational) = other.times(this)
operator fun BigInteger.times(other: Rational) = other.times(this)

operator fun Int.div(other: Rational) = other.div(this)
operator fun Long.div(other: Rational) = other.div(this)
operator fun BigInteger.div(other: Rational) = other.div(this)