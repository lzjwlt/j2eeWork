<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <h1>
                <xsl:apply-templates select="view/head/title"/>
                </h1>
            </head>
            <body>
                <xsl:apply-templates select="view/body/form"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="view/header/title">
        <xsl:element name="title">
            <xsl:value-of select="view/header/title"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="view/body/form">
        <xsl:element name="form">
            <xsl:attribute name="name">
                <xsl:value-of select="name"/>
            </xsl:attribute>
            <xsl:attribute name="action">
                <xsl:value-of select="action"/>
            </xsl:attribute>
            <xsl:attribute name="method">
                <xsl:value-of select="method"/>
            </xsl:attribute>
            <xsl:apply-templates select="./textView"/>
            <xsl:apply-templates select="./submitView"/>
            <br/>
            <xsl:apply-templates select="./buttonView"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="view/body/form/textView">
        <tr>
            <td align="left">
                <xsl:value-of select="label"/>
            </td>
            <td align="left">
                <xsl:element name="input">
                    <xsl:attribute name="name">
                        <xsl:value-of select="name"/>
                    </xsl:attribute>
                    <xsl:attribute name="value">
                        <xsl:value-of select="value"/>
                    </xsl:attribute>
                </xsl:element>
            </td>
        </tr>
        <br/>
    </xsl:template>

    <xsl:template match="view/body/form/submitView">
        <br/>
        <xsl:element name="input">
            <xsl:attribute name="type">submit</xsl:attribute>
            <xsl:attribute name="name">
                <xsl:value-of select="name"/>
            </xsl:attribute>
            <xsl:attribute name="value">
                <xsl:value-of select="label"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>

    <xsl:template match="view/body/form/buttonView">
        <br/>
        <xsl:element name="input">
            <xsl:attribute name="type">button</xsl:attribute>
            <xsl:attribute name="name">
                <xsl:value-of select="name"/>
            </xsl:attribute>
            <xsl:attribute name="value">
                <xsl:value-of select="label"/>
            </xsl:attribute>
            <xsl:attribute name="onclick">
                window.location.href='<xsl:value-of select="onclick"/>'
            </xsl:attribute>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>