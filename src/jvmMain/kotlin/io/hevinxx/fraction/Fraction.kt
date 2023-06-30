package io.hevinxx.fraction

import java.math.BigInteger
import java.rmi.NoSuchObjectException
import java.util.*

class Fraction(val numerator: Double, val denominator: Double) : Number(), Comparable<Fraction> {

    constructor(numerator: Int, denominator: Int) : this(numerator.toDouble(), denominator.toDouble())
    constructor(numerator: Int, denominator: Long) : this(numerator.toDouble(), denominator.toDouble())
    constructor(numerator: Int, denominator: Float) : this(numerator.toDouble(), denominator.toDouble())
    constructor(numerator: Int, denominator: Double) : this(numerator.toDouble(), denominator)
    constructor(numerator: Long, denominator: Int) : this(numerator.toDouble(), denominator.toDouble())
    constructor(numerator: Long, denominator: Long) : this(numerator.toDouble(), denominator.toDouble())
    constructor(numerator: Long, denominator: Float) : this(numerator.toDouble(), denominator.toDouble())
    constructor(numerator: Long, denominator: Double) : this(numerator.toDouble(), denominator)
    constructor(numerator: Float, denominator: Int) : this(numerator.toDouble(), denominator.toDouble())
    constructor(numerator: Float, denominator: Long) : this(numerator.toDouble(), denominator.toDouble())
    constructor(numerator: Float, denominator: Float) : this(numerator.toDouble(), denominator.toDouble())
    constructor(numerator: Float, denominator: Double) : this(numerator.toDouble(), denominator)
    constructor(numerator: Double, denominator: Int) : this(numerator, denominator.toDouble())
    constructor(numerator: Double, denominator: Long) : this(numerator, denominator.toDouble())
    constructor(numerator: Double, denominator: Float) : this(numerator, denominator.toDouble())

    fun isNaN(): Boolean = this == NaN
    fun isInfinite(): Boolean = numerator != 0.0 && denominator == 0.0
    fun isFinite(): Boolean = denominator != 0.0
    fun isZero(): Boolean = isFinite() && numerator == 0.0
    fun isPosInf(): Boolean = isInfinite() && numerator > 0.0
    fun isNegInf(): Boolean = isInfinite() && numerator < 0.0

    override fun compareTo(other: Fraction): Int {
        return when {
            this == other -> 0
            isNaN() -> 1
            other.isNaN() -> -1
            this.isPosInf() && other.isPosInf() -> 0
            this.isNegInf() && other.isNegInf() -> 0
            this.isPosInf() || other.isNegInf() -> 1
            this.isNegInf() || other.isPosInf() -> -1
            else -> (numerator * other.denominator).compareTo(other.numerator * denominator)
        }
    }

    override fun toByte(): Byte {
        return toLong().toByte()
    }

    override fun toChar(): Char {
        return toInt().toChar()
    }

    override fun toDouble(): Double {
        return numerator / denominator
    }

    override fun toFloat(): Float {
        return numerator.toFloat() / denominator.toFloat()
    }

    override fun toInt(): Int {
        return toLong().toInt()
    }

    override fun toLong(): Long {
        return when {
            isNaN() -> 0L
            isPosInf() -> Long.MAX_VALUE
            isNegInf() -> Long.MIN_VALUE
            else -> toDouble().toLong()
        }
    }

    override fun toShort(): Short {
        return toInt().toShort()
    }

    override fun toString(): String {
        return when {
            isNaN() -> "NaN"
            isPosInf() -> "Infinity"
            isNegInf() -> "-Infinity"
            isZero() -> "0"
            else -> "$numerator/$denominator"
        }
    }

    override fun equals(other: Any?): Boolean {
        return (other is Fraction)
                && numerator == other.numerator
                && denominator == other.denominator
    }

    override fun hashCode(): Int {
        return Objects.hash(numerator, denominator)
    }

    operator fun plus(other: Fraction): Fraction {
        return Fraction(
            numerator * other.denominator + denominator * other.numerator,
            denominator * other.denominator
        )
    }

    operator fun plus(other: Int): Fraction = this + other.toFraction()
    operator fun plus(other: Long): Fraction = this + other.toFraction()
    operator fun plus(other: Float): Fraction = this + other.toFraction()
    operator fun plus(other: Double): Fraction = this + other.toFraction()
    operator fun plus(other: Rational): Fraction = this + other.toFraction()
    operator fun plus(other: BigInteger): Fraction = this + other.toDouble().toFraction()

    operator fun minus(other: Fraction): Fraction {
        return Fraction(
            numerator * other.denominator - denominator * other.numerator,
            denominator * other.denominator
        )
    }

    operator fun minus(other: Int): Fraction = this - other.toFraction()
    operator fun minus(other: Long): Fraction = this - other.toFraction()
    operator fun minus(other: Float): Fraction = this - other.toFraction()
    operator fun minus(other: Double): Fraction = this - other.toFraction()
    operator fun minus(other: Rational): Fraction = this - other.toFraction()
    operator fun minus(other: BigInteger): Fraction = this - other.toDouble().toFraction()

    operator fun times(other: Fraction): Fraction {
        return Fraction(
            numerator * other.numerator,
            denominator * other.denominator
        )
    }

    operator fun times(other: Int): Fraction = this * other.toFraction()
    operator fun times(other: Long): Fraction = this * other.toFraction()
    operator fun times(other: Float): Fraction = this * other.toFraction()
    operator fun times(other: Double): Fraction = this * other.toFraction()
    operator fun times(other: Rational): Fraction = this * other.toFraction()
    operator fun times(other: BigInteger): Fraction = this * other.toDouble().toFraction()

    operator fun div(other: Fraction): Fraction {
        return Fraction(
            numerator * other.denominator,
            denominator * other.numerator
        )
    }

    operator fun div(other: Int): Fraction = this / other.toFraction()
    operator fun div(other: Long): Fraction = this / other.toFraction()
    operator fun div(other: Float): Fraction = this / other.toFraction()
    operator fun div(other: Double): Fraction = this / other.toFraction()
    operator fun div(other: Rational): Fraction = this / other.toFraction()
    operator fun div(other: BigInteger): Fraction = this / other.toDouble().toFraction()

    companion object {
        val POSITIVE_INFINITY = 1.0 over 0
        val NEGATIVE_INFINITY = -1.0 over 0
        val NaN = 0.0 over 0
        val ZERO = 0.0 over 1
    }
}