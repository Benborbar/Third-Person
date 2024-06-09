package net.leawind.mc.util.math.vector.impl;


import net.leawind.mc.util.math.vector.api.Vector3d;
import org.jetbrains.annotations.NotNull;

public class Vector3dImpl implements Vector3d {
	private double x;
	private double y;
	private double z;

	public Vector3dImpl (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public int hashCode () {
		final int l = 31;
		int       r = 1;
		r = l * r + Double.hashCode(x);
		r = l * r + Double.hashCode(y);
		r = l * r + Double.hashCode(z);
		return r;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Vector3d other = (Vector3d)obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x())) {
			return false;
		}
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y())) {
			return false;
		}
		return Double.doubleToLongBits(z) == Double.doubleToLongBits(other.z());
	}

	@Override
	public String toString () {
		return String.format("Vector3d(%f, %f, %f)", x, y, z);
	}

	@Override
	public double x () {
		return x;
	}

	@Override
	public double y () {
		return y;
	}

	@Override
	public double z () {
		return z;
	}

	@Override
	public void x (double x) {
		this.x = x;
	}

	@Override
	public void y (double y) {
		this.y = y;
	}

	@Override
	public void z (double z) {
		this.z = z;
	}

	@Override
	public Vector3d set (double d) {
		return set(d, d, d);
	}

	@Override
	public Vector3d set (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public Vector3d set (@NotNull Vector3d v) {
		return set(v.x(), v.y(), v.z());
	}

	@Override
	public Vector3d add (@NotNull Vector3d v, @NotNull Vector3d dest) {
		return add(v.x(), v.y(), v.z(), dest);
	}

	@Override
	public Vector3d add (double x, double y, double z, @NotNull Vector3d dest) {
		dest.x(this.x + x);
		dest.y(this.y + y);
		dest.z(this.z + z);
		return dest;
	}

	@Override
	public Vector3d add (@NotNull Vector3d v) {
		return add(v.x(), v.y(), v.z());
	}

	@Override
	public Vector3d add (double x, double y, double z) {
		this.x = this.x + x;
		this.y = this.y + y;
		this.z = this.z + z;
		return this;
	}

	@Override
	public Vector3d add (double d) {
		return add(d, d, d);
	}

	@Override
	public Vector3d sub (@NotNull Vector3d v, @NotNull Vector3d dest) {
		return sub(v.x(), v.y(), v.z(), dest);
	}

	@Override
	public Vector3d sub (double x, double y, double z, @NotNull Vector3d dest) {
		dest.x(this.x - x);
		dest.y(this.y - y);
		dest.z(this.z - z);
		return dest;
	}

	@Override
	public Vector3d sub (@NotNull Vector3d v) {
		return sub(v.x(), v.y(), v.z());
	}

	@Override
	public Vector3d sub (double x, double y, double z) {
		this.x = this.x - x;
		this.y = this.y - y;
		this.z = this.z - z;
		return this;
	}

	@Override
	public Vector3d mul (@NotNull Vector3d v, @NotNull Vector3d dest) {
		return mul(v.x(), v.y(), v.z(), dest);
	}

	@Override
	public Vector3d mul (double x, double y, double z, @NotNull Vector3d dest) {
		dest.x(this.x * x);
		dest.y(this.y * y);
		dest.z(this.z * z);
		return dest;
	}

	@Override
	public Vector3d mul (@NotNull Vector3d v) {
		return mul(v.x(), v.y(), v.z());
	}

	@Override
	public Vector3d mul (double x, double y, double z) {
		this.x = this.x * x;
		this.y = this.y * y;
		this.z = this.z * z;
		return this;
	}

	@Override
	public Vector3d mul (double d) {
		return mul(d, d, d);
	}

	@Override
	public Vector3d div (@NotNull Vector3d v, @NotNull Vector3d dest) {
		return div(v.x(), v.y(), v.z(), dest);
	}

	@Override
	public Vector3d div (double x, double y, double z, @NotNull Vector3d dest) {
		dest.x(this.x / x);
		dest.y(this.y / y);
		dest.z(this.z / z);
		return dest;
	}

	@Override
	public Vector3d div (@NotNull Vector3d v) {
		return div(v.x(), v.y(), v.z());
	}

	@Override
	public Vector3d div (double x, double y, double z) {
		this.x = this.x / x;
		this.y = this.y / y;
		this.z = this.z / z;
		return this;
	}

	@Override
	public Vector3d div (double d) {
		return div(d, d, d);
	}

	@Override
	public Vector3d pow (@NotNull Vector3d v, @NotNull Vector3d dest) {
		return pow(v.x(), v.y(), v.z(), dest);
	}

	@Override
	public Vector3d pow (double x, double y, double z, @NotNull Vector3d dest) {
		dest.x(Math.pow(this.x, x));
		dest.y(Math.pow(this.y, y));
		dest.z(Math.pow(this.z, z));
		return dest;
	}

	@Override
	public Vector3d pow (double d, @NotNull Vector3d dest) {
		return pow(d, d, d, dest);
	}

	@Override
	public Vector3d pow (@NotNull Vector3d v) {
		return pow(v.x(), v.y(), v.z());
	}

	@Override
	public Vector3d pow (double x, double y, double z) {
		this.x = Math.pow(this.x, x);
		this.y = Math.pow(this.y, y);
		this.z = Math.pow(this.z, z);
		return this;
	}

	@Override
	public Vector3d pow (double d) {
		return pow(d, d, d);
	}

	@Override
	public double lengthSquared () {
		return x * x + y * y + z * z;
	}

	@Override
	public double distance (@NotNull Vector3d v) {
		return distance(v.x(), v.y(), v.z());
	}

	@Override
	public double distance (double x, double y, double z) {
		double dx = this.x - x;
		double dy = this.y - y;
		double dz = this.z - z;
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	@Override
	public double distanceSquared (double x, double y, double z) {
		double dx = this.x - x;
		double dy = this.y - y;
		double dz = this.z - z;
		return dx * dx + dy * dy + dz * dz;
	}

	@Override
	public double length () {
		return Math.sqrt(x * x + y * y + z * z);
	}

	@Override
	public Vector3d normalize () {
		double len = length();
		x /= len;
		y /= len;
		z /= len;
		return this;
	}

	@Override
	public Vector3d normalizeSafely () {
		double len = length();
		if (len == 0) {
			throw new IllegalStateException("Vector length is 0");
		}
		x /= len;
		y /= len;
		z /= len;
		return this;
	}

	@Override
	public Vector3d normalizeSafely (double length) {
		double len = length() / length;
		if (len == 0) {
			throw new IllegalStateException("Vector length is 0");
		}
		x /= len;
		y /= len;
		z /= len;
		return this;
	}

	@Override
	public Vector3d rotateTo (@NotNull Vector3d direction) {
		return direction.normalize(length());
	}

	@Override
	public Vector3d normalize (double length) {
		double a = length / length();
		x *= a;
		y *= a;
		z *= a;
		return this;
	}

	@Override
	public Vector3d zero () {
		x = y = z = 0;
		return this;
	}

	@Override
	public Vector3d negate () {
		x = -x;
		y = -y;
		z = -z;
		return this;
	}

	@Override
	public double dot (@NotNull Vector3d v) {
		return x * v.x() + y * v.y() + z * v.z();
	}

	@Override
	public Vector3d clamp (double min, double max) {
		x = Math.min(Math.max(x, min), max);
		y = Math.min(Math.max(y, min), max);
		z = Math.min(Math.max(z, min), max);
		return this;
	}

	@Override
	public Vector3d clamp (@NotNull Vector3d min, @NotNull Vector3d max) {
		x = Math.min(Math.max(x, min.x()), max.x());
		y = Math.min(Math.max(y, min.y()), max.y());
		z = Math.min(Math.max(z, min.z()), max.z());
		return this;
	}

	@Override
	public Vector3d lerp (@NotNull Vector3d end, double t) {
		x += (end.x() - x) * t;
		y += (end.y() - y) * t;
		z += (end.z() - z) * t;
		return this;
	}

	@Override
	public Vector3d lerp (@NotNull Vector3d end, @NotNull Vector3d t) {
		x += (end.x() - x) * t.x();
		y += (end.y() - y) * t.y();
		z += (end.z() - z) * t.z();
		return this;
	}

	@Override
	public Vector3d absolute () {
		x = Math.abs(x);
		y = Math.abs(y);
		z = Math.abs(z);
		return this;
	}

	@Override
	public Vector3d copy () {
		return Vector3d.of(x, y, z);
	}
}
