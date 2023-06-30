package io.hevinxx.fraction

import java.math.BigInteger
import java.rmi.NoSuchObjectException
import java.util.*

class Rational(val numerator: BigInteger, val denominator: BigInteger) : Number(), Comparable<Rational> {

    constructor(numerator: Int, denominator: Int) : this(numerator.toBigInteger(), denominator.toBigInteger())
    constructor(numerator: Int, denominator: Long) : this(numerator.toBigInteger(), denominator.toBigInteger())
    constructor(numerator: Int, denominator: BigInteger) : this(numerator.toBigInteger(), denominator)
    constructor(numerator: Long, denominator: Int) : this(numerator.toBigInteger(), denominator.toBigInteger())
    constructor(numerator: Long, denominator: Long) : this(numerator.toBigInteger(), denominator.toBigInteger())
    constructor(numerator: Long, denominator: BigInteger) : this(numerator.toBigInteger(), denominator)
    constructor(numerator: BigInteger, denominator: Int) : this(numerator, denominator.toBigInteger())
    constructor(numerator: BigInteger, denominator: Long) : this(numerator, denominator.toBigInteger())

    fun isNaN(): Boolean = this == NaN
    fun isInfinite(): Boolean = numerator != BigInteger.ZERO && denominator == BigInteger.ZERO
    fun isFinite(): Boolean = denominator != BigInteger.ZERO
    fun isZero(): Boolean = isFinite() && numerator == BigInteger.ZERO
    fun isPosInf(): Boolean = isInfinite() && numerator > BigInteger.ZERO
    fun isNegInf(): Boolean = isInfinite() && numerator < BigInteger.ZERO

    override fun compareTo(other: Rational): Int {
        return when {
            this == other -> 0
            isNaN() -> 1
            other.isNaN() -> -1
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
        return when {
            isNaN() -> Double.NaN
            isPosInf() -> Double.POSITIVE_INFINITY
            isNegInf() -> Double.NEGATIVE_INFINITY
            else -> numerator.toDouble() / denominator.toDouble()
        }
    }

    override fun toFloat(): Float {
        return toDouble().toFloat()
    }

    override fun toInt(): Int {
        return toLong().toInt()
    }

    override fun toLong(): Long {
        return when {
            isNaN() -> 0L
            isPosInf() -> Long.MAX_VALUE
            isNegInf() -> Long.MIN_VALUE
            else -> (numerator / denominator).toLong()
        }
    }

    fun toBigInteger(): BigInteger {
        return when {
            isNaN() -> BigInteger.ZERO
            isPosInf() -> throw NoSuchObjectException("BigInteger has no infinity.")
            isNegInf() -> throw NoSuchObjectException("BigInteger has no infinity.")
            else -> numerator / denominator
        }
    }

    fun toFraction(): Fraction {
        return toDouble().toFraction()
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
            denominator == BigInteger.ONE -> numerator.toString()
            else -> "$numerator/$denominator"
        }
    }

    override fun equals(other: Any?): Boolean {
        return (other is Rational)
                && numerator == other.numerator
                && denominator == other.denominator
    }

    override fun hashCode(): Int {
        return Objects.hash(numerator, denominator)
    }

    fun reduced(): Rational {
        return when {
            isNaN() -> NaN
            isPosInf() -> POSITIVE_INFINITY
            isNegInf() -> NEGATIVE_INFINITY
            isZero() -> ZERO
            else -> {
                val gcd = numerator.gcd(denominator)
                Rational(numerator / gcd, denominator / gcd)
            }
        }
    }

    operator fun plus(other: Rational): Rational {
        return Rational(
            numerator * other.denominator + denominator * other.numerator,
            denominator * other.denominator
        ).reduced()
    }

    operator fun plus(other: Int): Rational = this + other.toRational()
    operator fun plus(other: Long): Rational = this + other.toRational()
    operator fun plus(other: BigInteger): Rational = this + other.toRational()
    operator fun plus(other: Fraction): Fraction = other + this

    operator fun minus(other: Rational): Rational {
        return Rational(
            numerator * other.denominator - denominator * other.numerator,
            denominator * other.denominator
        ).reduced()
    }

    operator fun minus(other: Int): Rational = this - other.toRational()
    operator fun minus(other: Long): Rational = this - other.toRational()
    operator fun minus(other: BigInteger): Rational = this - other.toRational()
    operator fun minus(other: Fraction): Fraction = this.toFraction() - other

    operator fun times(other: Rational): Rational {
        return Rational(
            numerator * other.numerator,
            denominator * other.denominator
        ).reduced()
    }

    operator fun times(other: Int): Rational = this * other.toRational()
    operator fun times(other: Long): Rational = this * other.toRational()
    operator fun times(other: BigInteger): Rational = this * other.toRational()
    operator fun times(other: Fraction): Fraction = other * this

    operator fun div(other: Rational): Rational {
        return Rational(
            numerator * other.denominator,
            denominator * other.numerator
        ).reduced()
    }

    operator fun div(other: Int): Rational = this / other.toRational()
    operator fun div(other: Long): Rational = this / other.toRational()
    operator fun div(other: BigInteger): Rational = this / other.toRational()
    operator fun div(other: Fraction): Fraction = this.toFraction() / other

    companion object {
        val POSITIVE_INFINITY = Rational(1, 0)
        val NEGATIVE_INFINITY = Rational(-1, 0)
        val NaN = Rational(0, 0)
        val ZERO = Rational(0, 1)
    }
}