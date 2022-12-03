package ru.hzerr.fx.framework.core.context.config;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Version {

    private final int major;
    private final int minor;
    private final int patch;
    private final int build;

    public Version(Integer major, Integer minor, Integer patch, Integer build) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.build = build;
    }

    public Integer getMajor() {
        return major;
    }

    public Integer getMinor() {
        return minor;
    }

    public Integer getPatch() {
        return patch;
    }

    public Integer getBuild() {
        return build;
    }

    public boolean isBefore(Version version) {
        return version.build > this.build;
    }

    public boolean isAfter(Version version) {
        return version.build < this.build;
    }

    public static Version of(String version) {
        String[] parts = version.split("\\.");

        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid version: " + version);
        }

        return new Version(Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Version version = (Version) o;

        return new EqualsBuilder()
                .append(major, version.major)
                .append(minor, version.minor)
                .append(patch, version.patch)
                .append(build, version.build)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(major)
                .append(minor)
                .append(patch)
                .append(build)
                .toHashCode();
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch + "." + build;
    }
}
