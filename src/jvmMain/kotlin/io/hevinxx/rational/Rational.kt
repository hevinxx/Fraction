package io.hevinxx.rational

import java.math.BigInteger
import java.rmi.NoSuchObjectException
import java.util.Objects

class Rational(val numerator: BigInteger, val denominator: BigInteger) : Number(), Comparable<Rational> {

    constructor(numerator: Int, denominator: Int) : this(numerator.toBigInteger(), denominator.toBigInteger())
    constructor(numerator: Int, denominator: Long) : this(numerator.toBigInteger(), denominator.toBigInteger())
    constructor(numerator: Int, denominator: BigInteger) : this(numerator.toBigInteger(), denominator)
    constructor(numerator: Long, denominator: Int) : this(numerator.toBigInteger(), denominator.toBigInteger())
    constructor(numerator: Long, denominator: Long) : this(numerator.toBigInteger(), denominator.toBigInteger())
    constructor(numerator: Long, denominator: BigInteger) : this(numerator.toBigInteger(), denominator)
    constructor(numerator: BigInteger, denominator: Int) : this(numerator, denominator.toBigInteger())
    constructor(numerator: BigInteger, denominator: Long) : this(numerator, denominator.toBigInteger())

    override fun compareTo(other: Rational): Int {
        return when {
            this == other -> 0
            this == NaN -> 1
            other == NaN -> -1
            this == POSITIVE_INFINITY || other == NEGATIVE_INFINITY -> 1
            this == NEGATIVE_INFINITY || other == POSITIVE_INFINITY -> -1
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
        return numerator.toDouble() / denominator.toDouble()
    }

    override fun toFloat(): Float {
        return numerator.toFloat() / denominator.toFloat()
    }

    override fun toInt(): Int {
        return toLong().toInt()
    }

    override fun toLong(): Long {
        return when (this) {
            NaN -> 0L
            POSITIVE_INFINITY -> Long.MAX_VALUE
            NEGATIVE_INFINITY -> Long.MIN_VALUE
            else -> (numerator / denominator).toLong()
        }
    }

    fun toBigInteger(): BigInteger {
        return when (this) {
            NaN -> BigInteger.ZERO
            POSITIVE_INFINITY -> throw NoSuchObjectException("BigInteger has no infinity.")
            NEGATIVE_INFINITY -> throw NoSuchObjectException("BigInteger has no infinity.")
            else -> numerator / denominator
        }
    }

    override fun toShort(): Short {
        return toInt().toShort()
    }

    override fun toString(): String {
        return when (this) {
            NaN -> "NaN"
            POSITIVE_INFINITY -> "Infinity"
            NEGATIVE_INFINITY -> "-Infinity"
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
        return if (numerator == BigInteger.ZERO) {
            if (denominator == BigInteger.ZERO) NaN else ZERO
        } else if (denominator == BigInteger.ZERO) {
            if (numerator > BigInteger.ZERO) POSITIVE_INFINITY else NEGATIVE_INFINITY
        } else {
            val gcd = numerator.gcd(denominator)
            Rational(numerator / gcd, denominator / gcd)
        }
    }

    operator fun plus(other: Rational): Rational {
        return Rational(
            numerator * other.denominator + denominator * other.numerator,
            denominator * other.denominator
        ).reduced()
    }

    operator fun minus(other: Rational): Rational {
        return Rational(
            numerator * other.denominator - denominator * other.numerator,
            denominator * other.denominator
        ).reduced()
    }

    operator fun times(other: Rational): Rational {
        return Rational(
            numerator * other.numerator,
            denominator * other.denominator
        ).reduced()
    }

    operator fun div(other: Rational): Rational {
        return Rational(
            numerator * other.denominator,
            denominator * other.numerator
        ).reduced()
    }

    companion object {
        val POSITIVE_INFINITY = Rational(1, 0)
        val NEGATIVE_INFINITY = Rational(-1, 0)
        val NaN = Rational(0, 0)
        val ZERO = Rational(0, 1)
    }
}