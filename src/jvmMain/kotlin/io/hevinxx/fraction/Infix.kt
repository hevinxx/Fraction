package io.hevinxx.fraction

import java.math.BigInteger

infix fun Int.over(denominator: Float) = Fraction(this, denominator)
infix fun Int.over(denominator: Double) = Fraction(this, denominator)
infix fun Long.over(denominator: Float) = Fraction(this, denominator)
infix fun Long.over(denominator: Double) = Fraction(this, denominator)
infix fun Float.over(denominator: Int) = Fraction(this, denominator)
infix fun Float.over(denominator: Long) = Fraction(this, denominator)
infix fun Float.over(denominator: Float) = Fraction(this, denominator)
infix fun Float.over(denominator: Double) = Fraction(this, denominator)
infix fun Double.over(denominator: Int) = Fraction(this, denominator)
infix fun Double.over(denominator: Long) = Fraction(this, denominator)
infix fun Double.over(denominator: Float) = Fraction(this, denominator)

infix fun Int.over(denominator: Int) = Rational(this, denominator)
infix fun Int.over(denominator: Long) = Rational(this, denominator)
infix fun Int.over(denominator: BigInteger) = Rational(this, denominator)
infix fun Long.over(denominator: Int) = Rational(this, denominator)
infix fun Long.over(denominator: Long) = Rational(this, denominator)
infix fun Long.over(denominator: BigInteger) = Rational(this, denominator)
infix fun BigInteger.over(denominator: Int) = Rational(this, denominator)
infix fun BigInteger.over(denominator: Long) = Rational(this, denominator)
infix fun BigInteger.over(denominator: BigInteger) = Rational(this, denominator)