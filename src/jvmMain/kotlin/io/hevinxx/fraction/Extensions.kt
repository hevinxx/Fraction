package io.hevinxx.fraction

import java.math.BigInteger

fun Int.toFraction(): Fraction = Fraction(this, 1)
fun Long.toFraction(): Fraction = Fraction(this, 1)
fun Float.toFraction(): Fraction = Fraction(this, 1)
fun Double.toFraction(): Fraction = Fraction(this, 1)

operator fun Int.plus(other: Fraction) = other.plus(this)
operator fun Long.plus(other: Fraction) = other.plus(this)
operator fun Float.plus(other: Fraction) = other.plus(this)
operator fun Double.plus(other: Fraction) = other.plus(this)
operator fun BigInteger.plus(other: Fraction) = other.plus(this)

operator fun Int.minus(other: Fraction) = this.toFraction() - other
operator fun Long.minus(other: Fraction) = this.toFraction() - other
operator fun Float.minus(other: Fraction) = this.toFraction() - other
operator fun Double.minus(other: Fraction) = this.toFraction() - other
operator fun BigInteger.minus(other: Fraction) = this.toDouble().toFraction() - other

operator fun Int.times(other: Fraction) = other.times(this)
operator fun Long.times(other: Fraction) = other.times(this)
operator fun Float.times(other: Fraction) = other.times(this)
operator fun Double.times(other: Fraction) = other.times(this)
operator fun BigInteger.times(other: Fraction) = other.times(this)

operator fun Int.div(other: Fraction) = this.toFraction() / other
operator fun Long.div(other: Fraction) = this.toFraction() / other
operator fun Float.div(other: Fraction) = this.toFraction() / other
operator fun Double.div(other: Fraction) = this.toFraction() / other
operator fun BigInteger.div(other: Fraction) = this.toDouble().toFraction() / other

fun Int.toRational(): Rational = Rational(this, 1)
fun Long.toRational(): Rational = Rational(this, 1)
fun BigInteger.toRational(): Rational = Rational(this, 1)

operator fun Int.plus(other: Rational) = other.plus(this)
operator fun Long.plus(other: Rational) = other.plus(this)
operator fun BigInteger.plus(other: Rational) = other.plus(this)

operator fun Int.minus(other: Rational) = this.toRational() - other
operator fun Long.minus(other: Rational) = this.toRational() - other
operator fun BigInteger.minus(other: Rational) = this.toRational() - other

operator fun Int.times(other: Rational) = other.times(this)
operator fun Long.times(other: Rational) = other.times(this)
operator fun BigInteger.times(other: Rational) = other.times(this)

operator fun Int.div(other: Rational) = this.toRational() / other
operator fun Long.div(other: Rational) = this.toRational() / other
operator fun BigInteger.div(other: Rational) = this.toRational() / other