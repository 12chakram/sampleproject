package com.eng.gp.project.util.date;

import java.util.StringTokenizer;

/**
 * @author dhorlick
 */
public class VersionNumber
{
    private int majorNumber = 0;
    private int minorNumber = 0;
    private Integer revisionNumber = 0;

    public VersionNumber()
    {
    }

    public VersionNumber(final int designatedMajorNumber, final int designatedMinorNumber)
    {
        majorNumber = designatedMajorNumber;
        minorNumber = designatedMinorNumber;
    }

    public VersionNumber(final int designatedMajorNumber, final int designatedMinorNumber,
                         final Integer designatedRevisionNumber)
    {
        this(designatedMajorNumber, designatedMinorNumber);
        revisionNumber = designatedRevisionNumber;
    }

    public int getMajorNumber()
    {
        return majorNumber;
    }

    public int getMinorNumber()
    {
        return minorNumber;
    }

    public Integer getRevisionNumber()
    {
        return revisionNumber;
    }

    public void setMajorNumber(final int designatedMajorNumber)
    {
        majorNumber = designatedMajorNumber;
    }

    public void setMinorNumber(final int designatedMinorNumber)
    {
        minorNumber = designatedMinorNumber;
    }

    public void setRevisionNumber(final Integer designatedRevisionNumber)
    {
        revisionNumber = designatedRevisionNumber;
    }

    public static VersionNumber parseGpecMetadataControllerFirmwareVersionString(final String string)
    {
        if (!string.startsWith("GPEC-"))
            throw new IllegalArgumentException("Version string \""+string+"\" doesn't start with prefix \"GPEC-\".");
        if (string.length()==5)
            throw new IllegalArgumentException("Version string \"" + string + "\" has no trailing version information.");

        final String versionNumberString = string.substring(5);
        return parseVersionNumberString(versionNumberString);
    }

    /**
     * @throws IllegalArgumentException if the versionNumberString doesn't conform to the formats X.Y or X.Y.Z
     */
    public static VersionNumber parseVersionNumberString(final String versionNumberString)
    {
        StringTokenizer stringTokenizer = new StringTokenizer(versionNumberString, ".");

        try
        {
        final int major = nextInteger(stringTokenizer);
        final int minor = nextInteger(stringTokenizer);
            final Integer revision = nextInteger(stringTokenizer);

            if (stringTokenizer.hasMoreElements())
                throw new IllegalArgumentException("Has more than three components: "+versionNumberString);

        return new VersionNumber(major, minor, revision);
    }
        catch (NullPointerException e)
        {
            throw new IllegalArgumentException("Doesn't have two components: "+versionNumberString, e);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Version components aren't all numeric: "+versionNumberString, e);
        }
    }

    private static Integer nextInteger(final StringTokenizer stringTokenizer)
    {
        if (!stringTokenizer.hasMoreElements())
            return null;

        final String intermediateResult = (String)stringTokenizer.nextElement();
        return Integer.parseInt(intermediateResult);
    }

    @Override
    public String toString()
    {
        final StringBuilder version = new StringBuilder();

        version.append(majorNumber);
        version.append(".");
        version.append(minorNumber);

        if (revisionNumber!=null)
        {
            version.append(".");
            version.append(revisionNumber);
        }

        return version.toString();
    }
}
